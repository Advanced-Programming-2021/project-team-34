package Model;

import Exceptions.NoSpellTrapWithThisNameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellAndTrap extends Card {
    boolean itIsSpellAndNotTrap;
    String spellAndTrapType;
    String icon;
    String description;
    String status;
    int price = 0;

    public SpellAndTrap(String name) throws IOException {
        super(name);
        String text = "";
        File file = new File("src/main/resources/SpellTrap.csv");
        Scanner scanner = new Scanner(file);
        boolean found = false;
        while (scanner.hasNextLine()) {
            text = scanner.nextLine();
            Pattern pattern = Pattern.compile(name + ",(\\S+),(\\S+),(.+),(\\S+),(\\d+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                found = true;
                spellAndTrapType = (matcher.group(1));
                if (spellAndTrapType .equals("Trap")) this.typeOfCard = TypeOfCard.TRAP; else this.typeOfCard = TypeOfCard.SPELL;
                icon = matcher.group(2);
                description = (matcher.group(3));
                status = (matcher.group(4));
                price = (Integer.parseInt(matcher.group(5)));
            }
        }
        if (!found) {
            throw new NoSpellTrapWithThisNameException(name);
        }
    }

    public String getSpellAndTrapType() {
        return spellAndTrapType;
    }

    public boolean isSpell() {
        return itIsSpellAndNotTrap;
    }
    public boolean isTrap() {
        return !itIsSpellAndNotTrap;
    }

    public boolean isItIsSpellAndNotTrap() {
        return itIsSpellAndNotTrap;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
