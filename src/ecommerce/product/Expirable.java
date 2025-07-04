package ecommerce.product;
import java.time.LocalDate;

public interface Expirable {
    LocalDate getExpiryDate();
    boolean isExpired();
}