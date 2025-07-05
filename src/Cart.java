import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
        }
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                int newQuantity = item.getQuantity() + quantity;
                if (!product.isAvailable(newQuantity)) {
                    throw new IllegalArgumentException("Exceeds available stock for product: " + product.getName());
                }
                items.remove(item);
                items.add(new CartItem(product, newQuantity));
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public List<Product> getShippableItems() {
        List<Product> shippable = new ArrayList<>();
        for (CartItem item : items) {
            Product p = item.getProduct();
            if (p.getWeight() > 0) {
                shippable.add(p);
            }
        }
        return shippable;
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) return "Cart is empty.";
        StringBuilder sb = new StringBuilder("Cart Contents:\n");
        for (CartItem item : items) {
            sb.append("- ").append(item.toString()).append("\n");
        }
        sb.append("Subtotal: ").append(getSubtotal());
        return sb.toString();
    }
}

