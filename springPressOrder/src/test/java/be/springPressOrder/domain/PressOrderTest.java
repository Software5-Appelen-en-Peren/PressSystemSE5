package be.springPressOrder.domain;

import be.springPressOrder.dao.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;


public class PressOrderTest {


    private PressOrder order;

    @Before
    public void prepareTests() {
        this.order = new PressOrder();
    }

    @Test
    // Input: aantal appelen = -1
    // output: foutmelding dat een bestelling minimum 3 appelen moet omvatten
    public void setAppelenAanMin1() throws Exception {
        order.setFruit("appel");
        // Wat we hier doen zou niet mogen werken.
        // Indien er geen foutmelding gebeurd is er een probleem.
        assertThrows(IllegalArgumentException.class, () -> order.setFruitAmount(-1));
    }

    @Test
    public void setPerenAan3() throws Exception {
        order.setFruit("peer");
        assertThrows(IllegalArgumentException.class, () -> order.setFruitAmount(3));
    }

    @Test
    public void setAppelenAan3() throws Exception {
        order.setFruit("appel");
        order.setFruitAmount(3);
        assertEquals(order.getMaxJuiceAmount(), Math.floor((3.0 / 2)));
    }

    @Test
    public void setPerenAan4() throws Exception {
        order.setFruit("peer");
        order.setFruitAmount(4);
        assertEquals(order.getMaxJuiceAmount(), Math.floor((4.0 / 3)));
    }

    @Test
    public void setFruitAanAppelsienen() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> order.setFruit("appelsien"));
    }

    @Test
    public  void processValidation() throws  Exception {
        assertThrows(IllegalArgumentException.class, () -> order.setFruit("appelsien"));

        order.setFruit("peer");
        order.setFruitAmount(4);
        assertEquals(order.getMaxJuiceAmount(), Math.floor((4.0 / 3)));
    }


}