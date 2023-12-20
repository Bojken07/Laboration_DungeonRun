package com.bojken.demo;

import java.util.Scanner;

public class MonsterEncounter {

    // Instantiation
    Scanner scanner = new Scanner(System.in);

    public void monsterBattle(Player player, Monster monster) {

        do {
            System.out.println("""
                What is your next action?
                1 - Attack
                2 - Check health stats
                3 - Flee"""
            );

            try {

                switch (scanner.nextInt()) {
                    case 1 -> {
                        monster.defend(player);         // Monster taking damage from player

                        if (monster.isAlive()) {
                            player.defend(monster);     // Player taking damage from monster
                        } else {
                            player.setMonstersKilled(player.getMonstersKilled() + 1);
                            System.out.println("You slayed the monster.\nReturning to main menu.");

                        }

                    }

                    case 2 -> System.out.println("\nPlayer health: " + player.getHealth() +
                            "\nMonster health: " + monster.monsterStats() + "\n"
                    );

                    case 3 -> {
                        if (player.playerDidFlee()) {
                            monster.setHealth(0);       // isAlive() while be false and break the do-while loop
                        }
                    }

                    default -> System.out.println("Choose an available action.");

                }
            } catch (Exception e) {

                scanner.next();         // Ignores whatever the user typed that wasn't an integer
                System.out.println("Only number are available. Try again.");
            }

        } while (player.isAlive() && monster.isAlive());

    }
}
