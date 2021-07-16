package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartMenu extends ViewMenu {
    static Scene scene;
    static StartMenu startMenu = new StartMenu();
    public GridPane usernameToLoginGridPane, passwordToLoginGridPane, stayInGridPane, resultLoginGridPane,
             usernameToRegisterGridPane, passwordToRegisterGridPane, nicknameToRegisterGridPane,
             resultOfRegisteringGridPane;
    public static void run() {
        try {
            launch();
        } catch (Exception e) {
            try {
                startMenu.start(stage);
            } catch (Exception exception) {
                MenuController.setMenuName(MenuNames.Exit);
            }
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
        BorderPane pane = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }



    public void registerByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            register();
        }
    }

    private void register() {
        String username = getStringOfAGridPane(usernameToRegisterGridPane);
        String password = getStringOfAGridPane(passwordToRegisterGridPane);
        String nickname = getStringOfAGridPane(nicknameToRegisterGridPane);
        boolean success = Controller.Menus.StartMenu.register(username, nickname, password);
        if (success) {
            message("حساب شما پیروزمندانه ساخته‌شد" , resultOfRegisteringGridPane);
        } else {
            message(Controller.Menus.StartMenu.getError() , resultOfRegisteringGridPane);
        }
    }

    public void registerByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            register();
        }
    }

    public void loginByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            login();
        }
    }

    private void login() {
        String username = getStringOfAGridPane(usernameToLoginGridPane);
        String password = getStringOfAGridPane(passwordToLoginGridPane);
        boolean success = Controller.Menus.StartMenu.login(username, password);
        if (success) {
            try {
                MainMenu.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            message(Controller.Menus.StartMenu.getError() , resultLoginGridPane);
        }
    }

    public void loginByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            login();
        }
    }


//    public static void run1() {
//        initializeMenu();
//        doMain();
//    }
//
//    protected static void doMain() {
//        String input;
//        Command myCommand;
//        String typeOfMyCommand;
//        while (toContinue) {
//            show("LOGIN MENU");
//            input = input();
//            myCommand = new Command(input);
//            typeOfMyCommand = myCommand.getType();
//            switch (typeOfMyCommand) {
//                case "invalid type" :
//                    print("The command you inputted is not valid in this menu!!!");
//                    break;
//                case "login":
//                    login(myCommand);
//                    break;
//                case "exit":
//                    MenuController.setMenuName(MenuNames.Exit);
//                    toContinue = false;
//                    break;
//                case "show current menu":
//                    print("Login Menu");
//                    break;
//                case "create user":
//                    createUser(myCommand);
//                    break;
//            }
//            getConfirmation();
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//    // for phase 1
//    private static void login(Command myCommand) {
//        boolean success = Controller.Menus.StartMenu.login(myCommand.getField("username"),
//                myCommand.getField("password"));
//        if (success) {
//            print("You logged in successfully");
//            toContinue = false;
//            // in controller : MenuController.setMenuName(MenuNames.MainMenu);
//        } else {
//            print(Controller.Menus.StartMenu.getError());
//        }
//    }
//
//    private static void createUser(Command myCommand) {
//        boolean success = Controller.Menus.StartMenu.register(myCommand.getField("username"),
//                myCommand.getField("nickname"),
//                myCommand.getField("password"));
//        if (success) {
//            print("user created successfully");
//        } else {
//            print(Controller.Menus.StartMenu.getError());
//        }
//    }
//
//    private static void initializeMenu() {
//        toContinue = true;
//        Command.clearValidCommandTypes();
//        initializeLoginCommandType();
//        initializeExitCommandType();
//        initializeShowCurrentMenuCommandType();
//        initializeCreateUserCommandType();
//    }
//
//    private static void initializeCreateUserCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("create user");
//        commandType.setMainPart("user create");
//        commandType.addField("username");
//        commandType.addField("password");
//        commandType.addField("nickname");
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
//
//    private static void initializeLoginCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("user login");
//        commandType.setName("login");
//        commandType.addField("username");
//        commandType.addField("password");
//        Command.addCommandType(commandType);
//    }


}