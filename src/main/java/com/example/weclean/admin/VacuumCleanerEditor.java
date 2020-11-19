package com.example.weclean.admin;

import com.example.weclean.domain.Manufacturer;
import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.domain.enums.CleaningFeature;
import com.example.weclean.domain.enums.Construction;
import com.example.weclean.domain.enums.DustCollectorType;
import com.example.weclean.domain.enums.PowerSource;
import com.example.weclean.repo.ManufacturerRepository;
import com.example.weclean.repo.VacuumCleanerRepository;
import com.example.weclean.service.ManufacturerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
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
    private final ManufacturerService manufacturerService;


    private VacuumCleaner vacuumCleaner;

    TextField model = new TextField("Model");
    TextField manufacturer = new TextField("Manufacturer");
    NumberField price = new NumberField("Price");
    ComboBox<Construction> construction  = new ComboBox<>("Construction");
    CheckboxGroup<CleaningFeature> cleaningFeatures = new CheckboxGroup<>();
    Checkbox hasFilter = new Checkbox("Has Filter");
    ComboBox<DustCollectorType> dustCollectorType = new ComboBox<>("Dust Collector Type");
    NumberField volumeOfDustCollector = new NumberField("Volume Of Dust Collector");;
    NumberField powerConsumption = new NumberField("Power Consumption");
    ComboBox<PowerSource> powerSource = new ComboBox<>("Power Source");
    TextField color = new TextField("Color");
    NumberField powerCordLength = new NumberField("Power Cord Length");
    NumberField weight = new NumberField("Weight");
    NumberField noiseLevel = new NumberField("Noise Level");

    Button hide = new Button("", VaadinIcon.CLOSE.create(), e->setVisible(false));

    FormLayout formLayout = new FormLayout(model, manufacturer, price, construction, cleaningFeatures, dustCollectorType, hasFilter, powerConsumption, powerSource, color, powerCordLength, weight, noiseLevel);

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");

    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<VacuumCleaner> binder = new Binder<>(VacuumCleaner.class);
    private ChangeHandler changeHandler;

    @Autowired
    public VacuumCleanerEditor(VacuumCleanerRepository repository, ManufacturerService manufacturerService) {
        this.repository = repository;
        this.manufacturerService = manufacturerService;

        setMinWidth("25vw");
        setMaxWidth("40vw");


        formLayout.getStyle().set("overflow-y", "auto");
        formLayout.getStyle().set("padding", "var(--lumo-space-m)");
        formLayout.setMaxHeight("60vh");

        cleaningFeatures.setItems(CleaningFeature.values());

        cleaningFeatures.setLabel("Cleaning features");
        cleaningFeatures.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        cleaningFeatures.setItemLabelGenerator(e->e.getName());

        construction.setItems(Construction.values());
        construction.setItemLabelGenerator(e->e.getName());

        powerSource.setItems(PowerSource.values());
        powerSource.setItemLabelGenerator(e->e.getName());

        dustCollectorType.setItems(DustCollectorType.values());
        dustCollectorType.setItemLabelGenerator(e->e.getName());

        binder.forField(manufacturer).bind(VacuumCleaner::getManufacturer, (vc, manufac)->vc.setManufacturer(manufacturerService.createManufacturer(manufac)));
        binder.bindInstanceFields(this);

        add(hide, formLayout, actions);

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