package main;

import java.util.ArrayList;
import java.util.List;

/**
 * In clasa Player commonDifference semnifica ratia din progresia aritmetica, mai retinem un punctaj si numele jucatorului.
 * Setam synchronized pe game.board pentru ca board este resursa pe care toti jucatorii o vor accesa in paralel.
 * Prima data verificam daca jocul s-a terminat, daca da ne oprim (return), daca nu, folosim variabila move car indica daca jucatorul a facut o mutare sau nu.
 * parcurgem jetoanele, daca jucatorul nu a mutat si daca jetonul nu a fost folosit inca si daca nr de piese din mana jucatorului
 * este mai mic de 2, atunci jucatorul va lua jetonul respectiv, daca este joker (jetonul are value=0), jucatorul nu va lua jetonul respectiv, daca nu e joker
 * va lua jetonul is used va deveni true
 * scorul va creste si se va afisa numele si jetonul luat de fiecare jucator
 * dupa verificam daca jucatorul are doua jetoane in mana, daca da vom calcula ratia (commonDifference) si dupa verificam daca mai raman
 * jetoane pe masa dupa ultima mutare, daca nu mai raman jocul se termina, alftel daca jucatorul are mai mult de doua jetoane si ultimul extras e egal cu penultimul+ratia,
 * sau este joker (caz in care va primi o valoare convenabila), scorul va creste, used va deveni true pentru jetoanele folosite si jucatorii vor fi afisati din nou alaturi de valoarea ultimului jeton extras
 * verificam apoi daca mai pot avea loc mutari (daca mai sunt jetoane pe masa de joc)
 * daca nu mai pot avea loc mutari sau cineva a ajuns la limita maxima a dimensiunii progresiei (sizeOfProgression din clasa Game), jocul se termina
 */
public class Player implements Runnable {
    int commonDifference = 0;
    int score = 0;
    boolean finished = false;
    String name;
    List<Token> playersTokens = new ArrayList<>();
    Game game;

    public Player(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public synchronized void run() {
        while (this.finished == false) {
            synchronized (game.board) {
                if (game.over)
                    return;
                else {
                    boolean move = false;
                    for (Token token : game.board.getBoardTokens()) {
                        if (move == false) {
                            if (token.used == false) {
                                if (playersTokens.size() < 2) {
                                    if (token.value == 0) continue;
                                    playersTokens.add(token);
                                    token.used = true;
                                    score++;
                                    System.out.println("Jucatorul " + name + ": " + token.value);
                                    if (playersTokens.size() == 2) {
                                        commonDifference = playersTokens.get(1).value - playersTokens.get(0).value;
                                    }
                                    if (game.board.numberOfAvailableTokens() == 0)
                                        game.over = true;
                                    move = true;
                                    break;
                                } else {
                                    if (token.value == playersTokens.get(playersTokens.size() - 1).value + commonDifference || token.value == 0) {
                                        if (token.value == 0)
                                            token.value = playersTokens.get(playersTokens.size() - 1).value + commonDifference;
                                        playersTokens.add(token);
                                        token.used = true;
                                        score++;
                                        System.out.println("Jucatorul " + name + ": " + token.value);
                                        if (game.board.numberOfAvailableTokens() == 0)
                                            game.over = true;
                                        move = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (move == false) {
                        finished = true;
                    }
                    if (playersTokens.size() == game.sizeOfProgression) {
                        game.over = true;
                    }
                    if (!game.anyMovesLeft()) {
                        game.over = true;
                    }
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
