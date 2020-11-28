package com.example.weclean.admin;


import ch.qos.logback.core.Layout;
import com.example.weclean.helpers.Helpers;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;

public class ImageGallery extends VerticalLayout {
    private String[] images;
    private int index;
    private int count;

    Button prev = new Button(VaadinIcon.ARROW_CIRCLE_LEFT.create());
    Button next = new Button(VaadinIcon.ARROW_CIRCLE_RIGHT.create());
    Div imageContainer = new Div();

    public ImageGallery(String[] images){
        this();
        this.images = images;
        Arrays.stream(images).forEach(this::addImage);
    }

    public ImageGallery(){
        setWidth("100%");
        imageContainer.setHeight("20vh");
        prev.addClickListener(e->showPrev());
        next.addClickListener(e->showNext());

        HorizontalLayout controls = new HorizontalLayout(prev,next);
        add(imageContainer, controls);
    };

    public void setImages(String[] images) {
        this.images = images;
        index = 0;
        count = images.length;
        if(count != 0){
            addImage(index);
        }
    }

    public void showNext(){
        if(index < count-1){
            index++;
            addImage(index);
        }
    }

    public void showPrev(){
        if(index > 0){
            index--;
            addImage(index);
        }
    }

    private void addImage(String path){
        Image image = new Image();
        image.setSrc(Helpers.getPublicPath(path));
        image.setHeight("15vh");
        image.getStyle().set("border", "1px solid bold");
        imageContainer.removeAll();
        imageContainer.addComponentAsFirst(image);
    }

    private void addImage(int index){
        this.addImage(images[index]);
    }
}
