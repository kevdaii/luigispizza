package at.spengergasse.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity
public class Order implements Cloneable{
    @Id
    private Long        orderId;
    private LocalDate   orderDate;
    private String      pizza;
    private String      size;
    private Integer     quantity;
    private Double      price;
    private Boolean     garlic;

    private static final AtomicLong sequence = new AtomicLong(1000);
    private static final String[] sizes = {"Small", "Medium", "Large", "Family"};

    public Order(Long orderId, LocalDate orderDate, String pizza, String size, Integer quantity, Double price, Boolean garlic){
        setOrderId(orderId);
        setOrderDate(orderDate);
        setPizza(pizza);
        setSize(size);
        setQuantity(quantity);
        setPrice(price);
        setGarlic(garlic);
    }

    public Order(LocalDate orderDate, String pizza, String size, Integer quantity, Double price, Boolean garlic){
        setOrderId();
        setOrderDate(orderDate);
        setPizza(pizza);
        setSize(size);
        setQuantity(quantity);
        setPrice(price);
        setGarlic(garlic);
    }

    public Order() {}

    public void setOrderId(){
        orderId = sequence.getAndIncrement();
    }

    public void setPrice(Double price){
        if (price.doubleValue() < 5)
            throw new OrderException("Price to low!");
        this.price = price;
    }

    public void setSize(String size){
        if(!Arrays.asList(sizes).contains(size))
            throw new OrderException("Wrong size!");
        this.size = size;
    }

    @Override
    public Order clone(){
        return new Order(orderId, orderDate, pizza, size, quantity, price, garlic);
    }
}
