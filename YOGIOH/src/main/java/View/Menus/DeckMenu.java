package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import Model.*;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

import java.util.ArrayList;
import java.util.Comparator;

public class DeckMenu extends ViewMenu {
    static boolean toContinue = true;

    public static void run() {
        initializeMenu();
        doMain();
    }

    private static void doMain() {
        String input;
        Command myCommand;
        String typeOfMyCommand;
        while (toContinue) {
            show("DECK MENU");
            input = input();
            myCommand = new Command(input);
            typeOfMyCommand = myCommand.getType();
            switch (typeOfMyCommand) {
                case "invalid type":
                    print("The command you inputted is not valid in this menu!!!");
                    break;
                case "exit":
                    MenuController.setMenuName(MenuNames.MainMenu);
                    toContinue = false;
                    break;
                case "show current menu":
                    print("Deck Menu");
                    break;
                case "create deck":
                    createDeck(myCommand);
                    break;
                case "delete deck":
                    deleteDeck(myCommand);
                    break;
                case "set active deck":
                    activateDeck(myCommand);
                    break;
                case "add card":
                    addCardToMainDeck(myCommand);
                    break;
                case "add card to side deck":
                    addCardToSideDeck(myCommand);
                    break;
                case "remove card from side deck":
                    removeCardFromSideDeck(myCommand);
                    break;
                case "remove card from deck":
                    removeCardFromMainDeck(myCommand);
                    break;
                case "show all decks":
                    showAllDecks();
                    break;
                case "show one side deck":
                    showOneSideDeck(myCommand);
                    break;
                case "show one deck":
                    showOneMainDeck(myCommand);
                    break;
                case "deck show cards":
                    showBoughtCard();
                    break;

            }
            getConfirmation();
        }
    }

    private static void showBoughtCard() {
        User user = Controller.MenuController.getLoggedInUser();
        user.getCards().sort(new Comparator<Card>() {
            @Override
            public int compare(Card card, Card t1) {
                return String.CASE_INSENSITIVE_ORDER.compare(card.getName(), t1.getName());
            }
        });
        for (Card card :
                user.getCards()) {
            print(card.getName() + " : " + card.getDescription());
        }
    }

    private static void showOneMainDeck(Command command) {
        String deckName = command.getField("deck-name");
        Deck deck = Controller.MenuController.getLoggedInUser().getDeckByName(deckName);
        if (deck == null) {
            print("deck with name " + deckName + " does not exist");
        } else {
            deck.sortCards();
            print("Deck: " + deckName);
            print("Main deck:");
            print("Monsters:");
            for (Card card :
                    deck.getCardsInMainDeck()) {
                if (card.getTypeOfCard().equals(TypeOfCard.MONSTER)) {
                    print(card.getName() + " : " + card.getDescription());
                }
            }
            print("Spell and Traps:");
            for (Card card :
                    deck.getCardsInMainDeck()) {
                if (card.getTypeOfCard().equals(TypeOfCard.SPELL) || card.getTypeOfCard().equals(TypeOfCard.TRAP)) {
                    print(card.getName() + " : " + card.getDescription());
                }
            }
        }
    }

    private static void showOneSideDeck(Command command) {
        String deckName = command.getField("deck-name");
        Deck deck = Controller.MenuController.getLoggedInUser().getDeckByName(deckName);
        if (deck == null) {
            print("deck with name " + deckName + " does not exist");
        } else {
            deck.sortCards();
            print("Deck: " + deckName);
            print("Side deck:");
            print("Monsters:");
            for (Card card :
                    deck.getCardsInSideDeck()) {
                if (card.getTypeOfCard().equals(TypeOfCard.MONSTER)) {
                    print(card.getName() + " : " + card.getDescription());
                }
            }
            print("Spell and Traps:");
            for (Card card :
                    deck.getCardsInSideDeck()) {
                if (card.getTypeOfCard().equals(TypeOfCard.SPELL) || card.getTypeOfCard().equals(TypeOfCard.TRAP)) {
                    print(card.getName() + " : " + card.getDescription());
                }
            }
        }
    }

    private static void showAllDecks() {
        User user = MenuController.getLoggedInUser();
        print("Decks :");
        print("Active Deck :");
        Deck activeDeck = user.getActiveDeck();
        if (activeDeck != null) {
            print(activeDeck.getName() +
            ": main deck " + activeDeck.getNumberOfCardsInMainDeck() +
            ", side deck " + activeDeck.getNumberOfCardsInSideDeck() +
            ", " + (activeDeck.isValid() ? "valid" : "invalid"));
        }
        print("Other Decks:");
        ArrayList<Deck> allDecks = user.getDecks();
        allDecks.sort(Comparator.comparing(Deck::getName));
        if (allDecks.size() > 1) {
            for (Deck deck : allDecks) {
                if (deck != activeDeck) {
                    print(deck.getName() +
                    ": main deck " + deck.getNumberOfCardsInMainDeck() +
                    ", side deck " + deck.getNumberOfCardsInSideDeck() +
                    ", " + (deck.isValid() ? "valid" : "invalid"));
                }
            }
        }
    }

    private static void removeCardFromMainDeck(Command command) {
        String cardName = command.getField("card");
        String deckName = command.getField("deck");
        boolean success = Controller.Menus.DeckMenu.removeCardFromMainDeck(deckName, cardName);
        if (success) {
            print("card removed from main deck successfully");
        } else {
            print(Controller.Menus.DeckMenu.getError());
        }
    }

