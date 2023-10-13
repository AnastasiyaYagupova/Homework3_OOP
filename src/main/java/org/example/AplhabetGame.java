package org.example;

import java.util.ArrayList;
import java.util.Arrays;


public class AplhabetGame extends AbstractGame {
    ArrayList<String> alphabetEn = new ArrayList<>(Arrays.asList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")));
    ArrayList<String> alphabetRus = new ArrayList<>(Arrays.asList("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".split("")));
    ArrayList<String> numbers = new ArrayList<>(Arrays.asList("123456789".split("")));

    @Override
    ArrayList<String> generateCharListEn() {
        return alphabetEn;
    }

    @Override
    ArrayList<String> generateCharListRus() {
        return alphabetRus;
    }

    @Override
    ArrayList<String> generateCharListNumbers() {
        return numbers;
    }

}