package at.spengergasse.domain;


import jakarta.persistence.*;
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
@Table(name = "car_order")
public class Order implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        orderId;

    @NotNull(message = "The price is required!")

    @PastOrPresent(message = "Order date must be now or in the past")
    private LocalDate   orderDate = LocalDate.now();

    @NotBlank(message = "The vehicleType is required!")
    @Size(min=2, max=20, message = "Wrong Type!")
    private String      vehicleType = "Coupe";

    @NotNull(message = "Make is required")
    @Pattern(regexp = "Nissan|Toyota|Mazda|Subaru|Honda", message = "Wrong make. Nissan|Toyota|Mazda|Subaru|Honda")
    private String      make = "Mazda";

    @NotNull(message = "The horsepower is required")
    @Min(value = 50, message = "Min 50")
    @Max(value = 1000, message = "Max 1000")
    private Integer     horsepower = 280;

    @NotNull(message = "The price is required!")
    @DecimalMin(value = "5000", message = "The min price is 5000 EUR!")
    private Double      price = 5000.0;

    @NotNull(message = "The oldTimer is required!")
    private Boolean     oldTimer = true;

    public Order(Long orderId, LocalDate orderDate, String vehicleType, String make, Integer horsepower, Double price, Boolean oldTimer){
        setOrderId(orderId);
        setOrderDate(orderDate);
        setVehicleType(vehicleType);
        setMake(make);
        setHorsepower(horsepower);
        setPrice(price);
        setOldTimer(oldTimer);
    }

    public Order(LocalDate orderDate, String vehicleType, String make, Integer horsepower, Double price, Boolean oldTimer){
        setOrderDate(orderDate);
        setVehicleType(vehicleType);
        setMake(make);
        setHorsepower(horsepower);
        setPrice(price);
        setOldTimer(oldTimer);
    }

    public Order() {}

    public void setPrice(Double price){
        if (price.doubleValue() < 5000)
            throw new OrderException("Price to low!");
        this.price = price;
    }

    @Override
    public Order clone(){
        return new Order(orderId, orderDate, vehicleType, make, horsepower, price, oldTimer);
    }
}
