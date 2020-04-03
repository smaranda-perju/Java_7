package main;

import java.util.Arrays;

/**
 * In clasa Main instantiem jucatorii, tabla de joc, si jocul
 * jocul primeste ca parametri un numar care reprezinta lungimea maxima pe care o poate avea o progresie,
 * tabla de joc si o lista cu jucatorii
 */
public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Smaranda");
        Player p2 = new Player("Cris");
        Player p3 = new Player("Lia");

        Board board = new Board(Arrays.asList(0, 0, 4, 6, 8, 10, 11, 12, 13));
        Game game = new Game(5, board, Arrays.asList(p1, p2, p3));
        game.startGame();
    }
}
