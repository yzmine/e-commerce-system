import java.util.List;

public class ShippingService {
    public static void ship(List<Product> itemsToShip) {
        System.out.println("📦 Shipping the following items:");
        for (Product product : itemsToShip) {
            System.out.println("- " + product.getName() + " | Weight: " + product.getWeight() + "kg");
        }
    }
}
