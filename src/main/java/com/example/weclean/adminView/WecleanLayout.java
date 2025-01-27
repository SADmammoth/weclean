package com.example.weclean.adminView;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@Route("admin")
@SpringComponent
@UIScope
@PageTitle("Vacuum cleaners")
public class WecleanLayout extends AppLayout {
    private final MainView mainView;
    private final VacuumCleanerEditor editor;

    @Autowired
    public WecleanLayout(MainView mainView, VacuumCleanerEditor editor) {
        this.mainView = mainView;
        this.editor=editor;

        SplitLayout layout = new SplitLayout();
        layout.addToPrimary(mainView);

        layout.addToSecondary(editor);
        layout.setSplitterPosition(75);

        setContent(layout);
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("WECLEAN CMS");
        logo.addClassName("logo");

        Anchor logout = new Anchor("logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(logo, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }
}