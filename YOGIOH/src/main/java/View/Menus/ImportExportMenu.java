package View.Menus;

import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ImportExportMenu extends ViewMenu {
    static ImportExportMenu importExportMenu = new ImportExportMenu();
    public GridPane resultGridPane, cardNameGridPane;
    Scene scene;
    public static void run() {
        try {
            importExportMenu.start(stage);
            User.saveAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        BorderPane pane = FXMLLoader.load(getClass().getResource("ImportExportMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }

    public void backByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            back();
    }

    private void back() {
        MainMenu.run();
    }

    public void backByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            back();
    }

    public void importByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            importCard();
        }
    }

    private void importCard() {
        boolean success = Controller.Menus.ImportExportMenu.importCard(getStringOfAGridPane(cardNameGridPane));
        if (success) {
            message("success" ,resultGridPane);
        } else {
            message(Controller.Menus.ImportExportMenu.getError() , resultGridPane);
        }
    }

    public void importByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            importCard();
    }

    public void exportByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            exportCard();
    }

    private void exportCard() {
        boolean success = Controller.Menus.ImportExportMenu.exportCard(getStringOfAGridPane(cardNameGridPane));
        if (success) {
            message("success" , resultGridPane);
        } else {
            message(Controller.Menus.ImportExportMenu.getError() , resultGridPane);
        }
    }

    public void exportByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            exportCard();
    }

    //    static boolean toContinue = true;
//    public static void run() {
//        initializeMenu();
//        doMain();
//    }
//
//    private static void doMain() {
//        String input;
//        Command myCommand;
//        String typeOfMyCommand;
//        while (toContinue) {
//            show("IMPORT/EXPORT MENU");
//            input = input();
//            myCommand = new Command(input);
//            typeOfMyCommand = myCommand.getType();
//            switch (typeOfMyCommand) {
//                case "invalid type" :
//                    print("The command you inputted is not valid in this menu!!!");
//                    break;
//                case "exit":
//                    MenuController.setMenuName(MenuNames.Exit);
//                    toContinue = false;
//                    break;
//                case "show current menu":
//                    print("Import/Export Menu");
//                    break;
//                case "import":
//                    importCard(myCommand);
//                    break;
//                case "export":
//                    exportCard(myCommand);
//                    break;
//
//            }
//            getConfirmation();
//        }
//    }
//
//    private static void exportCard(Command command) {
//        String cardName = command.getField("card");
//        boolean success = Controller.Menus.ImportExportMenu.exportCard(cardName);
//        if (success) {
//            print("card exported successfully");
//        } else {
//            print(Controller.Menus.ImportExportMenu.getError());
//        }
//    }
//
//    private static void importCard(Command command) {
//        String cardName = command.getField("card");
//        boolean success = Controller.Menus.ImportExportMenu.importCard(cardName);
//        if (success) {
//            print("card imported successfully");
//        } else {
//            print(Controller.Menus.ImportExportMenu.getError());
//        }
//    }
//
//    private static void initializeMenu() {
//        toContinue = true;
//        Command.clearValidCommandTypes();
//        initializeExitCommandType();
//        initializeShowCurrentMenuCommandType();
//        initializeImportCommandType();
//        initializeExportCommandType();
//    }
//
//    private static void initializeExportCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("export");
//        commandType.setName("export");
//        commandType.addField("card");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeImportCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("import");
//        commandType.setMainPart("import");
//        commandType.addField("card");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeShowCurrentMenuCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("menu show-current");
//        commandType.setName("show current menu");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeExitCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("exit");
//        commandType.setMainPart("menu exit");
//        Command.addCommandType(commandType);
//    }
}
