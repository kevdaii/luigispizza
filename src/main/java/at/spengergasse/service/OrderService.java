package at.spengergasse.service;

import at.spengergasse.domain.Order;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
public class OrderService {
    private ArrayList<Order> orders;

    public OrderService(){
        orders = new ArrayList<>(1000);
        fillTestData(500);
    }

    public void fillTestData(int amount){
        Faker faker;
        Order       order;
        LocalDate   orderDate;
        String      pizza;
        String      size;
        Integer     quantity;
        Double      price;
        Boolean     garlic;

        String[] PIZZAS = {"Margherita", "Salami", "Tonno", "Hawaii", "Funghi", "Diavolo"};
        String[] SIZES = {"Small", "Medium", "Large", "Family"};

        faker = new Faker();
        orders.clear();

        for(int i = 0; i < amount; i++){
            orderDate = LocalDate.now().minusDays((int) (Math.random() * 365 * 10));
            pizza = PIZZAS[faker.number().numberBetween(0, PIZZAS.length - 1)];
            size = SIZES[faker.number().numberBetween(0, SIZES.length - 1)];
            quantity = faker.number().numberBetween(1, 5);
            price = faker.number().randomDouble(2, 10, 50);
            garlic = faker.bool().bool();
            order = new Order(orderDate, pizza, size, quantity, price, garlic);
            orders.add(order);
        }
    }

    public ArrayList<Order> findAll(){
        ArrayList<Order> copy = new ArrayList<>(orders);
        return copy;
    }

    @Override
    public String toString(){
        return orders.stream()
                .map(o -> o.toString())
                .collect(Collectors.joining("\n"));
    }
}
