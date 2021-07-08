package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DuelMenu extends ViewMenu{
    static DuelMenu duelMenu = new DuelMenu();
    Scene scene;
    public static void run() {
        try {
            duelMenu.start(stage);
        } catch (Exception e) {
            MenuController.setMenuName(MenuNames.Exit);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("DuelMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }
}
