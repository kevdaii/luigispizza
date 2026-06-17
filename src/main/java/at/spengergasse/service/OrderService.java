package at.spengergasse.service;

import at.spengergasse.domain.Order;
import at.spengergasse.domain.OrderException;
import at.spengergasse.repository.OrderRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

        if(orderRepository.count() == 0) {
            fillTestData(30);
        }

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
        String[] MAKE = {"Nissan", "Honda", "Toyota", "Mazda", "Subaru"};

        faker = new Faker();

        for(int i = 0; i < amount; i++){
            orderDate = LocalDate.now().minusDays((int) (Math.random() * 365 * 10));
            vehicleType = VEHICLETYPE[faker.number().numberBetween(0, VEHICLETYPE.length - 1)];
            make = MAKE[faker.number().numberBetween(0, MAKE.length - 1)];
            horsepower = faker.number().numberBetween(50, 1000);
            price = faker.number().randomDouble(2, 5000, 100000);
            oldTimer = faker.bool().bool();
            order = new Order(orderDate, vehicleType, make, horsepower, price, oldTimer);
            orderRepository.save(order);
        }
    }


    public ArrayList<Order> findAll(){
        ArrayList<Order> copy = (ArrayList<Order>) orderRepository.findAll();
        return copy;
    }


    @Override
    public String toString(){
        return orderRepository.findAll().stream()
                .map(o -> o.toString())
                .collect(Collectors.joining("\n")).toString();
    }

    public void removeAllOrders() {
        orderRepository.deleteAll();
    }

    public void addOrder(Order o) {
        orderRepository.save(o);
    }

    /*
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

     */

    public void removeOrder(Long orderId) {
        if(orderId == null)
            throw new OrderException("No Order ID!");

        Optional<Order> order = orderRepository.findById(orderId);

        if(orderRepository.existsById(orderId) == false)
            throw new OrderException("Wrong Order ID!");
        orderRepository.deleteById(orderId);

    }

    /*
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

     */

    public void addOnePiece(Long orderId){
        Optional<Order> o = orderRepository.findById(orderId);

        if(o.isEmpty())
            throw new OrderException("No Order ID!");

        o.get().setHorsepower(o.get().getHorsepower()+50);
        orderRepository.save(o.get());
    }
}
