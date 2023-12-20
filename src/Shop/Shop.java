package Shop;



import com.bojken.demo.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    List<Weapon> weaponList = new ArrayList<>();

    public void shopForItems(Player player) {

        System.out.println("Welcome to the Shop.");
        System.out.println("Available weapons for purchase:\n" + getWeaponList());

        boolean isShopping = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Players currency: " + player.getCurrency());
            System.out.println("""
                    What would you like to purchase?
                     1 - Axe - 80g
                     2 - Staff - 70g
                     3 - Sword - 400g
                     4 - Exit shop""");

            try {

                switch (scanner.next()) {
                    case "1" -> {
                        if (checkIfOutOfCurrency(player)) {
                            isShopping = false;
                        } else if ( player.getCurrency() - weaponList.get(0).getPrice() <= 0 ) {
                            System.out.println("You do not have enough funds to purchase this item.");
                        } else {
                            System.out.println("You bought an axe for " + weaponList.get(0).getPrice() + "g");
                            player.setCurrency(player.getCurrency() - weaponList.get(0).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(0));
                        }

                    }

                    case "2" -> {
                        if (checkIfOutOfCurrency(player)) {
                            isShopping = false;
                        } else if ( player.getCurrency() - weaponList.get(1).getPrice() <= 0 ) {
                            System.out.println("You do not have enough funds to purchase this item.");
                        } else {
                            System.out.println("You bought a staff for " + weaponList.get(1).getPrice() + "g");
                            player.setCurrency(player.getCurrency() - weaponList.get(1).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(1));
                        }
                    }

                    case "3" -> {
                        if (checkIfOutOfCurrency(player)) {
                            isShopping = false;
                        } else if ( player.getCurrency() - weaponList.get(2).getPrice() <= 0 ) {
                            System.out.println("You do not have enough funds to purchase this item.");
                        } else {
                            System.out.println("You bought a sword for " + weaponList.get(2).getPrice() + "g");
                            player.setCurrency(player.getCurrency() - weaponList.get(2).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(2));
                        }
                    }

                    case "4" -> {
                        System.out.println("Exiting shop.");
                        isShopping = false;
                    }

                    default -> System.out.println("Please choose an existing item.");
                }

            } catch (Exception e) {

                scanner.next();     // Ignores whatever the user typed in that wasn't an integer
                System.out.println("Choose only from the numbers available.");
            }


        } while (isShopping);

    }

    public boolean checkIfOutOfCurrency(Player player) {

        if (player.getCurrency() <= 0) {
            System.out.println("You have no money. Come back when you have some to spend.");
            return true;
        } else {
            return false;
        }

    }

    public void addWeaponToList() {

        weaponList.add(new Axe("Rune Axe of Grombrindal",20,80));
        weaponList.add(new Staff("Staff of Volans",12,70));     // Anonymous objects, meaning we don't need to instantiate Weapon class
        weaponList.add(new Sword("Crucible Blade",80,400));
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

}
