package com.example.weclean.admin;

import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.repo.VacuumCleanerRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@SpringComponent
@UIScope
public class MainView extends VerticalLayout {

    private final VacuumCleanerRepository repo;
    private final VacuumCleanerEditor editor;
    final Grid<VacuumCleaner> grid;

    final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public MainView(VacuumCleanerRepository repo, VacuumCleanerEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(VacuumCleaner.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New vacuum cleaner", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "model", "manufacturer", "price");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter");


        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listVacuumCleaners(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editVacuumCleaner(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editVacuumCleaner(new VacuumCleaner()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listVacuumCleaners(filter.getValue());
        });

        // Initialize listing
        listVacuumCleaners(null);
    }

    // tag::listCustomers[]
    void listVacuumCleaners(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            grid.setItems(StreamSupport.stream(repo.findAll().spliterator(), false));
        }else{
            grid.setItems(StreamSupport.stream(repo.findAllByManufacturerIgnoreCaseContainingOrModelIgnoreCaseContaining(filterText,filterText).spliterator(), false));
        }
    }
    // end::listCustomers[]
}