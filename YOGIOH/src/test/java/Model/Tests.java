package Model;

import Exceptions.NoMonsterWithThisNameException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    @Test
    public void test1User() {
        User user1 = new User("mahdita" , "123456" , "mahdi");
        User user2 = new User("aliRZ888" , "1234" , "aliAgha");
        User user3 = new User("user3" , "333" , "a Simple User");
        User user4 = new User("aliRezA" , "*******" , "ali reza");
        user1.setHighScore(10000);
        user2.setHighScore(10000);
        user3.setHighScore(2000);
        user3.setHighScore(2300);
        user3.setHighScore(3000);
        user3.setHighScore(2500);
        user4.setHighScore(0);
        assertTrue(User.checkPassword("mahdita" , "123456"));
        assertFalse(User.checkPassword("mahdita" , "12345"));
        assertFalse(User.checkPassword("mahdi" , "123456"));
        assertEquals(1, user1.getRank());
        assertEquals(1, user2.getRank());
        assertEquals(3000, user3.getHighScore());
        assertEquals(3, user3.getRank());
        assertTrue(user3.changePassword("333" , "4444"));
        assertTrue(User.checkPassword(user3.getUsername() , "4444"));
    }

    @Test
    public void test2User() {
        User user1 = new User("mahdi" , "1234" , "mhd");
        User user2 = new User("aliRZ888" , "888" , "aliAgha");
        assertEquals("mahdi" , user1.getUsername());
    }

    @Test
    public void test1Monster() {
        Monster monster1 = null;
        try {
            monster1 = new Monster("Yomi Ship");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(800 , monster1.getDefAttPower());
        assertEquals("Effect" , monster1.getType());
        assertEquals("Aqua" , monster1.getMonsterType());
        assertEquals("WATER" , monster1.getAttribute());
    }
    @Test
    public void test1Deck() {
        Monster monster1 , monster2 , monster3 , monster4 , monster5 , monster6 , monster7 ,
                monster8 , monster9 , monster10, monster11, monster12, monster13, monster14,
                monster15, monster16, monster17, monster18, monster19, monster20, monster21;
        try {
            monster1 = new Monster("Battle OX");
            monster2 = new Monster("Axe Raider");
            monster3 = new Monster("Yomi Ship");
            monster4 = new Monster("Horn Imp");
            monster5 = new Monster("Silver Fang");
            monster6 = new Monster("Suijin");
            monster7 = new Monster("Fireyarou");
            monster8 = new Monster("Curtain of the dark ones");
            monster9 = new Monster("Feral Imp");
            monster10 = new Monster("Dark magician");
            monster11 = new Monster("Wattkid");
            monster12 = new Monster("Baby dragon");
            monster13 = new Monster("Hero of the east");
            monster14 = new Monster("Battle warrior");

            assertEquals("Beast-Warrior" , monster1.getMonsterType());
            assertNotNull(monster2);assertNotNull(monster3);assertNotNull(monster4);assertNotNull(monster5);
            assertNotNull(monster6);assertNotNull(monster7);assertNotNull(monster8);assertNotNull(monster9);
            assertNotNull(monster10);assertNotNull(monster11);assertNotNull(monster12);
            assertNotNull(monster13);assertNotNull(monster14);

            assertEquals(4 , monster1.getLevel());
            assertEquals(4 , monster2.getLevel());
            assertEquals(3 , monster3.getLevel());
            assertEquals(4 , monster4.getLevel());

            assertEquals("EARHT" , monster1.getAttribute());
            assertEquals("EARHT" , monster2.getAttribute());
            assertEquals("WATER" , monster3.getAttribute());
            assertEquals("DARK" , monster4.getAttribute());

            assertEquals("Beast-Warrior" , monster1.getMonsterType());
            assertEquals("Warrior" , monster2.getMonsterType());
            assertEquals("Aqua" , monster3.getMonsterType());
            assertEquals("Fiend" , monster4.getMonsterType());
            assertEquals("Beast" , monster5.getMonsterType());

            assertEquals("Normal" , monster1.getType());
            assertEquals("Normal" , monster2.getType());
            assertEquals("Effect" , monster3.getType());
            assertEquals("Normal" , monster4.getType());

            assertEquals(1700 , monster1.getDefAttPower());
            assertEquals(1700 , monster2.getDefAttPower());
            assertEquals(800 , monster3.getDefAttPower());
            assertEquals(1300 , monster4.getDefAttPower());

            assertEquals(1000 , monster1.getDefDefPower());
            assertEquals(1150 , monster2.getDefDefPower());
            assertEquals(1400 , monster3.getDefDefPower());
            assertEquals(1000 , monster4.getDefDefPower());

            assertEquals("\"A monster with tremendous power, it destroys enemies with a swing of its axe.\"",
                    monster1.getDescription());
            assertEquals("An axe-wielding monster of tremendous strength and agility.",
                    monster2.getDescription());
            assertEquals("If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.",
                    monster3.getDescription());
            assertEquals("\"A small fiend that dwells in the dark, its single horn makes it a formidable opponent.\"",
                    monster4.getDescription());

            assertEquals(2900 , monster1.getPrice());
            assertEquals(3100 , monster2.getPrice());
            assertEquals(1700 , monster3.getPrice());
            assertEquals(2500 , monster4.getPrice());
            assertEquals(1700 , monster5.getPrice());

            Deck deck = new Deck();
            deck.addCardToMainDeck(monster1);
            deck.addCardToMainDeck(monster2);
            deck.addCardToMainDeck(monster3);
            deck.addCardToMainDeck(monster4);
            deck.addCardToMainDeck(monster5);
            deck.addCardToMainDeck(monster6);
            deck.addCardToMainDeck(monster7);
            deck.addCardToMainDeck(monster8);
            deck.addCardToMainDeck(monster9);
            deck.addCardToMainDeck(monster10);
            deck.addCardToMainDeck(monster11);
            deck.addCardToMainDeck(monster12);
            deck.addCardToMainDeck(monster13);
            deck.addCardToMainDeck(monster14);

            assertFalse(deck.isValid());

            deck.addCardToMainDeck(monster1);
            deck.addCardToMainDeck(monster2);
            deck.addCardToMainDeck(monster3);
            deck.addCardToMainDeck(monster4);
            deck.addCardToMainDeck(monster5);
            deck.addCardToMainDeck(monster6);
            deck.addCardToMainDeck(monster7);
            deck.addCardToMainDeck(monster8);
            deck.addCardToMainDeck(monster9);
            deck.addCardToMainDeck(monster10);
            deck.addCardToMainDeck(monster11);
            deck.addCardToMainDeck(monster12);
            deck.addCardToMainDeck(monster13);
            deck.addCardToMainDeck(monster14);

            deck.addCardToMainDeck(monster1);
            deck.addCardToMainDeck(monster2);
            deck.addCardToMainDeck(monster3);
            deck.addCardToMainDeck(monster4);
            deck.addCardToMainDeck(monster5);
            deck.addCardToMainDeck(monster6);
            deck.addCardToMainDeck(monster7);
            deck.addCardToMainDeck(monster8);
            deck.addCardToMainDeck(monster9);
            deck.addCardToMainDeck(monster10);
            deck.addCardToMainDeck(monster11);
            deck.addCardToMainDeck(monster12);
            deck.addCardToMainDeck(monster13);
            deck.addCardToMainDeck(monster14);

            assertFalse(deck.isValid());

        } catch (Exception e) {
            System.out.println("Here is an error : \n"+e.getMessage());
        }
    }

    @Test
    public void test2Deck() {
        String[] monsterNames = {"Battle OX" , "Axe Raider" , "Yomi Ship" , "Horn Imp" , "Silver Fang" ,
                "Suijin" , "Fireyarou" , "Curtain of the dark ones" , "Feral Imp" , "Dark magician" ,
                "Wattkid" , "Baby dragon" , "Hero of the east" , "Battle warrior"};
        ArrayList<Monster> monsterArrayList = new ArrayList<Monster>();
        Monster monster = null;
        Deck deck = new Deck();
        for (String monsterName :
                monsterNames) {
            for (int i = 0; i < 3; i++) {
                try {
                    monster = new Monster(monsterName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                deck.addCardToMainDeck(monster);
                monsterArrayList.add(monster);
            }
        }
        assertTrue(deck.isValid());
        System.out.println(deck.getError());
        deck.addCardToMainDeck(monsterArrayList.get(0));
        assertTrue(deck.isValid());
        deck.deleteCardFromMainDeck(monsterArrayList.get(0));
        assertTrue(deck.isValid());
    }

    @Test
    public void testLoadingInvalidMonster() {
        boolean found = true;
        try {
            Monster monster = new Monster("hayulayetarsnakekhafan");
            found = true;
        } catch (NoMonsterWithThisNameException e) {
            found = false;
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        assertFalse(found);
    }
    @Test
    public void testSpellTrap() {
        try {
            SpellAndTrap spellAndTrap1 = new SpellAndTrap("Trap Hole");
            assertEquals("Trap",spellAndTrap1.spellAndTrapType);
            assertEquals("Normal" , spellAndTrap1.icon);
            assertEquals("When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.",spellAndTrap1.description);
            assertEquals("Unlimited" , spellAndTrap1.getStatus());
            assertEquals(2000 , spellAndTrap1.getPrice());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
