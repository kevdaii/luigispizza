package at.spengergasse.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price){
        if (price.doubleValue() < 5000)
            throw new OrderException("Price to low!");
        this.price = price;
    }

    public Boolean getOldTimer() {
        return oldTimer;
    }

    public void setOldTimer(Boolean oldTimer) {
        this.oldTimer = oldTimer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", vehicleType='" + vehicleType + '\'' +
                ", make='" + make + '\'' +
                ", horsepower=" + horsepower +
                ", price=" + price +
                ", oldTimer=" + oldTimer +
                '}';
    }

    @Override
    public Order clone(){
        return new Order(orderId, orderDate, vehicleType, make, horsepower, price, oldTimer);
    }
}