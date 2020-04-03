package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In clasa Game over indica daca s-a terminat jocul,
 * metoda anyMovesLeft indica daca sunt jucatori care trebuie sa mai faca mutari
 * constructorul Game(int sizeOfProgression, Board board, List<Player> players) primeste ca parametri
 * lungimea maxima pe care o progresie o poate avea astfel incat jocul sa se termine, tabla de joc si lista de jucatori
 * metoda startGame creaza cate un thread pt fiecare jucator pentru a incepe jocul
 */
public class Game extends Thread {
    int sizeOfProgression;
    Board board;
    boolean over = false;
    List<Player> players = new ArrayList<>();

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean anyMovesLeft() {
        for (Player player : players)
            return true;
        return false;
    }

    Game(int sizeOfProgression, Board board, List<Player> players) {
        this.sizeOfProgression = sizeOfProgression;
        this.board = board;
        this.players = players;
        for (Player player : players) {
            player.setGame(this);
        }
    }

    public void startGame() {
        List<Thread> threads = new ArrayList<Thread>();
        for (Player player : players) {
            Thread thread = new Thread(player);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
