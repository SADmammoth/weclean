package com.example.weclean.admin;

import com.example.weclean.domain.ImagesList;
import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.domain.enums.CleaningFeature;
import com.example.weclean.domain.enums.Construction;
import com.example.weclean.domain.enums.DustCollectorType;
import com.example.weclean.domain.enums.PowerSource;
import com.example.weclean.helpers.Helpers;
import com.example.weclean.repo.VacuumCleanerRepository;
import com.example.weclean.service.ManufacturerService;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import elemental.json.Json;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@SpringComponent
@UIScope
public class VacuumCleanerEditor extends VerticalLayout implements KeyNotifier {

    private final VacuumCleanerRepository repository;
    private final ManufacturerService manufacturerService;


    private VacuumCleaner vacuumCleaner;

    MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
    Upload upload = new Upload(buffer);
    MemoryBuffer mainBuffer = new MemoryBuffer();
    Upload mainImage = new Upload(mainBuffer);

    Details cover = new Details();
    Image coverImage = new Image();
    Details details = new Details();
    ImageGallery images = new ImageGallery();
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

    FormLayout formLayout = new FormLayout(cover, details, model, manufacturer, price, construction, cleaningFeatures, dustCollectorType, hasFilter, powerConsumption, powerSource, color, powerCordLength, weight, noiseLevel);

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

        upload.setAcceptedFileTypes("image/jpeg");
        mainImage.setAcceptedFileTypes("image/jpeg");

        mainImage.addSucceededListener(event -> {
            InputStream stream = mainBuffer.getInputStream();

            try {
                String subtype = MimeTypeUtils.parseMimeType(event.getMIMEType()).getSubtype();
                byte[] bytes = IOUtils.toByteArray(stream);
                File file = new File("./src/main/resources/static/images/"+vacuumCleaner.getId()+ "/600x600" + '.' + "jpg");

                FileUtils.writeByteArrayToFile(file, bytes);
                BufferedImage bi = ImageIO.read(file);


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(Helpers.getImageThumb(bi, subtype), subtype, baos);

                File file2 = new File("./src/main/resources/static/images/"+vacuumCleaner.getId()+ "/thumb" + '.' + "jpg");
                FileUtils.writeByteArrayToFile(file2, baos.toByteArray());

                vacuumCleaner.getImagesList().setExtension(subtype);
                coverImage.setSrc( new StreamResource(
                        event.getFileName(), () -> new ByteArrayInputStream(bytes)));
                mainImage.getElement().setPropertyJson("files", Json.createArray());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        upload.addSucceededListener(event -> {
            InputStream stream = buffer.getInputStream(event.getFileName());

            try {

                String subtype = MimeTypeUtils.parseMimeType(event.getMIMEType()).getSubtype();
                byte[] bytes = IOUtils.toByteArray(stream);
                ImagesList imagesList = vacuumCleaner.getImagesList();
                int count = 0;
                if(imagesList != null && imagesList.getOthers() != null){
                    count = imagesList.getOthers().length;

                }
                count++;

                File file = new File("./src/main/resources/static/images/"+vacuumCleaner.getId()+"/other/"+ "image" + count + '.' + subtype);

                FileUtils.writeByteArrayToFile(file, bytes);

                vacuumCleaner.getImagesList().findImages();
                images.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        formLayout.getStyle().set("overflow-y", "auto");
        formLayout.getStyle().set("padding", "var(--lumo-space-m)");
        formLayout.setMaxHeight("60vh");
        coverImage.setHeight("20vh");

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
        details.setSummaryText("Images");
        details.addContent(images, upload);
        cover.setSummaryText("Main image");
        cover.addContent(coverImage, mainImage);
        cover.setOpened(true);

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

        coverImage.setSrc(Helpers.getPublicPath(vacuumCleaner.getImagesList().getImage600x600()));

        setImages();

        setVisible(true);

        model.focus();
    }

    public void setChangeHandler(ChangeHandler h) {

        changeHandler = h;
    }

    public void setImages(){
        if(vacuumCleaner.getImagesList().getOthers().length == 0){
            images.setVisible(false);
            return;
        }
        images.setImages(vacuumCleaner.getImagesList().getOthers());
    }
}