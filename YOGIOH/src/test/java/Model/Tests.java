package Model;

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
        assertTrue(user1.doesHaveEnoughCoin(3500));
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
}