    private static void removeCardFromSideDeck(Command command) {
        String cardName = command.getField("card");
        String deckName = command.getField("deck");
        boolean success = Controller.Menus.DeckMenu.removeCardFromSideDeck(deckName, cardName);
        if (success) {
            print("card removed from side deck successfully");
        } else {
            print(Controller.Menus.DeckMenu.getError());
        }
    }

    private static void addCardToSideDeck(Command command) {
        String cardName = command.getField("card");
        String deckName = command.getField("deck");
        boolean success = Controller.Menus.DeckMenu.addCardToSideDeck(deckName, cardName);
        if (success) {
            print("card added to side deck successfully");
        } else {
            print(Controller.Menus.DeckMenu.getError());
        }
    }

    private static void addCardToMainDeck(Command command) {
        String cardName = command.getField("card");
        String deckName = command.getField("deck");
        boolean success = Controller.Menus.DeckMenu.addCardToMainDeck(deckName, cardName);
        if (success) {
            print("card added to deck successfully");
        } else {
            print(Controller.Menus.DeckMenu.getError());
        }
    }

    private static void activateDeck(Command command) {
        String deckName = command.getField("set-activate");
        boolean success = Controller.Menus.DeckMenu.activateDeck(deckName);
        if (success) {
            print("deck activated successfully");
        } else {
            print(Controller.Menus.DeckMenu.getError());
        }
    }

    private static void deleteDeck(Command command) {
        String deckName = command.getField("delete");
        boolean success = Controller.Menus.DeckMenu.deleteDeck(deckName);
        if (success) {
            print("deck deleted successfully");
        } else {
            print(Controller.Menus.DeckMenu.getError());
        }
    }

    private static void createDeck(Command command) {
        String deckName = command.getField("create");
        boolean success = Controller.Menus.DeckMenu.createDeck(deckName);
        if (success) {
            print("deck created successfully");
        } else {
            print(Controller.Menus.DeckMenu.getError());
        }
    }

    // methods
    public static void showCard(Monster monster) {
        print("Name : " + monster.getName());
        print("Level : " + monster.getLevel());
        print("Type : " + monster.getMonsterType());
        print("ATK : " + monster.getDefAttPower());
        print("DEF : " + monster.getDefDefPower());
        print("Description : " + monster.getDescription());
    }

    public static void showCard(SpellAndTrap spellAndTrap) {
        print("Name : " + spellAndTrap.getName());
        print((spellAndTrap.isSpell()) ? ("Spell") : ("Trap"));
        print("Type : " + spellAndTrap.getSpellAndTrapType());
        print("Description : " + spellAndTrap.getDescription());
    }

    // initialize
    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeExitCommandType();
        initializeShowCurrentMenuCommandType();
        initializeDeckCreateCommandType();
        initializeDeckDeleteCommandType();
        initializeDeckSetActiveCommandType();
        initializeDeckAddCardCommandTypeToSideDeck();
        initializeDeckAddCardCommandType();
        initializeDeckRemoveCardFromSideDeck();
        initializeDeckRemoveCardFromMainDeck();
        initializeDeckShowAllCommandType();
        initializeDeckShowOneSideDeckCommandType();
        initializeDeckShowOneDeckCommandType();
        initializeDeckShowCardsCommandType();
        initializeShowCardCommandType();
    }

    private static void initializeShowCardCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("card");
        commandType.addField("show");
        commandType.setName("show card");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckShowCardsCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("deck show cards");
        commandType.setName("show cards");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckShowOneDeckCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show one deck");
        commandType.setMainPart("deck show");
        commandType.addField("deck-name");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckShowOneSideDeckCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show one side deck");
        commandType.setMainPart("deck show");
        commandType.addField("deck-name");
        commandType.addField("side");
        commandType.getField("side").setDoesHaveArgument(false);
        Command.addCommandType(commandType);
    }

    private static void initializeDeckShowAllCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show all decks");
        commandType.setMainPart("deck show all");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckRemoveCardFromMainDeck() {
        CommandType commandType = new CommandType();
        commandType.setName("remove card from deck");
        commandType.setMainPart("deck rm-card");
        commandType.addField("card");
        commandType.addField("deck");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckRemoveCardFromSideDeck() {
        CommandType commandType = new CommandType();
        commandType.setName("remove card from side deck");
        commandType.setMainPart("deck rm-card");
        commandType.addField("card");
        commandType.addField("deck");
        commandType.addField("side");
        commandType.getField("side").setDoesHaveArgument(false);
        Command.addCommandType(commandType);
    }

    private static void initializeDeckAddCardCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("add card");
        commandType.setMainPart("deck add-card");
        commandType.addField("card");
        commandType.addField("deck");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckAddCardCommandTypeToSideDeck() {
        CommandType commandType = new CommandType();
        commandType.setName("add card to side deck");
        commandType.setMainPart("deck add-card");
        commandType.addField("card");
        commandType.addField("deck");
        commandType.addField("side");
        commandType.getField("side").setDoesHaveArgument(false);
        Command.addCommandType(commandType);
    }


    private static void initializeDeckSetActiveCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("set active deck");
        commandType.setMainPart("deck");
        commandType.addField("set-active");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckDeleteCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("delete deck");
        commandType.setMainPart("deck");
        commandType.addField("delete");
        Command.addCommandType(commandType);
    }

    private static void initializeDeckCreateCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("deck");
        commandType.setName("create deck");
        commandType.addField("create");
        Command.addCommandType(commandType);
    }

    private static void initializeShowCurrentMenuCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show current menu");
        commandType.setMainPart("menu show-current");
        Command.addCommandType(commandType);
    }

    private static void initializeExitCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("exit");
        commandType.setMainPart("menu exit");
        Command.addCommandType(commandType);
    }
}
