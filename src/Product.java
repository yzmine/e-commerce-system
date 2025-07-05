import java.time.LocalDate;

public class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected double weight;

    public Product(String name, double price, int quantity, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty;
    }

    public boolean isExpired() {
        return false;
    }

    public void reduceQuantity(int qty) {
        if (qty <= quantity) {
            quantity -= qty;
        } else {
            throw new IllegalArgumentException("Not enough stock to reduce.");
        }
    }

    @Override
    public String toString() {
        return name + " - $" + price + " x" + quantity + (weight > 0 ? " (shippable)" : "");
    }
}

