package com.backendproject.PostBook_App_API.Services.impl;

import com.backendproject.PostBook_App_API.Services.Fileservice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements Fileservice {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // File name
        String name = file.getOriginalFilename();

        // preventing duplicate filename by Generating random name
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));  // ex-->randomID.jpg or randomID.png etc


        //FullPath
        String filePath = path + File.separator + fileName1; // assume it as location upto image folder.



        // creating folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();            // creating directory
        }

        // data Copying on filePath
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream is= new FileInputStream(fullPath);
           return is;
    }
}
