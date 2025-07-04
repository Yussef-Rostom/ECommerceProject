package ecommerce.product;

import java.time.LocalDate;

public class PhysicalExpirableProduct extends PhysicalNonExpirableProduct implements Expirable, Shippable{
    protected final LocalDate expiryDate;

    public PhysicalExpirableProduct(String name, double price, int quantity, double weight, int expiryPeriod) {
        super(name, price, quantity, weight);
        this.expiryDate = LocalDate.now().plusDays(expiryPeriod);
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
