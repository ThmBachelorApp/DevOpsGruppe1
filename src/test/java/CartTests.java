import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.smartshop.service.Cart;
import com.smartshop.model.Product;


public class CartTests {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
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
