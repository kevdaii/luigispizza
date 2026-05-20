package at.spengergasse.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "The price is required!")

    @PastOrPresent(message = "Order date must be now or in the past")
    private LocalDate   orderDate = LocalDate.now();

    @NotBlank(message = "The pizza is required!")
    @Size(min=2, max=20, message = "Wrong Pizza!")
    private String      pizza = "Salami";

    @NotNull(message = "Size is required")
    @Pattern(regexp = "Small|Medium|Large|Family", message = "Wrong size. Small|Medium|Large|Family")
    private String      size = "Medium";

    @NotNull(message = "The quantity is required")
    @Min(value = 1, message = "Min 1 Pizza")
    @Max(value = 5, message = "Max 5 Pizzas")
    private Integer     quantity = 1;

    @NotNull(message = "The price is required!")
    @DecimalMin(value = "5", message = "The min price is 5 EUR!")
    private Double      price = 9.0;

    @NotNull(message = "The garlic is requirde")
    private Boolean     garlic = false;

    private static final AtomicLong sequence = new AtomicLong(1000);

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

    @Override
    public Order clone(){
        return new Order(orderId, orderDate, pizza, size, quantity, price, garlic);
    }
}
