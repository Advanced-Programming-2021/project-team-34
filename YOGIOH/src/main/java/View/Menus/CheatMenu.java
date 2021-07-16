package View.Menus;

import Controller.MenuController;
import Model.Card;
import Model.Monster;
import Model.SpellAndTrap;
import Model.User;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

import java.io.FileNotFoundException;
import java.io.IOException;

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
                default:
                    System.out.println("this command was not valid!");
                    break;
            }
        }
    }

    private static void buyAllCardsFree() {
        try {
            for (String monsterCardName :
                    Card.getNameOfAllMonsters()) {
                try {
                    MenuController.getLoggedInUser().addCard(new Monster(monsterCardName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (String spellTrapCardName :
                    Card.getNameOfAllSpellsAndTraps()) {
                try {
                    MenuController.getLoggedInUser().addCard(new SpellAndTrap(spellTrapCardName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

