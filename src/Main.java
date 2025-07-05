import java.time.LocalDate;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product tv = new Product("TV", 1000.0, 5, 10.0); // shippable
        Product scratchCard = new Product("Mobile Scratch Card", 20.0, 10, 0.0); // not shippable
        Product cheese = new ExpirableProduct("Cheese", 50.0, 3, 1.0, LocalDate.of(2025, 7, 1)); // expired if today > July 1
        Product biscuits = new ExpirableProduct("Biscuits", 15.0, 8, 0.5, LocalDate.of(2025, 7, 20));

        Customer customer = new Customer("Yasmine", 2000.0, new Cart());

        customer.addToCart(tv, 1);
        customer.addToCart(scratchCard, 2);
        customer.addToCart(biscuits, 3);

        customer.viewCart();
        customer.Checkout();
    }

}