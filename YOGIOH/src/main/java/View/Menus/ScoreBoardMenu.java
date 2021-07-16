package View.Menus;
import Controller.MenuController;
import Model.User;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoardMenu extends ViewMenu {
    public static void run() {
        try {
            new ScoreBoardMenu().start(stage);
            User.saveAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //VIEW
    Scene scene;
    Pane pane;
    public void start(Stage myStage) throws Exception {
        //super.start(primaryStage);
        pane = FXMLLoader.load(getClass().getResource("ScoreBoardMenu.fxml"));
        createThePane();
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("YU_GI_OH! | جدول امتیازها");
        stage = myStage;
        stage.show();
        //myStage.show();
    }
    private void createThePane() {
        addTitle();
        addScores();
        addReturnButton();
    }
    private void addReturnButton() {
        Button button = new Button("برگشتن") ;
        button.setLayoutX(225);
        button.setLayoutY(24*20+200);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (isPrimary(event)) {
                    try {
                        returnToTheMainMenu();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (isChoiceKey(event)) {
                    try {
                        returnToTheMainMenu();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        pane.getChildren().add(button);
    }
    private void returnToTheMainMenu() throws Exception {
        MainMenu.run();
    }
    private void addScores() {
        boolean hasShowedCurrentUser = false;
        ArrayList<User> users = User.getUsers();
        for (User aauser:
                users) {
            System.out.println("@" + aauser.getUsername());
        }
        User aUser;
        User user = MenuController.getLoggedInUser();
        for (int i = 0; i < 20; i++) {
            if (i >= users.size()) {
                //break;
                return;
            }
            aUser = users.get(i);
            if (aUser == user) {
                hasShowedCurrentUser = true;
                showUser(i , user , "#3f731a");
            } else {
                showUser(i , aUser , "#000000");
            }
        }
        if (!hasShowedCurrentUser) {
            showUser(21 , user , "#ba005d");
        }
    }
    private void showUser(int place, User aUser, String color) {
        String string = aUser.getRank()+" . @" +aUser.getUsername();
        String score = aUser.getHighScore()+"";
        Label userLabel = new Label( string+getSpaces(string+score)+score);
        userLabel.setFont(Font.font(18));
        userLabel.setTextFill(Color.web(color));
        userLabel.setLayoutX(20);
        userLabel.setLayoutY(100+ place*20);
        pane.getChildren().add(userLabel);
    }
    private String getSpaces(String s) {
        String string = "";
        int len = 100 - s.length();
        for (int i = 0; i < len; i++) {
            string += " ";
        }
        return string;
    }
    private void addTitle() {
        Label titleLabel = new Label("*** جدول امتیاز کاربران ***");
        titleLabel.setLayoutX(200);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(Font.font(24));
        pane.getChildren().add(titleLabel);
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
//            show("SCORE BOARD MENU");
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
//                    print("ScoreBoard Menu");
//                    break;
//                case "show":
//                    showScoreBoard();
//                    break;
//            }
//            getConfirmation();
//        }
//    }
//
//    private static void showScoreBoard() {
//        User.users.sort(new Comparator<User>() {
//            @Override
//            public int compare(User user, User t1) {
//                int c = Integer.compare(t1.getHighScore() , user.getHighScore());
//                if (c!=0) {
//                    return c;
//                } else {
//                    return String.CASE_INSENSITIVE_ORDER.compare(t1.getNickname() , user.getNickname());
//                }
//            }
//        });
//        for (User user :
//                User.users) {
//            print(user.getRank() + "- " + user.getNickname() + ": " + user.getHighScore());
//        }
//    }
//
//    private static void initializeMenu() {
//        toContinue = true;
//        Command.clearValidCommandTypes();
//        initializeExitCommandType();
//        initializeShowCurrentMenuCommandType();
//        initializeShowCommandType();
//    }
//
//    private static void initializeShowCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("scoreboard show");
//        commandType.setName("show");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeShowCurrentMenuCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("show current menu");
//        commandType.setMainPart("menu show-current");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeExitCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setMainPart("menu exit");
//        commandType.setName("exit");
//        Command.addCommandType(commandType);
//    }
}
