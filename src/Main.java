
import ecommerce.cart.Cart;
import ecommerce.customer.Customer;
import ecommerce.product.ProductFactory;
import ecommerce.product.CheckoutService;

public class Main {

    public static void main(String[] args) {


        //Successful checkout
        runScenario1();

        System.out.println("--------------------------------");

        //Invalid balance
        runScenario2();

        System.out.println("--------------------------------");

        //Invalid quantity
        runScenario3();

        System.out.println("--------------------------------");

        //Invalid quantity
        runScenario4();

        System.out.println("--------------------------------");

        //empty cart
        runScenario5();
    }


    public static void runScenario1() {

        double shippingCostPerKg = 10.5;
        CheckoutService checkoutService = new CheckoutService(shippingCostPerKg);

        System.out.println("SCENARIO 1");
        Customer customer = new Customer("YussefRostom", 1000.0);
        Cart cart = new Cart();

        // Create Digital Non Expirable Product
        cart.addProduct(ProductFactory.createProduct("Scratch Card", 10, 5), 1);

        // Create Digital Expirable Product
        cart.addProduct(ProductFactory.createProduct("expirable", "Windows License", 100, 10, 326), 2);

        // Create Physical Non Expirable Product
        cart.addProduct(ProductFactory.createProduct("shippable", "TV", 100, 10, 2500), 2);

        // Create Physical Expirable Product
        cart.addProduct(ProductFactory.createProduct("expirable", "shippable", "Cheese", 100, 10, 14, 200), 2);
        cart.addProduct(ProductFactory.createProduct("shippable", "expirable", "Biscuits", 100, 10, 30, 100), 2);

        //now Check out
        checkoutService.checkout(customer, cart);
    }


    public static void runScenario2() {
        double shippingCostPerKg = 10.5;
        CheckoutService checkoutService = new CheckoutService(shippingCostPerKg);

        System.out.println("SCENARIO 2");
        Customer customer = new Customer("YussefRostom", 100.0);
        Cart cart = new Cart();
        cart.addProduct(ProductFactory.createProduct("shippable", "TV", 100, 10, 100), 2);
        checkoutService.checkout(customer, cart);
    }

    public static void runScenario3() {
        System.out.println("SCENARIO 3");
        Customer customer = new Customer("YussefRostom", 100000.0);
        Cart cart = new Cart();
        cart.addProduct(ProductFactory.createProduct("expirable", "Windows License", 100, 10, 0), 2);
    }

    public static void runScenario4() {
        System.out.println("SCENARIO 4");
        Customer customer = new Customer("YussefRostom", 100000.0);
        Cart cart = new Cart();
        cart.addProduct(ProductFactory.createProduct("expirable", "shippable", "Cheese", 100, 1, 0, 200), 2);
    }
    public static void runScenario5() {
        double shippingCostPerKg = 10.5;
        CheckoutService checkoutService = new CheckoutService(shippingCostPerKg);

        System.out.println("SCENARIO 5");
        Customer customer = new Customer("YussefRostom", 100000.0);
        Cart cart = new Cart();
        checkoutService.checkout(customer, cart);
    }
}