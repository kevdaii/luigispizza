package at.spengergasse.views.orders;

import at.spengergasse.domain.Order;
import at.spengergasse.domain.OrderException;
import at.spengergasse.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import java.text.DecimalFormat;

import java.time.LocalDate;

@PageTitle("Orders")
@Route("orders")
@Menu(order = 1, icon = LineAwesomeIconUrl.CAR_ALT_SOLID)
public class OrdersView extends VerticalLayout {
    private final Button buttonRemoveAllOrders = new Button("Remove all orders");
    private final Button buttonAdd10Orders = new Button("Add 10 orders");
    private final Button buttonAddWrong = new Button("Add wrong order");
    private final Button buttonAdd1Order = new Button("Add order");

    private final Grid<Order> grid = new Grid<>(Order.class, false); // set true if you don't wanna do grid.addColumn...
    private final OrderService orderService;

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,##0.00'$'");

    public OrdersView(@Autowired OrderService orderService) {
        this.orderService = orderService;
        setSpacing(true);

        buttonRemoveAllOrders.addClickListener(e -> removeAllOrders());
        buttonAdd10Orders.addClickListener(e -> add10Orders());
        buttonAddWrong.addClickListener(e -> addWrongOrder());
        buttonAdd1Order.addClickListener(e -> add1Order());

        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAllOrders, buttonAdd10Orders,buttonAddWrong, buttonAdd1Order);
        buttons.setSpacing(true);
        setSizeFull();

        grid.addColumn(o -> o.getOrderId())
                        .setHeader("Order ID")
                        .setSortable(true);
        grid.addColumn(o -> o.getOrderDate())
                        .setHeader("Order Date")
                        .setSortable(true);
        /*
        grid.addColumn(o -> o.getVehicleType())
                        .setHeader("Vehicle Type")
                        .setSortable(true);

         */
        grid.addComponentColumn(o -> {
                    Span name = new Span(o.getVehicleType());

                    Image preview = new Image("images/" + o.getVehicleType().toLowerCase() + ".png", o.getVehicleType());
                    preview.setWidth("150px");

                    Popover popover = new Popover();
                    popover.setTarget(name);
                    popover.setOpenOnHover(true);
                    popover.add(preview);

                    return new HorizontalLayout(name, popover);
                })
                .setHeader("Vehicle Type")
                .setComparator(o -> o.getVehicleType())
                .setSortable(true);

        grid.addColumn(o -> o.getMake())
                        .setHeader("Make")
                        .setSortable(true);
        Image slice = new Image("icons/horsepower.png", "horse");
        slice.setWidth("20px");
        grid.addColumn(o -> o.getHorsepower())
                        .setHeader(new HorizontalLayout(slice, new Span("HP")))
                        .setSortable(true);

        /*
        grid.addColumn(o -> o.getPrice())
                        .setHeader("Price")
                        .setSortable(true);

         */

        grid.addColumn(o -> PRICE_FORMAT.format(o.getPrice()))
                .setHeader("Price")
                .setComparator(o -> o.getPrice())
                .setSortable(true);

        // ? = if o.getOldtimer() == true
        //        return "Y"
        //     else
        //        return "N"
        /*
        grid.addColumn(o -> o.getOldTimer() ? "Y" : "N")
                        .setHeader("Old Timer")
                        .setSortable(true);

         */

        grid.addComponentColumn(o -> {
            Checkbox cb;
            cb = new Checkbox(o.getOldTimer());
            cb.setReadOnly(true);
            return cb; })
                .setHeader("OldTimer")
                .setSortable(true);

        grid.addComponentColumn(o -> {
            Button remove = new Button("Delete Order");
            remove.addClickListener(e -> {removeOrder(o.getOrderId());});
            return remove;})
                        .setHeader("Action")
                        .setSortable(false);

        grid.addComponentColumn(o -> {
            Button add1 = new Button("One more");
            add1.addClickListener(e -> addOnePiece(o.getOrderId()));
            return add1;})
                .setHeader("Action")
                .setSortable(false);

        grid.setSizeFull();
        add(buttons);
        add(grid);
        reload();
    }

    private void add1Order() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add 1 Order");

        TextField orderId = new TextField("Order ID");
        orderId.setReadOnly(true);
        DatePicker orderDate = new DatePicker("Order Date");
        TextField vehicleType = new TextField("Vehicle Type");
        ComboBox<String> make = new ComboBox<>();
        make.setItems("NISSAN", "HONDA", "TOYOTA", "MAZDA", "SUBARU");
        IntegerField horspower = new IntegerField("Horsepower");
        NumberField price = new NumberField("Price");
        Checkbox oldTimer = new Checkbox("Old Timer");

        BeanValidationBinder<Order> binder = new BeanValidationBinder<>(Order.class);
        binder.forField(orderDate)
                .bind("orderDate");
        binder.forField(vehicleType)
                .bind("vehicleType");
        binder.forField(make)
                .bind("make");
        binder.forField(horspower)
                .bind("horsepower");
        binder.forField(price)
                .bind("price");
        binder.forField(oldTimer)
                .bind("oldTimer");

        Order order = new Order();
        order.setOrderId();
        orderId.setValue("" + order.getOrderId());

        binder.setBean(order);

        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");

        ok.addClickListener(e -> {
            if(binder.validate().isOk()){
                orderService.addOrder(order);
                reload();
                dialog.close();
                Notification.show("Order added!");
            }else{
                Notification.show("Invalid Order!");
            }
        });

        cancel.addClickListener(e -> {
            dialog.close();
            Notification.show("Order cancelled!");
        });

        HorizontalLayout buttons = new HorizontalLayout(ok, cancel);

        VerticalLayout formLayout = new VerticalLayout(orderId, orderDate, vehicleType, make, horspower, price, oldTimer, buttons);

        dialog.add(formLayout);
        dialog.open();



    }

    private void addOnePiece(Long orderId) {
        try{
            orderService.addOnePiece(orderId);
            reload();
        }catch (OrderException e){
            Notification.show("Error adding one piece!");
        }

    }

    private void removeOrder(Long orderId) {
        try{
            orderService.removeOrder(orderId);
            reload();
        }catch (OrderException e){
            Notification.show("Error removing order!");
        }

    }

    private void addWrongOrder() {
        try{
            Order o = new Order(LocalDate.now(), "SUV", "NISSAN", 580, 4999.0, true);
            orderService.addOrder(o);
        }catch (OrderException e){
           Notification.show(e.getMessage());
        }
    }

    private void add10Orders() {
        try{
            orderService.fillTestData(10);
            buttonRemoveAllOrders.setEnabled(true);
            reload();
        }catch (Exception e){
            Notification.show(e.getMessage());
        }
    }

    public void removeAllOrders() {
        try{
            orderService.removeAllOrders();
            buttonRemoveAllOrders.setEnabled(false);
            reload();
        }catch (Exception e){
            Notification.show(e.getMessage());
        }
    }

    private void reload(){
        grid.setItems(orderService.findAll());
    }
}
