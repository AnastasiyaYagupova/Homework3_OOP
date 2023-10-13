package org.example;

import java.util.*;

public abstract class AbstractGame implements Game {

    Integer sizeWord;
    Integer maxTry;
    String computerWord;
    Integer language;
    GameStatus gameStatus = GameStatus.INIT;

    public AbstractGame() {
        super();
    }

    abstract ArrayList<String> generateCharListEn();

    abstract ArrayList<String> generateCharListRus();

    abstract ArrayList<String> generateCharListNumbers();


    @Override
    public void start(Integer sizeWord, Integer maxTry, Integer language) {
        this.sizeWord = sizeWord;
        this.maxTry = maxTry;
        this.language = language;
        computerWord = generateWord();
        gameStatus = GameStatus.START;
    }

    private String generateWord() {
        Random random = new Random();
        String res = "";
        if (Objects.equals(language, 1)) {
            List<String> alphabet = generateCharListRus();
            for (int i = 0; i < sizeWord; i++) {
                int randomIndex = random.nextInt(alphabet.size());
                res += alphabet.get(randomIndex);
                alphabet.remove(randomIndex);
            }
            Logging.logger.info("СomputerWord - " + res);
            return res;
        } else if (Objects.equals(language, 2)) {
            List<String> alphabet = generateCharListEn();
            for (int i = 0; i < sizeWord; i++) {
                int randomIndex = random.nextInt(alphabet.size());
                res += alphabet.get(randomIndex);
                alphabet.remove(randomIndex);
            }
            Logging.logger.info("СomputerWord - " + res);
            return res;
        } else if (Objects.equals(language, 3)) {
            List<String> alphabet = generateCharListNumbers();
            for (int i = 0; i < sizeWord; i++) {
                int randomIndex = random.nextInt(alphabet.size());
                res += alphabet.get(randomIndex);
                alphabet.remove(randomIndex);
            }
            Logging.logger.info("СomputerWord - " + res);
            return res;
        } else {
            return "You entered an incorrect value.";
        }
    }

    @Override
    public Answer inputValue(String value) {
        Logging.logger.info("You word - " + value);
        int bull = 0;
        int cow = 0;
        try {
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) == computerWord.charAt(i)) {
                    bull++;
                    cow++;
                } else if (computerWord.contains(String.valueOf(value.charAt(i)))) {
                    cow++;
                }
            }
            if (bull == computerWord.length()) {
                gameStatus = GameStatus.WIN;
            }
            maxTry--;
            if (maxTry == 0 && gameStatus != GameStatus.WIN) {
                gameStatus = GameStatus.LOSE;
            }
            return new Answer(bull, cow, maxTry);
        } catch (Exception e) {
            System.out.println("Enter a word of length - " + computerWord.length());
        }
        return null;
    }

    @Override
    public void gameOperation() {
        Scanner scanner = new Scanner(System.in);
        Game game = new AplhabetGame();

        System.out.println("Enter the length of the word: ");
        Integer sizeWord = Integer.parseInt(scanner.nextLine());
        Logging.logger.info("Your sizeWord - " + sizeWord.toString());

        System.out.println("Enter the number of attempts: ");
        Integer maxTry = Integer.parseInt(scanner.nextLine());
        Logging.logger.info("Your maxTry - " + maxTry.toString());

        System.out.println("Choise language(1 - RU, 2 - EN, 3 - NUM): ");
        Integer language = Integer.parseInt(scanner.nextLine());
        Logging.logger.info("Your language - " + language.toString());

        game.start(sizeWord, maxTry, language);
        while (game.getGameStatus() != GameStatus.WIN && game.getGameStatus() != GameStatus.LOSE) {
            System.out.println("Enter your word(UPPERCASE): ");
            Answer answer = game.inputValue(scanner.nextLine());
            System.out.println("Result: " + answer);
        }
        System.out.println("Your status - " + game.getGameStatus());
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
