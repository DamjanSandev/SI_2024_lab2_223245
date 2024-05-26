import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    private List<Item> items(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }


    @Test
    void EveryBranch() {
        RuntimeException ex;
        //1
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 10));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        //2
        Item item = new Item("", "12", 180, 0.85f);
        SILab2.checkCart(items(item), 302);
        assertEquals("unknown", item.getName());

        //3
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(new Item("Lab", "64A", 300, 0.84f)), 200));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //4
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(new Item("Lab", null, 300, 0.84f)), 200));
        assertTrue(ex.getMessage().contains("No barcode!"));

        //5
        item = new Item("Lab", "123", 402, 0f);
        SILab2.checkCart(items(item), 302);
        assertEquals(item.discount, 0);
        assertFalse(SILab2.checkCart(items(item), 302));

        //6
        item = new Item("Lab", "0555", 472, 0.82f);
        SILab2.checkCart(items(item), 450);
        assertTrue(item.discount > 0);
        assertTrue(SILab2.checkCart(items(item), 360));
    }

    @Test
    void MultipleCondition() {

        // if(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')

        //TTT

        assertTrue(SILab2.checkCart(items(new Item("Lab","012",350,0.75f)),300));

        //TTF

        assertFalse(SILab2.checkCart(items(new Item("Lab","12",350,0.9f)),300));

        //TFX

        assertFalse(SILab2.checkCart(items(new Item("Lab","012",350,0f)),349));

        //FXX

        assertFalse(SILab2.checkCart(items(new Item("Lab","012",250,0.65f)),150));

    }

}