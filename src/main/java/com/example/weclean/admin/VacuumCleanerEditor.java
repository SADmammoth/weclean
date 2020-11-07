package com.example.weclean.admin;

import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.repo.VacuumCleanerRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.swing.*;

@SpringComponent
@UIScope
public class VacuumCleanerEditor extends VerticalLayout implements KeyNotifier {

    private final VacuumCleanerRepository repository;


    private VacuumCleaner vacuumCleaner;

    TextField model = new TextField("Model");
    TextField manufacturer = new TextField("Manufacturer");
    NumberField price = new NumberField("Price");

    Button hide = new Button("", VaadinIcon.CLOSE.create(), e->setVisible(false));

    FormLayout formLayout = new FormLayout(model, manufacturer, price);

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");

    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<VacuumCleaner> binder = new Binder<>(VacuumCleaner.class);
    private ChangeHandler changeHandler;

    @Autowired
    public VacuumCleanerEditor(VacuumCleanerRepository repository) {
        this.repository = repository;

        setMinWidth("25vw");
        setMaxWidth("40vw");

        add(hide, formLayout, actions);

        binder.bindInstanceFields(this);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editVacuumCleaner(vacuumCleaner));
        setVisible(false);
    }

    void delete() {
        repository.delete(vacuumCleaner);
        changeHandler.onChange();
    }

    void save() {
        repository.save(vacuumCleaner);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editVacuumCleaner(VacuumCleaner c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != 0;
        if (persisted) {
            vacuumCleaner = repository.findById(c.getId()).get();
        }
        else {
            vacuumCleaner = c;
        }

        cancel.setVisible(persisted);


        binder.setBean(vacuumCleaner);

        setVisible(true);

        model.focus();
    }

    public void setChangeHandler(ChangeHandler h) {

        changeHandler = h;
    }
}