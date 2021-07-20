package View.Menus;

import Controller.Connection;
import Controller.MenuController;
import Model.GameRequest;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.javafx.css.StyleClassSet;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class LobbyMenu extends ViewMenu {
    static GameRequest ourRequest;
    private static final int textY = 50, buttonsY = 140;
    static LobbyMenu lobbyMenu = new LobbyMenu();
    private Scene scene;
    Pane pane;
    public static void run() {
        try {
            User.users = new Gson().fromJson(Connection.sendMessageToTheServer("get all users") ,
                    new TypeToken<List<User>>(){}.getType());
            User user = new Gson().fromJson(Connection.sendMessageToTheServer("get user info token "+
                            MenuController.getToken()) ,
                    User.class);
            MenuController.setLoggedInUser(user);
            ourRequest = MenuController.getLoggedInUser().getGameRequest();
            lobbyMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("LobbyMenu.fxml"));
        initializer();
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }

    private void initializer() {
        if (ourRequest.equals(GameRequest.NO)) {
            Label label = new Label("شــمــا درخـــواســت فـعّــالی ندارید");
            label.setLayoutX((pane.getWidth()-label.getWidth())/2);
            label.setLayoutY(textY);
            pane.getChildren().add(label);
            Button backButton = new Button("برگشتن");
            backButton.setLayoutX(25);backButton.setLayoutY(buttonsY);
            backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isPrimary(event))
                        MainMenu.run();
                }
            });
            Button oneRoundRequestButton = new Button("۱ دوری");
            oneRoundRequestButton.setLayoutX(300);oneRoundRequestButton.setLayoutY(buttonsY);
            oneRoundRequestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isPrimary(event))
                        requestGame(1);
                }
            });
            Button threeRoundRequestButton = new Button("۳ دوری");
            threeRoundRequestButton.setLayoutX(575);threeRoundRequestButton.setLayoutY(buttonsY);
            threeRoundRequestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isPrimary(event))
                        requestGame(3);
                }
            });
            pane.getChildren().add(backButton);
            pane.getChildren().add(oneRoundRequestButton);
            pane.getChildren().add(threeRoundRequestButton);
        }
        else {
            Label label = new Label((ourRequest.equals(GameRequest.ONE_ROUND)) ? ("شــمــا درخــواست بـازی‌ای ۱ دوری داریــد") :
                    ("شــمــا درخــواست بـازی‌ای ۳ دوری داریــد"));
            label.setLayoutX((pane.getWidth()-label.getWidth())/2);
            label.setLayoutY(50);
            pane.getChildren().add(label);
            Button backButton = new Button("برگشتن");
            backButton.setLayoutX(100);backButton.setLayoutY(buttonsY);
            backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isPrimary(event))
                        MainMenu.run();
                }
            });
            Button cancelRequestButton = new Button("پـس گـرفتن");
            cancelRequestButton.setLayoutX(500);cancelRequestButton.setLayoutY(buttonsY);
            cancelRequestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isPrimary(event))
                        cancelRequest();
                }
            });
            pane.getChildren().add(backButton);
            pane.getChildren().add(cancelRequestButton);
        }
    }

    private void cancelRequest() {
        String result = Connection.sendMessageToTheServer("request game delete token "+
                MenuController.getToken());
        if (result.equals("success")) {
            ourRequest = GameRequest.NO;
        }
        lobbyMenu = new LobbyMenu();
        run();
    }

    private void requestGame(int numberOfRounds) {
        String result = Connection.sendMessageToTheServer("request game round "+numberOfRounds+" token "+
                MenuController.getToken());
        if (result.equals("success")) {
            ourRequest = (numberOfRounds == 1) ? GameRequest.ONE_ROUND : GameRequest.THREE_ROUND;
            //TODO : create a thread which listens if server send a message show somebody else has a same request.
        }
        print(result);
        lobbyMenu = new LobbyMenu();
        run();
    }
}
