package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenu extends ViewMenu {
    static MainMenu mainMenu = new MainMenu();
    Scene scene;
    public static void run() {
        try {
            mainMenu.start(stage);
            User.saveAllUsers();
        } catch (Exception exception) {
            MenuController.setMenuName(MenuNames.Exit);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }

    public void logoutByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            logout();
        }
    }

    private void logout() {
        MenuController.setLoggedInUser(null);
        MenuController.setMenuName(MenuNames.StartMenu);
        StartMenu.run();
    }

    public void logoutByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            logout();
        }
    }

    public void goToImportExportMenuBuMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            goToImportExportMenu();
        }
    }

    private void goToImportExportMenu() {
        ImportExportMenu.run();
    }

    public void goToImportExportMenuByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            goToImportExportMenu();
    }

    public void goToShopMenuByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            goToShopMenu();
    }

    private void goToShopMenu() {
        ShopMenu.run();
    }

    public void goToShopMenuByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            goToShopMenu();
    }

    public void goToProfileMenuByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            goToProfileMenu();
    }

    private void goToProfileMenu() {
        ProfileMenu.run();
    }

    public void goToProfileMenuByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            goToProfileMenu();
    }

    public void goToScoreBoardMenuByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            goToScoreBoardMenu();
    }

    private void goToScoreBoardMenu() {
        ScoreBoardMenu.run();
    }

    public void goToScoreBoardMenuByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            goToScoreBoardMenu();
    }

    public void goToDeckMenuByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            goToDeckMenu();
    }

    private void goToDeckMenu() {
        DeckMenu.run();
    }

    public void goToDeckMenuByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            goToDeckMenu();
    }

    public void startByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            DuelMenu.run();
        }
    }

    public void startByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            DuelMenu.run();
        }
    }


//    private static boolean toContinue = true;
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
//            show("MAIN MENU");
//            input = input();
//            myCommand = new Command(input);
//            typeOfMyCommand = myCommand.getType();
//            switch (typeOfMyCommand) {
//                case "invalid type":
//                    print("The command you inputted is not valid in this menu!!!");
//                    break;
//                case "exit":
//                    MenuController.setMenuName(MenuNames.StartMenu);
//                    toContinue = false;
//                    break;
//                case "show current menu":
//                    print("Main Menu");
//                    break;
//                case "enterProfileMenu":
//                    MenuController.setMenuName(MenuNames.ProfileMenu);
//                    toContinue = false;
//                    break;
//                case "duel":
//                    duel(myCommand);
//                    break;
//                case "duel with ai":
//                    duelWithAI(myCommand);
//                    break;
//            }
//            getConfirmation();
//        }
//    }
//
//    private static void duelWithAI(Command command) {
//        String rounds = command.getField("round");
//        boolean success = Controller.Menus.DuelMenu.duelWithAI(rounds);
//        if (success) {
//            print("success");
//            MenuController.setMenuName(MenuNames.DuelMenu);
//        } else {
//            print(Controller.Menus.DuelMenu.getError());
//        }
//    }
//
//    private static void duel(Command command) {
//        String secondPlayer = command.getField("second-player");
//        String rounds = command.getField("round");
//        boolean success = Controller.Menus.DuelMenu.duel(secondPlayer , rounds);
//        if (success) {
//            print("success!");
//            toContinue = false;
//            MenuController.setMenuName(MenuNames.DuelMenu);
//        } else {
//            print(Controller.Menus.DuelMenu.getError());
//        }
//    }
//
//    private static void initializeMenu() {
//        toContinue = true;
//        Command.clearValidCommandTypes();
//        initializeLogoutCommandType();
//        initializeExitCommandType();
//        initializeMenuEnterCommandTypes();
//        initializeShowCurrentCommandType();
//        initializeNewDuelCommandType();
//        initializeNewDuelWithAICommandType();
//    }
//
//    private static void initializeNewDuelWithAICommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("duel");
//        commandType.setName("duel with ai");
//        commandType.addField("new");
//        commandType.addField("ai");
//        commandType.getField("ai").setDoesHaveArgument(false);
//        commandType.getField("new").setDoesHaveArgument(false);
//        commandType.addField("round");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeNewDuelCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("duel");
//        commandType.setMainPart("duel");
//        commandType.addField("new");
//        commandType.getField("new").setDoesHaveArgument(false);
//        commandType.addField("second-player");
//        commandType.addField("round");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeShowCurrentCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("menu show-current");
//        commandType.setName("show current menu");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeMenuEnterCommandTypes() {
//        initializeProfileMenuEnterCommand();
//        initializeDuelMenuEnterCommand();
//    }
//
//    private static void initializeDuelMenuEnterCommand() {
//        CommandType commandType = new CommandType();
//        commandType.setName("enterDuelMenu");
//        commandType.setMainPart("menu enter duel");
//    }
//
//    private static void initializeProfileMenuEnterCommand() {
//        CommandType commandType = new CommandType();
//        commandType.setName("enterProfileMenu");
//        commandType.setMainPart("menu enter profile");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeExitCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("exit");
//        commandType.setMainPart("menu exit");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeLogoutCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("logout");
//        commandType.setMainPart("user logout");
//        Command.addCommandType(commandType);
//    }

}
