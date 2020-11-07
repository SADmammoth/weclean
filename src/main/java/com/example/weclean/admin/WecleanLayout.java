package com.example.weclean.admin;

import com.example.weclean.domain.VacuumCleaner;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Route("admin")
@SpringComponent
@UIScope
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
    }
}