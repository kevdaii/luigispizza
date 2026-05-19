package at.spengergasse.views.orders;

import at.spengergasse.domain.Order;
import at.spengergasse.domain.OrderException;
import at.spengergasse.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.time.LocalDate;

@PageTitle("Orders")
@Route("orders")
@Menu(order = 1, icon = LineAwesomeIconUrl.PIZZA_SLICE_SOLID)
public class OrdersView extends VerticalLayout {
    private final Button buttonRemoveAllOrders = new Button("Remove all orders");
    private final Button buttonAdd10Orders = new Button("Add 10 orders");
    private final Button buttonAddWrong = new Button("Add wrong order");

    private final Grid<Order> grid = new Grid<>(Order.class, true);
    private final OrderService orderService;

    public OrdersView(@Autowired OrderService orderService) {
        this.orderService = orderService;
        setSpacing(true);

        buttonRemoveAllOrders.addClickListener(e -> removeAllOrders());
        buttonAdd10Orders.addClickListener(e -> add10Orders());
        buttonAddWrong.addClickListener(e -> addWrongOrder());

        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAllOrders, buttonAdd10Orders,buttonAddWrong);
        buttons.setSpacing(true);
        setSizeFull();
        grid.setSizeFull();
        add(buttons);
        add(grid);
        reload();
    }

    private void addWrongOrder() {
        try{
            Order o = new Order(LocalDate.now(), "Salami", "Large", 1, 3.0, true);
            orderService.addOrder(o);
        }catch (Exception e){
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
