package ecommerce.product;
import ecommerce.cart.Cart;
import ecommerce.customer.Customer;
import java.util.ArrayList;
import java.util.Map;

public class CheckoutService {
    private final double costPerKg;

    public CheckoutService(double costPerKg) {
        this.costPerKg = costPerKg;
    }

    public double calculateFee(ArrayList<Shippable> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        double totalWeightGrams = items.stream().mapToDouble(Shippable::getWeight).sum();
        double totalWeightKg = totalWeightGrams / 1000.0;
        return totalWeightKg * this.costPerKg;
    }

    public void dispatch(ArrayList<Shippable> shippableItems) {
        if (shippableItems == null || shippableItems.isEmpty()) {
            return;
        }
        System.out.println("\n** Shipment Notice **");
        double totalWeight = 0;
        for (Shippable item : shippableItems) {
            System.out.printf("%s %.0fg%n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000.0);
    }

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        double subtotal = 0;
        ArrayList<Shippable> shippableItems = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            if (product.getQuantity() < entry.getValue()) {
                System.out.println("Product " + product.getName() + " is out of stock.");
                return;
            }
            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                System.out.println("Product " + product.getName() + " is expired.");
                return;
            }
            subtotal += product.getPrice() * entry.getValue();

            if (product instanceof Shippable) {
                for (int i = 0; i < entry.getValue(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }

        double shippingFee = calculateFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            System.out.println("Invalid balance");
            return;
        }

        customer.debit(totalAmount);
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            product.setQuantity(product.getQuantity() - entry.getValue());
        }

        dispatch(shippableItems);

        System.out.println("\n** Checkout Receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%d x %s %.2f%n", entry.getValue(), entry.getKey().getName(), entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.2f%n", subtotal);
        System.out.printf("Shipping %.2f%n", shippingFee);
        System.out.printf("Paid Amount %.2f%n", totalAmount);
        System.out.println("----------------------");
        System.out.printf("Customer new balance: %.2f%n", customer.getBalance());

        cart.clear();
    }
}