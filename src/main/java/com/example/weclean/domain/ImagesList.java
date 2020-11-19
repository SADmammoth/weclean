package com.example.weclean.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.io.File;
import java.io.Serializable;

public class ImagesList implements Serializable {
    private String thumb;
    private String Image600x600;
    private String[] others;



    private static final String defaultExtension = ".jpg";
    private static final String defaultBasicPath =  "/images/";

    ImagesList(int id, String extension, String basicPath){
        this.thumb = basicPath + id + "/thumb" + extension;
        this.Image600x600 = basicPath + id + "/600x600" + extension;
        this.others = this.findImages(id, basicPath);
    }

    ImagesList(int id, String extension){
        this(id, extension, defaultBasicPath);
    }

    ImagesList(int id){
        this(id, defaultExtension, defaultBasicPath);
    }


    public String getThumb() {
        return thumb;
    }

    public String getImage600x600() {
        return Image600x600;
    }

    public String[] getOthers(){return others;}

    private String[] findImages(int id, String basicPath){
        File folder = new File("./src/main/resources/static/images/" + id + "/other");
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles == null){
            return new String[0];
        }

        String[] others = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            others[i] = basicPath + id + "/other/"+ listOfFiles[i].getName();
        }

        return others;
    }

}
