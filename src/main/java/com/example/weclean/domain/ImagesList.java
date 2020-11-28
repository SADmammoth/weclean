package com.example.weclean.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;

public class ImagesList implements Serializable {
    private String thumb;
    private String Image600x600;
    private String[] others = new String[]{};


    private final int id;
    private String extension;
    private final String basicPath;

    ImagesList(int id, String extension, String basicPath){
        this.extension = extension;
        this.basicPath = basicPath;
        this.id = id;
        this.thumb = basicPath + id + "/thumb" + extension;
        this.Image600x600 = basicPath + id + "/600x600" + extension;
        findImages();
    }

    ImagesList(int id, String extension){
        this(id, extension, "/images/");
    }

    ImagesList(int id){
        this(id, ".jpg", "/images/");
    }


    public String getThumb() {
        return thumb;
    }

    public String getImage600x600() {
        return Image600x600;
    }

    public String[] getOthers(){return others;}

    public void findImages(){
        File folder = new File("./src/main/resources/static/images/" + id + "/other");
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles == null){
            return;
        }

        String[] others = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            others[i] = basicPath + id + "/other/"+ listOfFiles[i].getName();
        }

        this.others = others;
    }


    public void setExtension(String extension) {
        this.extension = extension;
    }
}
