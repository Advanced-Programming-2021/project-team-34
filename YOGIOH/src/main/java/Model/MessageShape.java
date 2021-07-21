package Model;

import Controller.Connection;
import Controller.MenuController;
import View.Menus.ChatroomMenu;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class MessageShape {
    Circle circle;
    TextField textField;
    Message message;
    int x = 10, y = 100;
    Label usernameLabel , idLabel, replyOnLabel , deleteMessageLabel;
    static int number = 0;
    static ArrayList<MessageShape> arrayList = new ArrayList<>();
    public MessageShape(Message message) {
        this.message = message;
        textField = new TextField();
        textField.setText(message.getMessageText());
        textField.setEditable(false);
        textField.setPrefWidth(900);
        idLabel = new Label();
        idLabel.setText(message.getId() + " : id");
        usernameLabel = new Label();
        usernameLabel.setText("@"+message.getUsernameOfSender());
        circle = new Circle();
        circle.setRadius(30);
        replyOnLabel = new Label();
        if (message.getReplyOnMessage() != null) {
            replyOnLabel.setText(message.getReplyOnMessage().getId()+"پاسخ به : ");
        }
        String address = Connection.sendMessageToTheServer("" +
                "get user avatar username " + message.getUsernameOfSender());
        try {
            ImagePattern shape = new ImagePattern(
                    new Image(getClass().getResource(address).toExternalForm()));
            circle.setFill(shape);
        } catch (NullPointerException e) {
            System.out.println(address);
            System.out.println(e.getMessage());
        }
        deleteMessageLabel = new Label("حذف");
        deleteMessageLabel.setTextFill(Color.web("#f00000"));
        deleteMessageLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Connection.sendMessageToTheServer("delete message id "+message.getId()+" token "+
                        MenuController.getToken());
                ChatroomMenu.run();
            }
        });
        this.setXY();
        arrayList.add(this);
    }

    private void setXY() {
        circle.setLayoutX(x+930);circle.setLayoutY(y+20);
        usernameLabel.setLayoutX(x+700);usernameLabel.setLayoutY(y);
        replyOnLabel.setLayoutX(x+300);replyOnLabel.setLayoutY(y);
        idLabel.setLayoutX(x+80);idLabel.setLayoutY(y);
        textField.setLayoutX(x);textField.setLayoutY(y+20);
        deleteMessageLabel.setLayoutX(x);deleteMessageLabel.setLayoutY(y);
    }

    public void hide() {
        circle.setLayoutX(x+1030);circle.setLayoutY(y+1120);
        usernameLabel.setLayoutX(x+1700);usernameLabel.setLayoutY(1000+y);
        idLabel.setLayoutX(x+1000);idLabel.setLayoutY(10000+y);
        textField.setLayoutX(x+1000);textField.setLayoutY(y+120);
    }

    public void addToPane(Pane pane) {
        pane.getChildren().add(circle);
        pane.getChildren().add(idLabel);
        pane.getChildren().add(replyOnLabel);
        pane.getChildren().add(usernameLabel);
        pane.getChildren().add(textField);
        pane.getChildren().add(deleteMessageLabel);
    }

    public void setX(int x) {
        this.x = x;
        this.setXY();
    }

    public void setY(int y) {
        this.y = y;
        this.setXY();
    }

    public static void addY(int yToAdd) {
        for (MessageShape messageShape :
                arrayList) {
            messageShape.y += yToAdd;
            if (messageShape.y >= 700) {
                messageShape.hide();
            } else {
                messageShape.setXY();
            }
        }
    }
}
