package Model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
}
