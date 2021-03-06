package View.Menus;

import Controller.CommandHelper.Command;
import Controller.CommandHelper.CommandType;
import Controller.Connection;
import Controller.MenuController;
import Model.Card;
import Model.User;

import java.io.FileNotFoundException;

import static View.Menus.ViewMenu.input;

public class CheatMenu extends Thread {
    @Override
    public void run() {
        initializeMenu();
        doMain();
    }

    private static void doMain() {
        Command command;
        String input;
        String typeOfCommand;
        while (true) {
            input = input();
            command = new Command(input);
            typeOfCommand = command.getType();
            switch (typeOfCommand) {
                case "add coin":
                    addCoin(command);
                    break;
                case "buy all cards":
                    buyAllCardsFree();
                    break;
                case "send to server":
                    sendToServer(command);
                default:
                    System.out.println("this command was not valid!");
                    break;
            }
        }
    }

    private static void sendToServer(Command command) {
        String notToSend = command.getField("request");
        String toSend = "";
        for (char c :
                notToSend.toCharArray()) {
            if (c != '\"') {
                toSend += c;
            }
        } Connection.sendMessageToTheServer(toSend);
    }

    private static void buyAllCardsFree() {
        try {
            for (String monsterCardName :
                    Card.getNameOfAllMonsters()) {
                MenuController.getLoggedInUser().getCards().put(Card.getAllCards().get(monsterCardName), 100);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (String spellTrapCardName :
                    Card.getNameOfAllSpellsAndTraps()) {
                MenuController.getLoggedInUser().getCards().put(Card.getAllCards().get(spellTrapCardName), 100);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addCoin(Command command) {
        String add = command.getField("add");
        String username = command.getField("username");
        User user = User.getUserByUsername(username);
        if (user != null && add.matches("\\d+")) {
            user.increaseCoin(Integer.parseInt(add));
            Connection.sendMessageToTheServer("increase coin token "+MenuController.getToken()+
                    " value "+add);
            System.out.println("success");
        }
        System.out.println("failure");
    }

    // initialization
    private void initializeMenu() {
        Command.clearValidCommandTypes();
        initializeAddCoinCommandType();
        initializeBuyAllCardsFreeCommandType();
        initializeIncreaseHealthPointCommandType();
        initializeDecreaseHealthPointCommandType();
        initializeSetAValidDeck();
        initializeSendToServerCommand();
    }

    private void initializeSendToServerCommand() {
        CommandType commandType = new CommandType();
        commandType.setName("send to server");
        commandType.setMainPart("send to server");
        commandType.addField("request");
        Command.addCommandType(commandType);
    }

    private void initializeSetAValidDeck() {
        // deck create auto
        CommandType commandType = new CommandType();
        commandType.setName("auto create deck");
        commandType.setMainPart("deck create auto");
        Command.addCommandType(commandType);
    }

    private void initializeDecreaseHealthPointCommandType() {
        // healthPoint decrease <1000>
        CommandType commandType = new CommandType();
        commandType.setName("decrease heath point");
        commandType.setMainPart("healthPoint");
        commandType.addField("decrease");
        Command.addCommandType(commandType);
    }

    private void initializeIncreaseHealthPointCommandType() {
        // healthPoint increase <1000>
        CommandType commandType = new CommandType();
        commandType.setName("increase health point");
        commandType.setMainPart("healthPoint");
        commandType.addField("increase");
        Command.addCommandType(commandType);
    }

    private void initializeBuyAllCardsFreeCommandType() {
        // card buy all
        CommandType commandType = new CommandType();
        commandType.setName("buy all cards");
        commandType.setMainPart("card buy all");
        Command.addCommandType(commandType);
    }

    private void initializeAddCoinCommandType() {
        // coin add <1000> username <username>
        CommandType commandType = new CommandType();
        commandType.setName("add coin");
        commandType.setMainPart("coin");
        commandType.addField("add");
        commandType.addField("username");
        Command.addCommandType(commandType);
    }
}

