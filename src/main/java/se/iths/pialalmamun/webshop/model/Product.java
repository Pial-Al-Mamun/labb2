package se.iths.pialalmamun.webshop.model;

public class Product {
    private String articleNumber;
    private String title;
    private double price;
    private String description;

    public Product(String articleNumber, String title, double price, String description) {
        this.articleNumber = articleNumber;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toFileLine() {
        return articleNumber + ";" + title + ";" + price + ";" + description;
    }

    public static Product fromFileLine(String line) {
        var parts = line.split(";");

        if (parts.length != 4) {
            IO.println("Invalid line format" + parts.toString());
        }

        return new Product(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
    }
}
