package com.bojken.demo;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.io.FileWriter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class WriteScoreFile {

    Player player = new Player();

    public void writeScoreFile() {

        try {
            FileWriter playerFileWriter = new FileWriter("Player_Results.txt");  // Default means file gets created in the project folder

            playerFileWriter.write(player.getPlayerName() +
                    "\nLevel reached: " + player.getLevel() +
                    "\nWeapon used: " + player.getCurrentWeapon() +
                    "\nMonsters defeated: " + player.getMonstersKilled()
            );
            playerFileWriter.close();
            System.out.println("To see final score stats, check the text-file named 'Player_Results.txt'");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("It doesn't work");
        }

    }

    public static class PlayerTests {

        Player player;

        @BeforeEach
        void setup() {
            player = new Player();
        }

        @Test
        @DisplayName("Checking to see if the right amount of damage from player is returned")
        public void calculateDamageTest() {

            assertTrue(
                    player.getMinDamage() <= player.calculateDamage() &&
                            player.getMaxDamage() >= player.calculateDamage());
        }

        @Test
        @DisplayName("Checking if players level increases when maxExperience is reached")
        public void checkIfLeveledUpTest() {
            player.setExperience(player.getMaxExperience());        // Sets to max experience to level up
            player.checkIfLevelUp();

            assertEquals(2, player.getLevel());
        }

        @Test
        @DisplayName("Checking to see if the player can lose the game by dying")
        public void isAliveTest() {
            player.setHealth(0);

            assertFalse(player.isAlive());
        }

        @RepeatedTest(5)
        @DisplayName("Adding X amount of experience to check if the random amount of exp is added to players experience")
        public void experienceAmountXTest() {
            Random random = new Random();
            player.setExperience(19);

            int x = random.nextInt(1, 10);
            System.out.println(x);
            player.setExperience(player.getExperience() + x);
            player.checkIfLevelUp();

        }
    }
}

