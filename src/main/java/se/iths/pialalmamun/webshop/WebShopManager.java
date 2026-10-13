package se.iths.pialalmamun.webshop;

import se.iths.pialalmamun.webshop.model.Discount;
import se.iths.pialalmamun.webshop.model.HalfPriceDiscount;
import se.iths.pialalmamun.webshop.model.Product;
import se.iths.pialalmamun.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private ProductStorage productStorage;
    private Discount discount;

    public WebShopManager(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    private void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    private List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    private Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    public void startWebshop() {
        var code = IO.readln("Mata in rabattkod: ").trim();

        if (code.equals("halfprice")) {
            discount = new HalfPriceDiscount("discount to half price");
        } else {
            discount = new TenPercentDiscount("discount to ten percent");
        }

        boolean running = true;

        while (running) {
            printMenu();
            String choice = IO.readln("Ditt val: ").trim();

            switch (choice) {
                case "1" -> addProduct();
                case "2" -> listProducts();
                case "3" -> showProduct();
                case "4" -> {
                    IO.println("Avslutar applikationen. Hej då!");
                    running = false;
                }
                default -> IO.println("Ogiltigt val, välj 1-4.");
            }
        }
    }

    private void printMenu() {
        IO.println();
        IO.println("=== Webshop ===");
        IO.println("1. Lägg till produkt");
        IO.println("2. Lista alla produkter");
        IO.println("3. Visa information om en produkt");
        IO.println("4. Avsluta applikationen");
    }

    private void addProduct() {
        var articleNumber = IO.readln("Mata in artikelnummer: ").trim();
        var title = IO.readln("Mata in titel: ").trim();

        double price;
        try {
            price = Double.parseDouble(IO.readln("Mata in pris: ").trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            IO.println("Ogiltigt pris, produkten sparades inte.");
            return;
        }

        String description = IO.readln("Mata in beskrivning: ").trim();

        saveProductToStorage(new Product(articleNumber, title, price, description));
        IO.println("Produkten har lagts till.");
    }

    private void listProducts() {
        var products = getProductsFromStorage();

        if (products.isEmpty()) {
            IO.println("Inga produkter finns ännu.");
            return;
        }

        for (var product : products) {
            printProduct(product);
        }
    }

    private void showProduct() {
        var articleNumber = IO.readln("Mata in artikelnummer: ").trim();
        var product = getProductFromStorage(articleNumber);

        if (product == null) {
            IO.println("Ingen produkt med artikelnummer " + articleNumber + " hittades.");
        } else {
            printProduct(product);
        }
    }

    private void printProduct(Product product) {
        IO.println("Artikelnummer: " + product.getArticleNumber());
        IO.println("Titel: " + product.getTitle());
        IO.println("Pris: " + product.getPrice());
        IO.println("Rabatterat pris: " + discount.calculatePrice(product.getPrice()));
        IO.println("Beskrivning: " + product.getDescription());
        IO.println("---");
    }
}
