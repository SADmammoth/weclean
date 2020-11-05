package com.example.weclean.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.io.Serializable;

public class ImagesList implements Serializable {
    private String thumb;
    private String Image600x600;



    private static final String defaultExtension = ".jpg";
    private static final String defaultBasicPath =  "/files/images/";

    ImagesList(int id, String extension, String basicPath){
        this.thumb = basicPath + id + "/thumb" + extension;
        this.Image600x600 = basicPath + id + "/600x600" + extension;

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

}
