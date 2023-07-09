/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha8;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ines Aguas
 */
public class Servidor extends UnicastRemoteObject implements InterfaceJogo {

    private ArrayList<Jogo> jogos = new ArrayList<>();
    private Map<InterfaceCliente, Jogo> clientes = new HashMap<>();

    Servidor() throws RemoteException {
        super();
    }

    @Override
    public Jogo obtemJogo(int id) throws RemoteException {
        if (jogos.isEmpty()) {
            return null;
        }

        for (Jogo j : jogos) {
            if (j.getId() == id) {
                if (j.getEstado() == 2 || j.getEstado() == 4) {
                    j.calculaTempo();
                }
                return j;
            }
        }
        return null;
    }

    @Override
    public Jogo[] obtemJogoPorEstado(int estado) throws RemoteException {
        ArrayList<Jogo> jogosEstado = new ArrayList<>();
        if (jogos.isEmpty()) {
            return null;
        }

        Jogo[] lista = new Jogo[jogosEstado.size()];

        if (estado == 6) {
            for (Jogo j : jogos) {
                if (j.getEstado() == 2 || j.getEstado() == 4) {
                    j.calculaTempo();
                }
            }
            lista = jogos.toArray(lista);
            return lista;
        }

        for (Jogo j : jogos) {
            if (j.getEstado() == estado) {
                if (j.getEstado() == 2 || j.getEstado() == 4) {
                    j.calculaTempo();
                }
                jogosEstado.add(j);
            }
        }

        if (!jogosEstado.isEmpty()) {
            lista = jogosEstado.toArray(lista);
            return lista;
        }
        return null;
    }

    @Override
    public synchronized boolean adicionaJogo(Jogo novo) throws RemoteException {
        if (novo.getEquipa1().isBlank() || novo.getEquipa2().isBlank() || novo.getData().isBlank()) {
            return false;
        }

        if (!verificaData(novo.getData())) {
            return false;
        }

        if (jogos.isEmpty()) {
            novo.setId(1);
        } else {
            novo.setId(jogos.get(jogos.size() - 1).getId() + 1);
        }
        jogos.add(novo);
        return true;
    }

    @Override
    public synchronized boolean removeJogo(int id) throws RemoteException {
        for (Jogo j : jogos) {
            if (j.getId() == id) {
                jogos.remove(j);
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean alteraEstado(int id, int novo_estado) throws RemoteException {
        if (novo_estado < 1 || novo_estado > 5) {
            return false;
        }
        for (Jogo j : jogos) {
            if (j.getId() == id) {
                if (novo_estado != j.getEstado() + 1) {
                    return false;
                } else {
                    j.setEstado(novo_estado);
                    notificarEstado(j);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public synchronized boolean alteraScore(int id, int score1, int score2) throws RemoteException {
        if (score1 < 0 || score2 < 0) {
            return false;
        }

        for (Jogo j : jogos) {
            if (j.getId() == id) {
                if (score1 >= j.getScore1() && score2 >= j.getScore2()) {
                    j.setScores(score1, score2);
                    notificarScore(j);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean verificaData(String data) {
        SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

        try {
            dateF.setLenient(false);
            dateF.parse(data);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            Servidor serv = new Servidor();

            reg.bind("jogos", serv);

            System.out.println("Servidor RMI iniciado.\nIP: " + InetAddress.getLocalHost().getHostAddress());

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean login(InterfaceCliente cli, int id) throws RemoteException {
        for (Jogo j : jogos) {
            if (j.getId() == id) {
                clientes.put(cli, j);

                Thread teste = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                if (j.getEstado() == 2 || j.getEstado() == 4) {
                                    j.calculaTempo();
                                    long tempo = System.currentTimeMillis();
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                    Date data = new Date(tempo);
                                    cli.atualizaTempo(j, sdf.format(data));
                                     Thread.sleep(10000);
                                }
                            } catch (Exception ex) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                });

                teste.start();
                return true;
            }
        }
        return false;
    }

    private void notificarScore(Jogo jogo) throws RemoteException {
        for (InterfaceCliente i : clientes.keySet()) {
            if (clientes.get(i).getId() == jogo.getId()) {
                long tempo = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date data = new Date(tempo);
                i.atualizaScore(jogo, sdf.format(data));
            }
        }
    }

    private void notificarEstado(Jogo jogo) throws RemoteException {
        for (InterfaceCliente i : clientes.keySet()) {
            if (clientes.get(i).getId() == jogo.getId()) {
                long tempo = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date data = new Date(tempo);
                i.atualizaEstado(jogo, sdf.format(data));
            }
        }
    }

    @Override
    public void logout(InterfaceCliente cli) throws RemoteException {
        clientes.remove(cli);
    }

}
