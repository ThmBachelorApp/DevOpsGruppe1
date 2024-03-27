import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows; // Stellen Sie sicher, dass Sie dies hinzufügen
import org.junit.jupiter.api.Test;
import com.smartshop.model.Product;
import com.smartshop.service.Cart;
import org.junit.jupiter.api.BeforeEach;



class CartTests {
    public Cart cart;

    @BeforeEach
    void setUp() {
    private cart = new Cart();
    }

    @Test
    void testAddProduct() {
        cart.addProduct(new Product("1", "Java Book", 29.99));
        assertEquals(1, cart.getProductCount(), "Nach dem Hinzufügen eines Produkts sollte der Warenkorb 1 Produkt enthalten.");
    }

    @Test
    void testGetTotalPriceWithSingleProduct() {
        cart.addProduct(new Product("1", "Java Book", 29.99));
        assertEquals(29.99, cart.getTotalPrice(), 0.01, "Die Gesamtsumme sollte 29.99 betragen, nachdem ein Produkt hinzugefügt wurde.");
    }

    @Test
    void testGetTotalPriceWithMultipleProducts() {
        cart.addProduct(new Product("1", "Java Book", 29.99));
        cart.addProduct(new Product("2", "Spring Book", 39.99));
        assertEquals(69.98, cart.getTotalPrice(), 0.01, "Die Gesamtsumme sollte 69.98 betragen, nachdem zwei Produkte hinzugefügt wurden.");
    }

    @Test
    void testGetProductCountWithNoProduct() {
        assertEquals(0, cart.getProductCount(), "Der Warenkorb sollte keine Produkte enthalten, wenn noch keine hinzugefügt wurden.");
    }

    @Test
    void testGetProductCountWithMultipleProducts() {
        cart.addProduct(new Product("1", "Java Book", 29.99));
        cart.addProduct(new Product("2", "Spring Book", 39.99));
        assertEquals(2, cart.getProductCount(), "Der Warenkorb sollte 2 Produkte enthalten, nachdem zwei Produkte hinzugefügt wurden.");
    }

    
}

class ProductTests {

    @Test
    void testProductConstructorAndGetters() {
        Product product = new Product("1", "Java Book", 29.99);
        assertEquals("1", product.getId());
        assertEquals("Java Book", product.getName());
        assertEquals(29.99, product.getPrice(), 0.001); // Hinzufügen einer Toleranz für den Preisvergleich
    }

    @Test
    void testSetters() {
        Product product = new Product("1", "Java Book", 29.99);
        product.setId("2");
        product.setName("Spring Book");
        product.setPrice(39.99);

        assertEquals("2", product.getId());
        assertEquals("Spring Book", product.getName());
        assertEquals(39.99, product.getPrice(), 0.001); // Hinzufügen einer Toleranz für den Preisvergleich
    }

    @Test
    void testApplyDiscountWithinRange() {
        Product product = new Product("1", "Java Book", 50.00);
        product.applyDiscount(0.2); // 20% Rabatt
        assertEquals(40.00, product.getPrice(), 0.001); // Hinzufügen einer Toleranz für den Preisvergleich
    }

    @Test
    void testApplyDiscountLowerBoundary() {
        Product product = new Product("1", "Java Book", 50.00);
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-0.1)); // Überprüfen der Ausnahme
    }

    @Test
    void testApplyDiscountUpperBoundary() {
        Product product = new Product("1", "Java Book", 50.00);
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(1.1)); // Überprüfen der Ausnahme
    }
}

