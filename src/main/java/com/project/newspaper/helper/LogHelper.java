package com.project.newspaper.helper;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.project.newspaper.helper.Constant.LOG_URL;
public class LogHelper {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
    public static ClassPathResource resource = new ClassPathResource("src/main/resources/log");
    public static void writeRequest(String message) throws IOException {

        String path = resource.getPath() + File.separator + "log_" + dtf.format(LocalDateTime.now()) + ".txt";

        File file = new File(path);

        if (!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (FileWriter writer = new FileWriter(file.getPath(),true)) {
            // Write the content to the file
            writer.write(message + "\n");
        }

    }
}
