package se.iths.pialalmamun.webshop;

import se.iths.pialalmamun.webshop.interfaces.OutInputHandler;
import se.iths.pialalmamun.webshop.model.Product;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        var productFileStorage = new ProductFileStorage();
        var consoleInputHandler = new ConsoleOutInputHandler();
        var webShopManager = new WebShopManager(productFileStorage, consoleInputHandler);
        webShopManager.startWebshop();
    }
}
