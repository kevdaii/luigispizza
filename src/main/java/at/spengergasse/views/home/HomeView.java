package at.spengergasse.views.home;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(false);

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

        Image img = new Image("images/logo.png", "pizza logo");
        img.setWidth("200px");
        add(img);

        Paragraph description = new Paragraph("Craving authentic, stone-baked pizza? Luigi’s Pizza serves up fresh, hand-tossed pies made with premium ingredients and a whole lot of love. Order online now!");
        description.setWidth("500px");
        description.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");
        add(description);


        H3 name = new H3("Luigi's Pizza GmbH");
        H3 street = new H3 ("Goldschmiedgasse 10");
        H3 city = new H3 ("1010 Vienna");
        add(name, street, city);
    }
}
