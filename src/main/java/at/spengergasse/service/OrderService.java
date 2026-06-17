package at.spengergasse.service;

import at.spengergasse.domain.Order;
import at.spengergasse.domain.OrderException;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
        String      vehicleType;
        String      make;
        Integer     horsepower;
        Double      price;
        Boolean     oldTimer;

        String[] VEHICLETYPE = {"SUV", "Coupe", "Sedan", "Van", "Pickup", "Roadster"};
        String[] MAKE = {"NISSAN", "HONDA", "TOYOTA", "MAZDA", "SUBARU"};

        faker = new Faker();

        for(int i = 0; i < amount; i++){
            orderDate = LocalDate.now().minusDays((int) (Math.random() * 365 * 10));
            vehicleType = VEHICLETYPE[faker.number().numberBetween(0, VEHICLETYPE.length - 1)];
            make = MAKE[faker.number().numberBetween(0, MAKE.length - 1)];
            horsepower = faker.number().numberBetween(50, 1000);
            price = faker.number().randomDouble(2, 5000, 100000);
            oldTimer = faker.bool().bool();
            order = new Order(orderDate, vehicleType, make, horsepower, price, oldTimer);
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

    public void removeAllOrders() {
        orders.clear();
    }

    public void addOrder(Order o) {
        orders.add(o);
    }

    public void removeOrder_Old(Long orderId) {
        Order o;
        Iterator<Order> it;

        it = orders.iterator();
        while(it.hasNext()){
            o = it.next();
            if(o.getOrderId().equals(orderId)){
                it.remove();
            }
        }
    }

    public void removeOrder(Long orderId) {
        if(orderId == null)
            throw new OrderException("No Order ID!");
        boolean found = orders.removeIf(o -> o.getOrderId().equals(orderId));
        if(!found)
            throw new OrderException("No Order ID!");
    }

    public void addOnePiece_Old(Long orderId){
        boolean found = false;
        if(orderId == null)
            throw new OrderException("No Order ID!");
        for(Order o : orders){
            if(o.getOrderId().equals(orderId)) {
                o.setHorsepower(o.getHorsepower() + 1);
                found = true;
            }
        }
        if(!found)
            throw new OrderException("Wrong Order ID!");
    }

    public void addOnePiece(Long orderId){
        orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .forEach(order -> order.setHorsepower(order.getHorsepower()+50));
    }
}
