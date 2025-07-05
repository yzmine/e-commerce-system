import java.util.List;

public class ShippingService {
    public static void ship(List<Product> itemsToShip) {
        System.out.println("📦 Shipping the following items:");
        double totalweight=0;
        for (Product product : itemsToShip) {
            totalweight += product.getWeight();
            System.out.println("- " + product.getName() + " | Weight: " + product.getWeight() + "kg");
        }
        System.out.println("Total weight: " + totalweight);
        System.out.println("- - - - - - - - - - - - -");
    }
}
