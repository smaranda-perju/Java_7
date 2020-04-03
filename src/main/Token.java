package main;

/**
 * In clasa Token sunt initializate jetoanele de pe tabla de joc.
 * cand value este 0 inseamna ca jetonul este joker si jucatorul ii va atribui valoarea dorita.
 * used indica daca jetonul a fost folosit sau nu.
 */

public class Token {
    int value; //nu este de tip private pentru cazul in care este joker si valoarea poate fi aleasa de jucator din alta clasa
    boolean used;

    public Token(int value) {
        this.value = value;
        this.used = false;
    }

    public int getValue() {
        return value;
    }
}
