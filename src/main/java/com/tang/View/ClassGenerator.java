package com.tang.View;

import com.tang.Service.CreateFileService;
import com.tang.com.tang.NotFoundFileException;

/**
 * @author ASUS
 * @create 2018-12-27 16:50
 */
public class ClassGenerator {

    private CreateFileService createFileService = new CreateFileService();

    public void start(){
        createFileService.start();
    }

}
