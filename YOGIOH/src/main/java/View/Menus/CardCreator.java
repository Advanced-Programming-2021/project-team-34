package View.Menus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CardCreator extends ViewMenu {
    static CardCreator cardCreator = new CardCreator();
    Scene scene;
    @FXML
    TextField nameTextField, descriptionTextField, priceTextField, attPowerTextField, defPowerTextField,
            levelTextField, attributeTextField, monsterTypeTextFiled, cardTypeTextField, resultTextField;
    public static void run() {
        try {
            cardCreator.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("CardCreator.fxml"));
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

    public void createByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            create();
    }

    private void create() {
        String answer = "";
        answer += nameTextField.getText()+",";
        answer += levelTextField.getText()+",";
        answer += attributeTextField.getText()+",";
        answer += monsterTypeTextFiled.getText()+",";
        answer += cardTypeTextField.getText()+",";
        answer += attPowerTextField.getText()+",";
        answer += defPowerTextField.getText()+",";
        answer += descriptionTextField.getText()+",";
        answer += priceTextField.getText();
        resultTextField.setText(answer);
    }
}
