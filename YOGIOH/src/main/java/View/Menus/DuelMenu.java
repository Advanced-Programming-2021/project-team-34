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

public class DuelMenu extends ViewMenu{
    static DuelMenu duelMenu = new DuelMenu();
    Scene scene;
    public GridPane usernameOfOpponentGridPane, roundsGridPane, resultGridPane;
    int round = 1;
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

    public void backByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            MainMenu.run();
    }

    public void backByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            MainMenu.run();
    }

    public void changeRoundsByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            changeRounds();
    }

    private void changeRounds() {
        round = (round==1 ? 3 : 1);
        message((round==1 ? "شمار دورهای بازی روی ۱ تنظیم شده است." : "شمار دورهای بازی روی ۳ تنظیم شده است." ), roundsGridPane);
    }

    public void changeRoundsByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
            changeRounds();
    }

    public void playByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent))
            play();
    }

    private void play() {
        String secondPlayer = getStringOfAGridPane(usernameOfOpponentGridPane);
        boolean success = Controller.Menus.DuelMenu.duel(secondPlayer , ""+round);;
        if (success) {
            System.out.println("success");
        } else {
            message(Controller.Menus.DuelMenu.getError() , resultGridPane);
        }
    }

    public void playByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent))
                play();
    }
}
