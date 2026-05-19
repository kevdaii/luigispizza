package at.spengergasse.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void testToString() {
        OrderService luigispizza = new OrderService();
        System.out.println(luigispizza);
    }
}