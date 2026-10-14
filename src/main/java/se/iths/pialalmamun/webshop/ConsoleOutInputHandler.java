package se.iths.pialalmamun.webshop;

import se.iths.pialalmamun.webshop.interfaces.OutInputHandler;

public class ConsoleOutInputHandler implements OutInputHandler {
    @Override
    public String prompt(String message) {
        IO.println(message);
        return IO.readln();
    }

    @Override
    public void info(String message) {
        IO.println(message);
    }

    @Override
    public String menu() {
        IO.println();
        IO.println("=== Webshop ===");
        IO.println("1. Lägg till produkt");
        IO.println("2. Lista alla produkter");
        IO.println("3. Visa information om en produkt");
        IO.println("4. Avsluta applikationen");

        return IO.readln("Ditt val: ").trim();
    }
}
