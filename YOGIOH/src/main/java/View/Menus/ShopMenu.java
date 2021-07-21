package View.Menus;

import Controller.Connection;
import Controller.MenuController;
import Controller.MenuNames;
import Exceptions.NoMonsterWithThisNameException;
import Model.Card;
import Model.Monster;
import Model.SpellAndTrap;
import Model.User;
import com.google.gson.Gson;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ShopMenu extends ViewMenu {
    static ShopMenu shopMenu = new ShopMenu();
    Scene scene;
    ArrayList<String> nameOfCards = null , nameOfCards2 = null;
    static String selectedCardName = "";
    @FXML
    GridPane monsterCardsGridPane, spellTrapCardsGridPane, resultGridPane, moneyGridPane, propertiesGridPane;
    @FXML
    Rectangle selectedCardRectangle;
    public static void run() {
        try {
            shopMenu.start(stage);
            User.saveAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = FXMLLoader.load(getClass().getResource("ShopMenu.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YU_GI_OH!");
        stage = primaryStage;
        stage.show();
    }
    @FXML
    private void initialize() {
        message("پول : "+MenuController.getLoggedInUser().getCoin() , moneyGridPane);
        // drawing monsters
        try {
            nameOfCards = Card.getNameOfAllMonsters();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lenOfAll = nameOfCards.size();
        int rows = lenOfAll/15+1;
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < 15; i++) {
                int index = 15*j+i;
                if (index >= lenOfAll) break;
                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(80);
                rectangle.setHeight(100);
                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (isPrimary(event)){
                            clickHandlerOnCards((nameOfCards.get(index)));
                        }
                    }
                });
                try {
                    ImagePattern shape = new ImagePattern(new Image(getClass().getResource(
                            "/Images/Cards/Monsters/" + getStringWithoutSpace(nameOfCards.get(index)) + ".jpg").toExternalForm()));
                    rectangle.setFill(shape);
                } catch (Exception e) {
                    print("Here is an error! --> cardName : "+nameOfCards.get(index));
                }
                monsterCardsGridPane.add(rectangle, i, j);
            }
        }

        // drawing spells and traps
        try {
            nameOfCards2 = Card.getNameOfAllSpellsAndTraps();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lenOfAll2 = nameOfCards2.size();
        int rows2 = lenOfAll2/15+1;
        for (int j = 0; j < rows2; j++) {
            for (int i = 0; i < 15; i++) {
                int index = 15*j+i;
                if (index >= lenOfAll2) break;
                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(80);
                rectangle.setHeight(100);
                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (isPrimary(event)){
                            clickHandlerOnCards((nameOfCards2.get(index)));
                        }
                    }
                });
                try {
                    ImagePattern shape = new ImagePattern(new Image(getClass().getResource(
                            "/Images/Cards/SpellTrap/" + getStringWithoutSpace(nameOfCards2.get(index)) + ".jpg").toExternalForm()));
                    rectangle.setFill(shape);
                } catch (Exception e) {
                    print("Here is an error! --> cardName : "+nameOfCards2.get(index));
                }
                spellTrapCardsGridPane.add(rectangle, i, j);
            }
        }

    }

    private void clickHandlerOnCards(String s) {
        selectedCardName = s;
        try {
            message(new Monster(s).getPrice()+"" , propertiesGridPane);
        } catch (NoMonsterWithThisNameException e) {
            try {
                message(new SpellAndTrap(s).getPrice()+"" , propertiesGridPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ImagePattern shape = new ImagePattern(new Image(getClass().getResource(
                    "/Images/Cards/Monsters/" + getStringWithoutSpace(s)+ ".jpg").toExternalForm()));
            selectedCardRectangle.setFill(shape);
        } catch (Exception e) {
            try {
                ImagePattern shape = new ImagePattern(new Image(getClass().getResource(
                        "/Images/Cards/SpellTrap/" + getStringWithoutSpace(s)+ ".jpg").toExternalForm()));
                selectedCardRectangle.setFill(shape);
            } catch (Exception e1) {
                print("Here is an error! --> cardName : "+getStringWithoutSpace(s));
            }
        }
    }

    public void backByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            back();
        }
    }

    private void back() {
        MainMenu.run();
    }

    public void backByKey(KeyEvent keyEvent) {
        back();
    }

    private static ArrayList<String> getArrayWithoutSpace(ArrayList<String> stringsArrayList) {
        ArrayList<String> answer = new ArrayList<>();
        for (String string :
                stringsArrayList) {
            answer.add(getStringWithoutSpace(string));
        }return answer;
    }

    private static String getStringWithoutSpace(String string) {
        String[] strings = string.split("\\s+");
        String answer = "";
        for (String str :
                strings) {
            answer += str;
        }return answer;
    }

    public void buyByMouse(MouseEvent mouseEvent) {
        buy();
    }

    private void buy() {
        String result = Connection.sendMessageToTheServer("buy card cardName "+selectedCardName+" token "+
                MenuController.getToken());
        if (result.equals("success")) {
            message("کارت پیروزمندانه خریده شد" , resultGridPane);
            MenuController.setLoggedInUser(new Gson().fromJson(Connection.sendMessageToTheServer("get" +
                    " user info token "+MenuController.getToken()) , User.class));
            message("پول : "+MenuController.getLoggedInUser().getCoin() , moneyGridPane);
        } else {
            message(result , resultGridPane);
        }
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
//            show("SHOP MENU");
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
//                    print("Shop Menu");
//                    break;
//                case "show all":
//                    showAll();
//                    break;
//                case "buy card":
//                    buy(myCommand);
//                    break;
//            }
//            getConfirmation();
//        }
//    }
//
//    private static void buy(Command command) {
//        String nameOfCardToBuy = command.getField("buy");
//        boolean success = Controller.Menus.ShopMenu.buyCard(nameOfCardToBuy);
//        if (success) {
//            print("card bought successfully");
//        } else {
//            print(Controller.Menus.ShopMenu.getError());
//        }
//
//    }
//
//    private static void showAll() {
//        // TODO : @aliagha do this
//    }
//
//    private static void initializeMenu() {
//        toContinue = true;
//        Command.clearValidCommandTypes();
//        initializeExitCommandType();
//        initializeShowCurrentMenuCommandType();
//        initializeBuyCardCommandType();
//        initializeShowAllCommandType();
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
//
//    private static void initializeShowAllCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("show all");
//        commandType.setMainPart("shop show all");
//        Command.addCommandType(commandType);
//    }
//
//    private static void initializeBuyCardCommandType() {
//        CommandType commandType = new CommandType();
//        commandType.setName("buy card");
//        commandType.setMainPart("shop");
//        commandType.getField("buy");
//        Command.addCommandType(commandType);
//    }
//

}
