package View.Menus;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Scanner;

public class ViewMenu extends Application {
    static Scanner scanner = new Scanner(System.in);
    public static Stage stage;// = new Stage();
    final static int NUMBEROFLINESTOSKIP = 100;
    public static void print(String stringToBeWritten) {
        System.out.println(stringToBeWritten);
    }
    public static void write(String stringToBeWritten) {
        System.out.print(stringToBeWritten);
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

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
    public boolean isPrimary(MouseEvent mouseEvent) {
        String mouseButton = mouseEvent.getButton().toString();
        if (mouseButton.equals("PRIMARY")) {
            return true;
        }
        return false;

    }
    public boolean isSecondary(MouseEvent mouseEvent) {
        String mouseButton = mouseEvent.getButton().toString();
        if (mouseButton.equals("SECONDARY")) {
            return true;
        }return false;
    }
    public boolean isChoiceKey(KeyEvent keyEvent) {
        String keyName = keyEvent.getCode().toString();
        if (keyName.equals("ENTER") || keyName.equals("SPACE")) {
            return true;
        }
        return false;
    }

    /**
     * This method gets string of the first child of a gridPane if that child is a TextField or a PasswordField
     * @param gridPane the gridPane you want to get text from.
     *
     * @param isPassword is a boolean which should be true if the firs child is a PasswordChild and false if it is a TextField
     *                   and you can don't give it so it is false be default (it will be considered as aTextField).
     */
    public String getStringOfAGridPane(GridPane gridPane , boolean isPassword) {
        Object[] children = (Object[]) gridPane.getChildren().toArray();
        return (isPassword ? (((PasswordField) children[0]).getText()) : ((TextField) children[0]).getText());
    }
    /**
     * This method is actually getStringOfAGridPane(GridPane gridPane , boolean isPassword) which isPassword is false
     *
     */
    public String getStringOfAGridPane(GridPane gridPane) {
        return getStringOfAGridPane(gridPane , false);
    }

    /**
     * This method writes a message on a place of our scene where is a Label of javafx which is the first child
     *          of a gridPane.
     * @param message : String the message you want to show to user.
     * @param gridPane : GridPane the gridPane which we want to set text of that first child which is a Label.
     */
    public void message(String message , GridPane gridPane){
        Object[] children = gridPane.getChildren().toArray();
        Label label = (Label) children[0];
        label.setText(message);
        System.out.println(message);
    }

}
