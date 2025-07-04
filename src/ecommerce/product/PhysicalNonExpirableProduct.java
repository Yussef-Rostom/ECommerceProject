package ecommerce.product;

public class PhysicalNonExpirableProduct extends Product implements Shippable{
    protected double weight;

    public PhysicalNonExpirableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;

    }
}
