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

        Component card0 = getCard("EUROPE", 1500.0);
        Component card1 = getCard("NORTH AMERICA", 2500.0);
        Component card2 = getCard("AUSTRALIA", 1600.0);
        Component card3 = getCard("ASIA", 1200.0);
        Component card4 = getCard("AFRICA", 2000.0);

        FlexLayout cards = new FlexLayout(card0, card1, card2, card3, card4);
        cards.setWidthFull();
        cards.setJustifyContentMode(JustifyContentMode.CENTER);
        cards.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        add(cards);

        Paragraph info = new Paragraph("From the Japanese auction block to your driveway in 6 to 8 weeks. We handle the shipping, customs, and full local compliance.");
        info.setWidth("100%");
        info.getStyle().set("text-align", "center");
        add(info);
    }

    private Component getCard(String sellingZone, double deliveryPrice){
        Paragraph subtitle;

        H2 zone = new H2(sellingZone);
        Paragraph price = new Paragraph(deliveryPrice + " Dollar");

        if(sellingZone == "EUROPE")
            subtitle = new Paragraph("Typical arrival via Bremerhaven or Koper. Transit: 6-8 weeks.\n (These are the standard entry ports for vehicles heading into Central Europe and Austria)");
        else if(sellingZone == "NORTH AMERICA")
            subtitle = new Paragraph("RoRo shipping to major East and West Coast ports. Transit: 4-6 weeks.");
        else if(sellingZone == "AUSTRALIA")
            subtitle = new Paragraph("Includes mandatory bio-security cleaning preparation. Transit: 3-5 weeks.");
        else if(sellingZone == "ASIA")
            subtitle = new Paragraph("Shortest maritime transit times. Est: 1-3 weeks.");
        else if(sellingZone == "AFRICA")
            subtitle = new Paragraph("Serving major Eastern and Southern ports (e.g., Mombasa, Durban).");
        else subtitle = new Paragraph("No free delivery");

        VerticalLayout card = new VerticalLayout(zone, price, subtitle);
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
