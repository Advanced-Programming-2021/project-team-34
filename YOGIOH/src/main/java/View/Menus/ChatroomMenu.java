package View.Menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChatroomMenu extends ViewMenu {
    static ChatroomMenu chatroomMenu = new ChatroomMenu();
    Scene scene;
    public static void run() {
        try {
            chatroomMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = FXMLLoader.load(getClass().getResource("ChatroomMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }
}
