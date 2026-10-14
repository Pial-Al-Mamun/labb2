package se.iths.pialalmamun.webshop;

import se.iths.pialalmamun.webshop.interfaces.ProductStorage;
import se.iths.pialalmamun.webshop.model.Product;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ProductFileStorage implements ProductStorage {

    private static final Path FILE = Path.of("products.txt");

    @Override
    public void saveProduct(Product product) {
        String line = product.toFileLine() + System.lineSeparator();
        try {
            Files.writeString(FILE, line,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public List<Product> getProducts() {
        if (Files.notExists(FILE)) {
            return List.of();
        }
        try (var lines = Files.lines(FILE)) {
            return lines
                    .filter(l -> !l.isBlank())
                    .map(Product::fromFileLine)
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Product getProduct(String articleNumber) {
        return getProducts().stream()
                .filter(p -> p.getArticleNumber().equals(articleNumber))
                .findFirst()
                .orElse(null);
    }
}
