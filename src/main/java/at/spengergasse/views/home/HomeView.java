package at.spengergasse.views.home;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
        setSpacing(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getHeader());

        Image img = new Image("images/logo.png", "jdm logo");
        img.setWidth("200px");
        img.setHeight("200px");

        Paragraph description = new Paragraph("Pure JDM, delivered. Premium Japanese vehicle imports, seamlessly sourced and handled from the auctions in Tokyo to your garage.");
        description.setWidth("500px");
        description.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

        HorizontalLayout desc = new HorizontalLayout(img, description);
        add(desc);

        H3 name = new H3("JapaneseCarImport GmbH");
        H3 street = new H3 ("Goldschmiedgasse 10");
        H3 city = new H3 ("1010 Vienna");

        HorizontalLayout address = new HorizontalLayout(name, street, city);
        address.getStyle()
                    .set("pag", "40px");
        add(address);
    }

    public static Component getHeader(){
        VerticalLayout header;
        H1 companyName = new H1("Japanese Car Import");
        companyName.getStyle()
                .set("font-family", "'Rajdhani', 'Oswald', sans-serif")
                .set("font-size", "4rem")
                .set("font-weight", "600")
                .set("text-transform", "uppercase")
                .set("margin", "0");

        H2 subtitle = new H2("Premium JDM Sourcing");
        subtitle.getStyle()
                .set("font-family", "'Inter', 'Roboto', sans-serif")
                .set("font-weight", "400")
                .set("margin", "0")
                .set("color", "gray")
                .set("letter-spacing", "2px");

        header = new VerticalLayout(companyName, subtitle);
        header.setSpacing(false);
        header.setPadding(false);
        header.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        return header;
    }
}
