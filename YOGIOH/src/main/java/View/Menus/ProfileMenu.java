package View.Menus;

import Controller.Connection;
import Controller.MenuController;
import Controller.MenuNames;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ProfileMenu extends ViewMenu {
    static ProfileMenu profileMenu = new ProfileMenu();
    Scene scene;
    public GridPane usernameGridPane, scoreGridPane, moneyGridPane, nicknameGridPane, resultGridPane;
    @FXML
    public Circle profileImageCircle;
    public static void run() {
        try {
            profileMenu.start(stage);
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
        Pane pane = FXMLLoader.load(getClass().getResource("ProfileMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }

    @FXML
    private void initialize() {
        message("@"+MenuController.getLoggedInUser().getUsername() , usernameGridPane);
        Object[] children = nicknameGridPane.getChildren().toArray();
        TextField textFieldOfNickname = (TextField) children[0];
        textFieldOfNickname.setText(MenuController.getLoggedInUser().getNickname());
        textFieldOfNickname.deselect();
        ImagePattern shape = new ImagePattern(
                new Image(getClass().getResource(MenuController.getLoggedInUser().getAvatarName()).toExternalForm()));
        profileImageCircle.setFill(shape);
        message(MenuController.getLoggedInUser().getHighScore()+"" , scoreGridPane);
        message(MenuController.getLoggedInUser().getCoin()+"" , moneyGridPane);
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

    public void initializeMenu(KeyEvent keyEvent) {
        ;
    }

    public void keyTyped(KeyEvent keyEvent) {
        print(keyEvent.toString());
    }

    public void changeNicknameByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            changeNickname();
    }

    private void changeNickname() {
        String result = Connection.sendMessageToTheServer("change nickname newNickname "+
                (getStringOfAGridPane(nicknameGridPane))+" token "+MenuController.getToken());
        if (result.equals("success")) {
            message("نام مستعار شما پیروزمندانه ویراسته شد" , resultGridPane);
        } else {
            message(result , resultGridPane);
        }
    }

    public void changeNicknameByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            changeNickname();
    }

    public void changePasswordByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            ChangePasswordMenu.run();
    }

    public void changePasswordByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            ChangePasswordMenu.run();
    }

    public void changeAvatarByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            changeAvatar();
    }

    private void changeAvatar() {
        MenuController.getLoggedInUser().changeAvatar();
        Connection.sendMessageToTheServer("change avatar token "+MenuController.getToken()+
                " newAvatar "+MenuController.getLoggedInUser().getAvatarInt());
        ImagePattern shape = new ImagePattern(
                new Image(getClass().getResource(MenuController.getLoggedInUser().getAvatarName()).toExternalForm()));
        profileImageCircle.setFill(shape);
    }

    public void changeAvatarByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            changeAvatar();
    }

    //
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
//            show("PROFILE MENU");
//            input = input();
//            myCommand = new Command(input);
//            typeOfMyCommand = myCommand.getType();
//            switch (typeOfMyCommand) {
//                case "invalid type":
//                    print("The command you inputted is not valid in this menu!!!");
//                    break;
//                case "exit":
//                    MenuController.setMenuName(MenuNames.MainMenu);
//                    toContinue = false;
//                    break;
//                case "show current menu":
//                    print("Profile Menu");
//                    break;
//                case "change nickname" :
//                    changeNickname(myCommand);
//                    break;
//                case "change password":
//                    changePassword(myCommand);
//                    break;
//            }
//            getConfirmation();
//        }
//    }
//
//    private static void changePassword(Command myCommand) {
//        String currentPassword = myCommand.getField("current");
//        String newPassword = myCommand.getField("new");
//        boolean success = Controller.Menus.ProfileMenu.changePassword(currentPassword, newPassword);
//        if (success) {
//            print("password changed successfully");
//        } else {
//            print(Controller.Menus.ProfileMenu.getError());
//        }
//    }
//
//    private static void changeNickname(Command myCommand) {
//        String nickname = myCommand.getField("nickname");
//        boolean success = Controller.Menus.ProfileMenu.changeNickname(nickname);
//        if (success) {
//            print("nickname changed successfully");
//        } else {
//            print(Controller.Menus.ProfileMenu.getError());
//        }
//    }
//
//
//    private static void initializeMenu() {
//        toContinue = true;
//        Command.clearValidCommandTypes();
//        initializeExitCommandType();
//        initializeChangeNicknameCommandType();
//        initializeChangePasswordCommandType();
//        initializeShowCurrentMenuCommandType();
//
//    }
//
//    private static void initializeShowCurrentMenuCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("menu show-current");
//        commandType.setName("show current menu");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeChangePasswordCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("change password");
//        commandType.setMainPart("profile change");
//        commandType.addField("password");
//        commandType.getField("password").setDoesHaveArgument(false);
//        commandType.addField("current");
//        commandType.addField("new");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeChangeNicknameCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("profile change");
//        commandType.setName("change nickname");
//        commandType.addField("nickname");
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
