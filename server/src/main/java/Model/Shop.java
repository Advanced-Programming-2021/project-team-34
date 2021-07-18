package Model;

import FinalStrings.Results;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shop {
    private static final String ADMIN_USERNAME = "admin";
    private static HashMap<String, Integer> prices = new HashMap<>();
    private static HashMap<String, Integer> amount = new HashMap<>();
    private static ArrayList<String> forbidden = new ArrayList<>();

    public static void initializeHashMaps() {
        try {
            String text;
            File file = new File("src/main/resources/Monster.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                text = scanner.nextLine();
                //Battle OX,4,EARTH,Beast-Warrior,Normal,1700,1000,"A monster with tremendous power, it destroys enemies with a swing of its axe.",2900,100
                Pattern pattern = Pattern.compile("(.+),(\\d+),(.+),(.+),(.+),(\\d+),(\\d+),(.+),(\\d+),(\\d+)");
                Matcher matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    String cardName = matcher.group(1);
                    int price = Integer.parseInt(matcher.group(9));
                    int number = Integer.parseInt(matcher.group(10));
                    prices.put(cardName, price);
                    amount.put(cardName, number);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(Results.ERROR);
        }
    }

    public static void increaseCard(String cardName, int numberToIncrease) {
        try {
            StringBuilder answer = new StringBuilder("1");
            String text;
            File file = new File("src/main/resources/Monster.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                text = scanner.nextLine();
                //Battle OX,4,EARTH,Beast-Warrior,Normal,1700,1000,"A monster with tremendous power, it destroys enemies with a swing of its axe.",2900,100
                Pattern pattern = Pattern.compile("(.+),(\\d+),(.+),(.+),(.+),(\\d+),(\\d+),(.+),(\\d+),(\\d+)");
                Matcher matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    if (cardName.equals(matcher.group(1))) {
                        int currentNumber = Integer.parseInt(matcher.group(10));
                        int numberOfDigits = String.valueOf(currentNumber).length();
                        int number = currentNumber + numberToIncrease;
                        text = text.substring(0, text.length() - numberOfDigits);
                        text += number;
                    }
                    answer.append(text).append("\n");
                }
            }
            answer.deleteCharAt(0);
            writeInFile(answer);
        } catch (IOException e) {
            System.out.println(Results.ERROR);
        }
    }

    public static void decreaseCard(String cardName, int numberToDecrease) {
        try {
            StringBuilder answer = new StringBuilder("1");
            String text;
            File file = new File("src/main/resources/Monster.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                text = scanner.nextLine();
                //Battle OX,4,EARTH,Beast-Warrior,Normal,1700,1000,"A monster with tremendous power, it destroys enemies with a swing of its axe.",2900,100
                Pattern pattern = Pattern.compile("(.+),(\\d+),(.+),(.+),(.+),(\\d+),(\\d+),(.+),(\\d+),(\\d+)");
                Matcher matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    if (cardName.equals(matcher.group(1))) {
                        int currentNumber = Integer.parseInt(matcher.group(10));
                        int numberOfDigits = String.valueOf(currentNumber).length();
                        int number = Math.max(currentNumber - numberToDecrease, 0);
                        text = text.substring(0, text.length() - numberOfDigits);
                        text += number;
                    }
                    answer.append(text).append("\n");
                }
            }
            answer.deleteCharAt(0);
            writeInFile(answer);
        } catch (IOException e) {
            System.out.println(Results.ERROR);
        }
    }

    public static void buyCard(String cardName, User user) {
        if (user.getCards().containsKey(Card.getAllCards().get(cardName)))
            user.getCards().put(Card.getAllCards().get(cardName),
                    user.getCards().get(Card.getAllCards().get(cardName)) + 1);
        else user.getCards().put(Card.getAllCards().get(cardName), 1);
        user.setCoin(user.getCoin() - prices.get(cardName));
        Shop.decreaseCard(cardName, 1);
    }

    public static void sellCard(String cardName, User user) {
        user.getCards().put(Card.getAllCards().get(cardName), user.getCards().get(Card.getAllCards().get(cardName)) - 1);
        user.setCoin(user.getCoin() + prices.get(cardName));
        Shop.increaseCard(cardName, 1);
    }

    private static void writeInFile(StringBuilder answer) throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/Monster.csv");
        writer.write(answer.toString());
        writer.close();
    }

    public static HashMap<String, Integer> getPrices() {
        return prices;
    }

    public static HashMap<String, Integer> getAmount() {
        return amount;
    }

    public static ArrayList<String> getForbidden() {
        return forbidden;
    }

    public static String getAdminUsername() {
        return ADMIN_USERNAME;
    }
}
