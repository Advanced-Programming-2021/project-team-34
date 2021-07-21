package View.Menus;

import Controller.Connection;
import Controller.MenuController;
import Model.Message;
import Model.MessageShape;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatroomMenu extends ViewMenu {
    static ChatroomMenu chatroomMenu = new ChatroomMenu();
    Scene scene;
    @FXML
    Pane pane;
    @FXML
    Circle sendButton, backButton;

    @FXML
    TextField messageInputTextField, idToReplyTextField;
    public static void run() {
        try {
            chatroomMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("ChatroomMenu.fxml"));
        scene = new Scene(pane);
        initializer();
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }

    private void initializer() {
        String messagesStringOfArrayList = Connection.sendMessageToTheServer("get messages");
        System.out.println("messagesStringOfArrayList : {\n"+messagesStringOfArrayList+"\n}");
        Message.setMessages(new ArrayList<>());
        ArrayList<Message> messageArrayList = new Gson().fromJson(messagesStringOfArrayList ,
                new TypeToken<List<Message>>(){}.getType());
        Message.setMessages(messageArrayList);
        System.out.println("Messages.getMessages()");
        int i = 1;
        for (Message message :
                Message.getMessages()) {
            MessageShape messageShape = new MessageShape(message);
            messageShape.setY(i*80);
            messageShape.addToPane(pane);
            i++;
        }
        setOnDragAction(pane);
    }
    @FXML
    public void initialize() {
        try {
            ImagePattern shape = new ImagePattern(
                    new Image(getClass().getResource(
                            "/Images/Buttons/rightArrowButton.png").toExternalForm()));
            sendButton.setFill(shape);
            backButton.setFill(shape);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            send();
    }

    private void send() {
        String messageText = messageInputTextField.getText();
        String idToReplyString = idToReplyTextField.getText();
        if (idToReplyString.equals("")) {
            String result = Connection.sendMessageToTheServer("send message token " + MenuController.getToken() +
                    " message \"" + messageText + "\"");
        } else {
            Connection.sendMessageToTheServer("send message token " +MenuController.getToken()+
                    " message \""+messageText+"\" replyOn "+idToReplyString);
        }
        run();
    }

    public void backByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            MainMenu.run();
    }

    private <T extends Node> void setOnDragAction(T o) {
        o.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                MessageShape.addY((int) event.getDeltaY());
            }
        });
    }
}
