package ecommerce.product;
import java.util.ArrayList;

public class ProductFactory {
    public static Product createProduct(String name, double price, int quantity){
        return new DigitalNonExpirableProduct(name, price, quantity);
    }
    public static Product createProduct(String option, String name, double price, int quantity, int value){
        if(option.equalsIgnoreCase("expirable")) return new DigitalExpirableProduct(name, price, quantity, value);
        if(option.equalsIgnoreCase("shippable")) return new PhysicalNonExpirableProduct(name, price, quantity, value);
        throw new IllegalArgumentException("Invalid option");
    }
    public static Product createProduct(String option1, String option2, String name, double price, int quantity, int value1, int value2){
        if(option1.equalsIgnoreCase("shippable") && option2.equalsIgnoreCase("expirable")) return new PhysicalExpirableProduct(name, price, quantity, value1, value2);
        if(option1.equalsIgnoreCase("expirable") && option2.equalsIgnoreCase("shippable")) return new PhysicalExpirableProduct(name, price, quantity, value2, value1);
        throw new IllegalArgumentException("Invalid option");
    }
}
