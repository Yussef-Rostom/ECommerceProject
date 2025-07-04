package ecommerce.cart;
import ecommerce.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0 || product.getQuantity() < quantity) {
            System.out.println("Invalid quantity");
            return;
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
        System.out.printf("Added %d x %s to cart.%n", quantity, product.getName());
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
