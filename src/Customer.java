
public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance, Cart cart ) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product, int quantity) {
        this.cart.addItem(product, quantity);
    }
    public void Checkout(){
        if(this.cart.isEmpty()){
            throw new IllegalArgumentException("Cart is empty");
        }
        else {
            for(CartItem item : cart.getItems()){
                if (!item.getProduct().isAvailable(item.getQuantity())) {
                    throw new IllegalStateException("Product out of stock: " + item.getProduct().getName());
                }

                if (item.getProduct().isExpired()) {
                    throw new IllegalStateException("Product is expired: " + item.getProduct().getName());
                }
            }
            double total= cart.getSubtotal()+ cart.getShippableItems().size()*10;
            if (balance < total){
                throw new IllegalArgumentException("Balance is insufficient");
            }
            else {
                balance-=total;
                for (CartItem item : cart.getItems()) {
                    item.getProduct().reduceQuantity(item.getQuantity());
                }
                if (!cart.getShippableItems().isEmpty()) {
                    ShippingService.ship(cart.getShippableItems());
                }

                viewCart();
                System.out.println("- - - - - - - - - - - - -");
                System.out.println("Checkout successful");
                System.out.println("Customer: " + name);
                System.out.println("Subtotal: $" + cart.getSubtotal());
                System.out.println("Shipping Fee: $" + cart.getShippableItems().size()*10);
                System.out.println("Total Paid: $" + total);
                System.out.println("Remaining Balance: $" + balance);


                cart.clear();
            }

        }

    }
    public void viewCart() {
        System.out.println(cart);
    }
}
