package org.example;

public interface Game {
    void start(Integer sizeWord, Integer maxTry, Integer language);

    void gameOperation();

    Answer inputValue(String value);

    GameStatus getGameStatus();

}
