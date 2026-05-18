package at.spengergasse.views.delivery;

import at.spengergasse.views.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.OptionalDouble;

@PageTitle("Delivery")
@Route("delivery")
@Menu(order = 2, icon = LineAwesomeIconUrl.BICYCLE_SOLID)
public class DeliveryView extends VerticalLayout {

    public DeliveryView() {
        setSpacing(true);

        add(HomeView.getHeader());

        H2 delivery = new H2("Delivery Costs");

        add(delivery);

        Component card0 = getCard("1st district", 2.5, OptionalDouble.of(12.0));
        Component card1 = getCard("3rd and 4th district", 3.5, OptionalDouble.of(15.0));
        Component card2 = getCard("7th and 8th district", 4.0, OptionalDouble.of(16.0));
        Component card3 = getCard("Vienna", 6.0, OptionalDouble.empty());
        Component card4 = getCard("Lower Austria", 20.0, OptionalDouble.empty());

        FlexLayout cards = new FlexLayout(card0, card1, card2, card3, card4);
        cards.setWidthFull();
        cards.setJustifyContentMode(JustifyContentMode.CENTER);
        cards.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        add(cards);

        Paragraph info = new Paragraph("We delivery to you within 30 to 90 minutes - fresh and hot.");
        info.setWidth("100%");
        info.getStyle().set("text-align", "center");
        add(info);
    }

    private Component getCard(String sellingZone, double deliveryPrice, OptionalDouble deliveryFee){
        Paragraph fee;

        H2 zone = new H2(sellingZone);
        Paragraph price = new Paragraph(deliveryPrice + " Euro");
        if(deliveryFee.isPresent())
            fee = new Paragraph("Free delivery for an order over " + deliveryFee.getAsDouble() + "Euro");
        else
            fee = new Paragraph("No free delivery");

        VerticalLayout card = new VerticalLayout(zone, price, fee);
        card.setWidth("350px");
        card.setPadding(true);
        card.setSpacing(false);

        card.getStyle()
                .set("border", "1px solid lightgray")
                .set("border-radius", "10px")
                .set("margin", "10px");
        return card;
    }
}
