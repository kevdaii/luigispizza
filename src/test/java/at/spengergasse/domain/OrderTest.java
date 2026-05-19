package at.spengergasse.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testToString() {
        Order a =  new Order(LocalDate.now(), "Salami", "Large", 2, 15.0, true);
        System.out.println(a);
        System.out.println(a.getPrice());
    }
}