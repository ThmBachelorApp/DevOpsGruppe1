import com.smartshop.model.Product;
import com.smartshop.service.Cart; // Stellen Sie sicher, dass Sie Cart importieren
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTests { // 'public' wurde entfernt; in JUnit 5 optional
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testAddProductAndTotalPrice() {
        Product product1 = new Product("1", "Java Book", 29.99);
        Product product2 = new Product("2", "Spring Book", 39.99);

        cart.addProduct(product1);
        cart.addProduct(product2);

        assertEquals(2, cart.getProductCount(), "Der Warenkorb sollte 2 Produkte enthalten.");
        assertEquals(69.98, cart.getTotalPrice(), 0.01, "Die Gesamtsumme sollte 69.98 betragen.");
    }
}
