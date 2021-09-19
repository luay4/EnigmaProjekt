package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Velkommen til den episke krypteringsmaskine!");
        System.out.println("Vælg en af følgende muligheder:");
        System.out.println("(1) Number cipher\n(2) Caesar cipher\n(3) Vigenére cipher\n(0) Exit");
        System.out.println("Tast 1, 2, 3 eller 0");
        int choice = in.nextInt();

        if (choice == 1) {
            numberCipherMenu();
        }
        if (choice == 2) {
            caesarCipherMenu();
        }
        if (choice == 3) {
            vigenereCipherMenu();
        }
        if (choice == 0) {
            System.out.println("Du har valgt exit. Tak for denne gang!");

        }

    }

    public static void vigenereCipherMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("Du har valgt Vigenére cipher");
        System.out.println("Tast e for encrypt eller d for decrypt:  ");
        String numberChoice = in.nextLine();
        if (numberChoice.equals("e")) {
            System.out.print("Indtast tekst: ");
            String vigenereEncrypt = in.nextLine();
            System.out.print("Indtast nøgleord: ");
            String keywordEncrypt = in.nextLine();
            System.out.print("Kodteksten er: ");
            System.out.println(arrayToString(vigenereCipherEncrypt(vigenereEncrypt, keywordEncrypt)));

        }
        if (numberChoice.equals("d")) {
            System.out.print("Indtast kodetekst: ");
            String vigenereDecrypt = in.nextLine();
            System.out.print("Indtast nøgleord: ");
            String keywordEncrypt = in.nextLine();
            System.out.print("Originalteksten er: ");
            System.out.println(arrayToString(vigenereCipherDecrypt(vigenereDecrypt, keywordEncrypt)));
        }
    }

    public static void numberCipherMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("Du har valgt Number cipher");
        System.out.println("Tast e for encrypt eller d for decrypt:  ");
        String numberChoice = in.nextLine();
        if (numberChoice.equals("e")) {
            System.out.print("Indtast tekst: ");
            String numberEncryptText = in.nextLine();
            System.out.print("Kodesekvensen er: ");
            System.out.println(Arrays.toString(numberCipherEncrypt(numberEncryptText)));
        }
        if (numberChoice.equals("d")) {
            System.out.print("Indtast kodesekvens: ");
            String numberDecryptText = in.nextLine();
            System.out.print("Originalteksten er: ");
            System.out.println(arrayToString(numberCipherDecrypt(numberDecryptText)));
        }
    }

    public static void caesarCipherMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("Du har valgt Caesar cipher");
        System.out.print("Indtast tekst/kodetekst: ");
        String caesarDecrypt = in.nextLine();
        System.out.print("Vælg shift (1-29) (minus hvis det skal decodes): ");
        int decryptShift = in.nextInt();
        System.out.print("Originalteksten er: ");
        System.out.println(arrayToString(caeserCipherEncrypAndDecrypt(caesarDecrypt, decryptShift)));
    }

    public static int letterToInt(String letter) {
        String[] alphabet = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å"};
        String let = letter.toUpperCase();
        int number = Arrays.binarySearch(alphabet, let);
        if (number == -28) {
            number = 29;
        }
        return number;
    }

    public static int shiftNumber(int number, int shift) {
        if (number == 0) {
            return 0;
        }
        int shiftedNumber = number + shift;
        return shiftedNumber;
    }

    public static int[] textToIntArray(String text) {
        int[] list = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            int number = letterToInt(text.substring(i, i+1));
            list[i] = number;
        }

        return list;
    }

    public static int[] shiftListOfNumbers(int[] numberList, int shift) {
        //kald shiftNumber for hver tal i numbers
        for (int i = 0; i < numberList.length; i++) {
            numberList[i] = shiftNumber(numberList[i], shift);
        }
        return numberList;
    }

    public static String[] caesarIntArrayToStringArray(int[] numberList, int shift) {
        int[] integerList = shiftListOfNumbers(numberList, shift - shift);
        String[] alphabet = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å", "A", "B", "C", "D",
                "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å"};
        String[] finalText = new String[integerList.length];

        for (int i = 0; i < integerList.length; i++) {
            finalText[i] = alphabet[integerList[i]];
        }
        return finalText;
    }

    public static String[] caeserCipherEncrypAndDecrypt(String text, int shift) {
        int[] numberList = textToIntArray(text);
        int[] shiftedNumberList = shiftListOfNumbers(numberList, shift);
        String[] finalText =  caesarIntArrayToStringArray(shiftedNumberList, shift);
        return finalText;
    }

    public static String arrayToString(String[] array){
        String arrayText = Arrays.toString(array);
        String arrayTextFixed = arrayText.replace("[", "").replace("]", "").replaceAll(",", "");
        return arrayTextFixed;
    }

    public static String[] stringToArray(String numbers) {
        String[] stringArray = numbers.replaceAll("\\{", "").replaceAll("}", "").replaceAll(" ", "")
                .replaceAll("\\[", "").replaceAll("]", "").split(",");
        return stringArray;
    }

    public static int[] stringArrayToIntArray(String numbers) {
        String[] stringArray = stringToArray(numbers);

        int[] intArray = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.valueOf(stringArray[i]);
        }
        return intArray;
    }

    public static String[] intArrayToStringArray(String numbers) {
        int[] integerList = stringArrayToIntArray(numbers);
        String[] alphabet = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å"};
        String[] finalText = new String[integerList.length];

        for (int i = 0; i < integerList.length; i++) {
            finalText[i] = alphabet[integerList[i]];
        }
        return finalText;
    }


    public static int[] numberCipherEncrypt(String text) {
        int[] numberList = textToIntArray(text);
        return numberList;
    }

    public static String[] numberCipherDecrypt(String text) {
        String[] finalText = intArrayToStringArray(text);
        return finalText;
    }


    public static String[] vigenereCipherEncrypt(String text, String keyword) {
        int[] textArray = textToIntArray(text);
        int[] keywordArray = textToIntArray(keyword);
        int[] newNumberlist = keywordShiftList(textArray, keywordArray);
        String[] stringArray = vigenereIntArrayToStringArray(newNumberlist);
        return stringArray;
    }

    public static String[] vigenereCipherDecrypt(String text, String keyword)  {
        int[] textArray = textToIntArray(text);
        int[] keywordArray = textToIntArray(keyword);
        int[] newNumberlist = keywordShiftBackList(textArray, keywordArray);
        String[] stringArray = vigenereIntArrayToStringArray(newNumberlist);
        return stringArray;
    }

    public static int[] keywordShiftList(int[] numberList, int[] keywordList) {
        int[] newNumberList = new int[numberList.length];
        int n = 0;
        for (int i = 0; i < numberList.length; i++) {
            newNumberList[i] = shiftNumber(numberList[i], keywordList[n]);
            n++;
            if (n == keywordList.length) {
                n = 0;
            }
        }
        return newNumberList;
    }

    public static int[] keywordShiftBackList(int[] numberList, int[] keywordList) {
        int[] newNumberList = new int[numberList.length];
        int n = 0;
        for (int i = 0; i < numberList.length; i++) {
            newNumberList[i] = shiftNumber(numberList[i], keywordList[n] * -1);
            n++;
            if (n == keywordList.length) {
                n = 0;
            }
        }
        return newNumberList;
    }

    public static String[] vigenereIntArrayToStringArray(int[] numberList) {
        String[] alphabet = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å", "A", "B", "C", "D",
                "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å"};
        String[] finalText = new String[numberList.length];

        for (int i = 0; i < numberList.length; i++) {
            finalText[i] = alphabet[numberList[i]];
        }
        return finalText;
    }
}
