package model;

import org.junit.jupiter.api.Test;
import se.iths.pialalmamun.webshop.model.HalfPriceDiscount;
import se.iths.pialalmamun.webshop.model.TenPercentDiscount;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountsTests {

    @Test
    public void calculatePrice_shouldReturn90PercentOfOriginalPrice() {
        var discount = new  TenPercentDiscount("Discount");
        var originalPrice = 100.0;
        var expected = 90.0;
        assertEquals(expected, discount.calculatePrice(originalPrice), 0.0001);
    }


    @Test
    public void calculatePrice_shouldReturn50PercentOfOriginalPrice() {
        var discount = new HalfPriceDiscount("Discount");
        var originalPrice = 100.0;
        var expected = 50.0;
        assertEquals(expected, discount.calculatePrice(originalPrice), 0.0001);
    }
}
