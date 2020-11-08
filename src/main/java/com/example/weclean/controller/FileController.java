package com.example.weclean.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class FileController {

    @RequestMapping(path = {"/files", "/files/{fileName}"}, method = RequestMethod.GET)
    public ResponseEntity files(@PathVariable(required = false) String fileName, @RequestParam(required = false) String filePath) throws IOException {
        String path;
        if(fileName != null){
            path = fileName;
        }else{
            path = filePath;
        }

        File file = new File("./src/main/resources/static/" + path);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        InputStreamResource resource;
        try {
            resource= new InputStreamResource(new FileInputStream(file));
        }
        catch(FileNotFoundException exception){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
