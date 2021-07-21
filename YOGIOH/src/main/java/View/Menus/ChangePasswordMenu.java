package View.Menus;

import Controller.Connection;
import Controller.MenuController;
import Controller.Menus.ProfileMenu;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangePasswordMenu extends ViewMenu {
    static ChangePasswordMenu changePasswordMenu = new ChangePasswordMenu();
    Scene scene;
    public GridPane currentPasswordGridPane, newPasswordGridPane, resultGridPane;
    public static void run() {
        try {
            changePasswordMenu.start(new Stage());
            User.saveAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("ChangePasswordMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initOwner(stage);
        primaryStage.show();
    }
    public void OKByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            changePassword();
    }

    private void changePassword() {
        String result = Connection.sendMessageToTheServer("change password currentPassword "+
                getStringOfAGridPane(currentPasswordGridPane , true)+" newPassword "+
                getStringOfAGridPane(newPasswordGridPane , true)+" token "+ MenuController.getToken());
        if (result.equals("success")) {
            System.out.println("success");
        } else {
            System.out.println(result);
        }
    }

    public void OKByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            changePassword();
    }
}
