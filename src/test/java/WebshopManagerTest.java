import org.junit.jupiter.api.Test;
import se.iths.pialalmamun.webshop.WebShopManager;
import se.iths.pialalmamun.webshop.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebshopManagerTest {
    @Test
    void saveTwoProductsAndCheckListSize() {
        var storage = new ProductStorageMock();
        var manager = new WebShopManager(storage, null);

        var p1 = new Product("1", "Kaffe", 49.0, "Mörkrost");
        var p2 = new Product("2", "Te", 39.0, "Grönt te");

        manager.saveProductToStorage(p1);
        manager.saveProductToStorage(p2);

        var products = manager.getProductsFromStorage();
        assertEquals(2, products.size());
    }

    @Test
    void getSavedProductByArticleNumber() {
        var storage = new ProductStorageMock();
        var manager = new WebShopManager(storage, null);

        var product = new Product("42", "Choklad", 25.0, "Mjölkchoklad");
        manager.saveProductToStorage(product);

        var fetched = manager.getProductFromStorage("42");
        assertEquals("42", fetched.getArticleNumber());
    }
}
