package com.bojken.demo;

import java.util.Random;

public class Monster implements ICombat{

    // Instantiation
    static Random random = new Random();

    private String name;
    private int health;
    private int minBaseDamage;
    private int maxBaseDamage;

    // Method that creates new monsters (three in this case)
    public static Monster newMonsterEncounter(int chosenMonster) {

        // Returns an object that is a new monster of your choice
        if (chosenMonster == 1) {
            return new Monster("Imp",20,3,6);
        } else if (chosenMonster == 2) {
            return new Monster("Hell Knight",35,8,12);
        } else if (chosenMonster == 3) {
            return new Monster("Baron of Hell",70,14,18);
        } else {
            return new Monster("Arch-vile",500,25,40);
        }

    }

    public Monster(String name, int health, int minBaseDamage, int maxBaseDamage) {
        this.name = name;
        this.health = health;
        this.minBaseDamage = minBaseDamage;
        this.maxBaseDamage = maxBaseDamage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public int fight() {
        return calculateDamage();
    }

    @Override
    public int calculateDamage() {
        return random.nextInt(minBaseDamage, maxBaseDamage + 1);
    }

    public void defend(Player player) {

        int damageTaken = player.fight();
        health -= damageTaken;        // takes damage from player
        System.out.println("\nMonster took " + damageTaken + " damage.");

    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public String monsterStats() {
        return "Monster{" +
                "name = '" + name + '\'' +
                ", health = " + health +
                '}';
    }

}
