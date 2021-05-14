import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowCard {
    public static void showSelectedCard(String inputCommand) {
        Pattern pattern = Pattern.compile("^card show (.*)$");
        Matcher matcher = pattern.matcher(inputCommand);

        if (matcher.find()) {

            String cardName = matcher.group(1);

            if (!Card.allCards.containsKey(cardName)) System.out.println("there is no card with this name");
            else {
                if (Card.allCards.get(cardName).getType().equals("monster")) {
                    Monster monster = (Monster) Card.allCards.get(cardName);
                    System.out.println("Name: " + cardName);
                    System.out.println("Level: " + monster.getLevel());
                    System.out.println("Type: " + monster.getType());
                    System.out.println("ATK: " + monster.getDefAttPower());
                    System.out.println("DEF: " + monster.getDefDefPower());
                    System.out.println("Description: " + monster.getDescription());
                } else {
                    SpellAndTrap spellAndTrap = (SpellAndTrap) Card.allCards.get(cardName);
                    System.out.println("Name: " + cardName);
                    if (spellAndTrap.getType().equals("spell")) System.out.println("Spell");
                    else System.out.println("Trap");
                    System.out.println("Type: " + spellAndTrap.getIcon());
                    System.out.println("Description: " + spellAndTrap.getDescription());
                }
            }
        }
        else System.out.println("invalid command");
    }
}
