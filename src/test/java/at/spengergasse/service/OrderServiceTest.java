package at.spengergasse.service;

import at.spengergasse.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    @Test
    void testToString() {
        OrderRepository repository = mock(OrderRepository.class);
        when(repository.count()).thenReturn(1L);      // skip test-data seeding
        when(repository.findAll()).thenReturn(List.of());
        OrderService luigispizza = new OrderService(repository);
        System.out.println(luigispizza);
    }
}