import se.iths.pialalmamun.webshop.interfaces.ProductStorage;
import se.iths.pialalmamun.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductStorageMock implements ProductStorage {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void saveProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    @Override
    public Product getProduct(String articleNumber) {
        return products.stream()
                .filter(p -> p.getArticleNumber().equals(articleNumber))
                .findFirst()
                .orElse(null);
    }

}
