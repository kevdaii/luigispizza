package at.spengergasse.views.delivery;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Delivery")
@Route("delivery")
@Menu(order = 2, icon = LineAwesomeIconUrl.BICYCLE_SOLID)
public class DeliveryView extends VerticalLayout {

    public DeliveryView() {
        setSpacing(true);

        H1 companyName = new H1("Luigi's Pizza");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");
        add(companyName);

        H2 subtitle = new H2("... the best pizza in the world ...");
        subtitle.getStyle()
                .set("margin", "0")
                .set("color", "gray");
        add(subtitle);

        H2 delivery = new H2("Delivery Costs");

        H2 zone0 = new H2("1st district");
        Paragraph price0 = new Paragraph("2.5 Euro");
        Paragraph free0 = new Paragraph("Free delivery for an order over 12.0 Euro");

        H2 zone1 = new H2("3rd and 4th district");
        Paragraph price1 = new Paragraph("3.5 Euro");
        Paragraph free1 = new Paragraph("Free delivery for an order over 15.0 Euro");

        H2 zone2 = new H2("8th and 7th district");
        Paragraph price2 = new Paragraph("4 Euro");
        Paragraph free2 = new Paragraph("Free delivery for an order over 16.0 Euro");

        H2 zone3 = new H2("Vienna");
        Paragraph price3 = new Paragraph("6.0 Euro");
        Paragraph free3 = new Paragraph("No free delivery");

        H2 zone4 = new H2("Lower Austria");
        Paragraph price4 = new Paragraph("20.0 Euro");
        Paragraph free4 = new Paragraph("No free delivery");

        add(delivery,
                zone0, price0, free0,
                zone1, price1, free1,
                zone2, price2, free2,
                zone3, price3, free3,
                zone4, price4, free4);
    }
}
