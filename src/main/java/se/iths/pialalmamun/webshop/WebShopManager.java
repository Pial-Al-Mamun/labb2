package se.iths.pialalmamun.webshop;

import se.iths.pialalmamun.webshop.interfaces.OutInputHandler;
import se.iths.pialalmamun.webshop.interfaces.ProductStorage;
import se.iths.pialalmamun.webshop.model.Discount;
import se.iths.pialalmamun.webshop.model.HalfPriceDiscount;
import se.iths.pialalmamun.webshop.model.Product;
import se.iths.pialalmamun.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private final ProductStorage productStorage;
    private final OutInputHandler inputHandler;
    private Discount discount;

    public WebShopManager(ProductStorage productStorage, OutInputHandler inputHandler) {
        this.productStorage = productStorage;
        this.inputHandler = inputHandler;
    }

    public void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    public List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    public Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    private void addProduct() {
        var articleNumber = inputHandler.prompt("Mata in artikelnummer:").trim();
        var title = inputHandler.prompt("Mata in titel:").trim();

        double price;
        try {
            price = Double.parseDouble(inputHandler.prompt("Mata in pris:").trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            inputHandler.info("Ogiltigt pris, produkten sparades inte.");
            return;
        }

        String description = inputHandler.prompt("Mata in beskrivning:").trim();

        saveProductToStorage(new Product(articleNumber, title, price, description));
        inputHandler.info("Produkten har lagts till.");
    }

    private void listProducts() {
        var products = getProductsFromStorage();

        if (products.isEmpty()) {
            inputHandler.info("Inga produkter finns ännu.");
            return;
        }

        for (var product : products) {
            printProduct(product);
        }
    }

    private void showProduct() {
        var articleNumber = inputHandler.prompt("Mata in artikelnummer:").trim();
        var product = getProductFromStorage(articleNumber);

        if (product == null) {
            inputHandler.info("Ingen produkt med artikelnummer " + articleNumber + " hittades.");
        } else {
            printProduct(product);
        }
    }

    private void printProduct(Product product) {
        inputHandler.info("Artikelnummer: " + product.getArticleNumber());
        inputHandler.info("Titel: " + product.getTitle());
        inputHandler.info("Pris: " + product.getPrice());
        inputHandler.info("Rabatterat pris: " + discount.calculatePrice(product.getPrice()));
        inputHandler.info("Beskrivning: " + product.getDescription());
        inputHandler.info("---");
    }
    public void startWebshop() {
        var code = inputHandler.prompt("Mata in rabattkod:").trim();

        if (code.equals("halfprice")) {
            discount = new HalfPriceDiscount("discount to half price");
        } else {
            discount = new TenPercentDiscount("discount to ten percent");
        }

        boolean running = true;

        while (running) {
            String choice = inputHandler.menu();

            switch (choice) {
                case "1" -> addProduct();
                case "2" -> listProducts();
                case "3" -> showProduct();
                case "4" -> {
                    inputHandler.info("Avslutar applikationen. Hej då!");
                    running = false;
                }
                default -> inputHandler.info("Ogiltigt val, välj 1-4.");
            }
        }
    }

}
