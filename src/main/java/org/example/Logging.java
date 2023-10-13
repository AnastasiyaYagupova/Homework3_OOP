package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    public static Logger logger = Logger.getAnonymousLogger();

    void log() {
        FileHandler fileHandler;
        Boolean flag = true;
        try {
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "%1$tF %1$tR %5$s %n");
            fileHandler = new FileHandler("log.txt");
            logger.addHandler(fileHandler);
            SimpleFormatter sFormatter = new SimpleFormatter();
            fileHandler.setFormatter(sFormatter);

            while (flag) {
                Scanner scanner = new Scanner(System.in);
                AbstractGame gameOperation = new AplhabetGame();
                gameOperation.gameOperation();
                System.out.println("Do you want to restart?\n1 - yes \n2 - no \n3 - check logs");
                Integer word = scanner.nextInt();
                if (word == 2) {
                    System.out.println("GAME OVER!");
                    flag = false;
                }
                if (word == 3) {
                    try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                    }
                    flag = false;
                }
            }
        } catch (Exception e) {
        }
    }
}
