package View.Menus;

import java.util.Scanner;

public class ViewMenu {
    static Scanner scanner = new Scanner(System.in);
    final static int NUMBEROFLINESTOSKIP = 100;
    public static void print(String stringToBeWritten) {
        System.out.println(stringToBeWritten);
    }
    public static String input() {
        return scanner.nextLine();
    }
    public static void showTitle(String title) {
        print("========================"+title+"========================");
    }
    public static void manyLinesAfter(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            System.out.println("");
        }
    }
    public static void manyLinesAfter() {
        manyLinesAfter(NUMBEROFLINESTOSKIP);
    }
    public static void getConfirmation() {
        manyLinesAfter(2);
        print("Press Enter to continue");
        input();
    }
    protected static void show(String menuName) {
        manyLinesAfter();
        showTitle(menuName);
        manyLinesAfter(5);
    }
}
