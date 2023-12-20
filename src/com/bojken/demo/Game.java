package com.bojken.demo;


import Shop.Shop;

import java.util.Scanner;

import static com.bojken.demo.Monster.newMonsterEncounter;

public class Game {

    static DBConnection dbConnection = new DBConnection();




    Scanner scanner = new Scanner(System.in);
    Player player = new Player();



    WriteScoreFile playerStats = new WriteScoreFile();
    MonsterEncounter monsterEncounter = new MonsterEncounter();
    Shop shop = new Shop();

    public void mainMenu() {

        dbConnection.open();
        dbConnection.getPlayer(1);
        System.out.println(dbConnection.getPlayer(1));
        System.out.println(dbConnection.getPlayerIDCount());
        for (int i = 1; i < dbConnection.getPlayerIDCount()+1; i++) {
            System.out.println(dbConnection.getPlayer(i));
            System.out.println("");
        }
        dbConnection.getPlayerWithId(1);
        dbConnection.createTablePlayer();
        dbConnection.updatePlayerHealth(player);
        player.setPlayerName(scanner.nextLine());
        dbConnection.createPlayer(player);
        shop.addWeaponToList();                     // Beginning the game by adding weapons to the shop
        boolean continueGame;

        do {
            continueGame = true;

            System.out.println("\nCurrent player: " + player.getPlayerName() +
                    "\nWhat would you like to do next? " +
                    "\n 1 - Fight a monster" +
                    "\n 2 - Check your current stats" +
                    "\n 3 - Go to the Shop" +
                    "\n 4 - Quit the game"
            );

            try {

                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.println();
                        System.out.println("You encounter a monster, a battle is sure to ensue.\n" + """
                                What type of monster would you like to fight? Type in an option from below
                                1 - Small monster
                                2 - Medium monster
                                3 - Big monster
                                4 - Boss monster""");

                        try {

                            int chosenMonster = scanner.nextInt();
                            System.out.println("\nYou chose monster type " + chosenMonster);

                            monsterEncounter.monsterBattle(player, newMonsterEncounter(chosenMonster));

                            if (player.isAlive()) {
                                player.setHealth(player.getMaxHealth());
                                dbConnection.updatePlayerHealth(player);
                                player.setExperience(player.getExperience() + 10);
                                dbConnection.updatePlayerExperience(player);
                                player.checkIfLevelUp();
                                dbConnection.updatePlayerLevel(player);
                                dbConnection.updatePlayerAgility(player);
                                player.setCurrency(player.getCurrency() + 15);
                                dbConnection.updatePlayerCurrency(player);
                            } else {
                                continueGame = false;
                            }

                        } catch (Exception e) {

                            scanner.next();     // Ignores whatever the user typed in that wasn't an integer
                            System.out.println("Choose only from the numbers available.");
                        }

                    }

                    case 2 -> {

                        if (player.currentWeapon.size() == 1) {
                            System.out.println("Your current stats:\n" + player.playerStats());
                            System.out.println("Current weapon:\n" + player.getCurrentWeapon());
                        } else {
                            System.out.println("Your current stats:\n" + player.playerStats());
                        }

                    }

                    case 3 -> shop.shopForItems(player);

                    case 4 -> {
                        System.out.println("Exiting Game");

                        if (player.currentWeapon.size() == 1) {
                            playerStats.writeScoreFile();       // Creates and/or writes players final scores in a text file
                        }

                        continueGame = false;
                    }

                    default -> System.out.println("Please choose an action that's available.");
                }

            } catch (Exception e) {

                scanner.next();         // Ignores whatever the user typed that wasn't an integer
                System.out.println("Only number are available. Try again.");
            }

        } while (continueGame);

        System.out.println("\nGame Over. Farewell great Slayer.");

    }
}
