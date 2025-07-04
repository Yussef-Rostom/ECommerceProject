package ecommerce.product;
import java.time.LocalDate;

public class DigitalExpirableProduct extends DigitalNonExpirableProduct implements Expirable{
    protected final LocalDate expiryDate;
    public DigitalExpirableProduct(String name, double price, int availableQuantity, int expiryPeriod) {
        super(name, price, availableQuantity);
        this.expiryDate =  LocalDate.now().plusDays(expiryPeriod);
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}
