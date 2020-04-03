package main;

import java.util.ArrayList;
import java.util.List;

/**
 * In clasa Board numberOfTokens indica numarul de jetoane de pe tabla de joc,
 * iar boardTokens este lista care contine toate jetoanele.
 * constructorul Board(List<Integer> values) construieste tabla de joc in functie de o lista de token-uri
 * (jetoane) date ca parametru.
 */
public class Board {
    int numberOfTokens;
    List<Token> boardTokens = new ArrayList<>();

    public int numberOfAvailableTokens() {
        int n = 0;
        for (Token token : boardTokens)
            if (token.used == true)
                n++;
        return n;
    }

    public Board(List<Integer> values) {
        for (Integer value : values) {
            Token token = new Token(value);
            this.boardTokens.add(token);
        }
    }

    public List<Token> getBoardTokens() {
        return boardTokens;
    }
}
