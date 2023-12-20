package Shop;

public class Weapon {

    private String name;
    private int damage;
    private int price;

    public Weapon(String name, int damage, int price) {
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", price=" + price +
                '}';
    }

}