package se.iths.pialalmamun.webshop.interfaces;

import se.iths.pialalmamun.webshop.model.Product;

import java.util.List;

public interface ProductStorage {
    void saveProduct(Product product);

    List<Product> getProducts();

    Product getProduct(String articleNumber);
}
