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
                System.out.println("Name: " + cardName);
                if (spellAndTrap.getType() == "spell") System.out.println("Spell");
                else System.out.println("Trap");
                System.out.println("Type: " + spellAndTrap.getIcon());
                System.out.println("Description: " + spellAndTrap.getDescription());
            }
        }
    }
}
