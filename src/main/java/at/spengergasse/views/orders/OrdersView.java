package at.spengergasse.views.orders;

import at.spengergasse.domain.Order;
import at.spengergasse.service.OrderService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Orders")
@Route("orders")
@Menu(order = 1, icon = LineAwesomeIconUrl.PIZZA_SLICE_SOLID)
public class OrdersView extends VerticalLayout {
    private final Grid<Order> grid = new Grid<>(Order.class, true);
    private final OrderService orderService;

    public OrdersView(@Autowired OrderService orderService) {
        this.orderService = orderService;
        setSpacing(false);

        setSizeFull();
        grid.setSizeFull();
        add(grid);
        reload();
    }

    private void reload(){
        grid.setItems(orderService.findAll());
    }
}
