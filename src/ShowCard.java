public class ShowCard {
    public static void showSelectedCard(String cardName) {
        if (!Card.allCards.containsKey(cardName)) System.out.println("there is no card with this name");
        else{
            if (Card.allCards.get(cardName).getType().equals("monster")){
                Monster monster = (Monster) Card.allCards.get(cardName);
                System.out.println("Name: " + cardName);
                System.out.println("Level: " + monster.getLevel());
                System.out.println("Type: " + monster.getType());
                System.out.println("ATK: " + monster.getDefAttPower());
                System.out.println("DEF: " + monster.getDefDefPower());
                System.out.println("Description: " + monster.getDescription());
            }
            else{
                SpellAndTrap spellAndTrap = (SpellAndTrap) Card.allCards.get(cardName);
                UserInterface.printResponse("Name: " + cardName);
                if (spellAndTrap.gettype == "spell") UserInterface.printResponse("Spell");
                else UserInterface.printResponse("Trap");
                UserInterface.printResponse("Type: " + spellAndTrap.getIcon());
                UserInterface.printResponse("Description: " + spellAndTrap.getDescription());
            }
        }
    }
}
