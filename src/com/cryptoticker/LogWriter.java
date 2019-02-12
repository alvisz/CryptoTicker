package com.cryptoticker;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class LogWriter {
    LogWriter(ArrayList<Crypto> list){
        String filename = "./CryptoLog.txt";
        String placeholder = "----------CRYPTO LOG----------";
        String header = "Time: " + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        writeFile(placeholder,filename,false);
        writeFile(header,filename,false);
        Iterator<Crypto> it = list.iterator();
        while (it.hasNext()) {
            Crypto item = it.next();
            String stringToWrite = "Name: " + item.getName()+", Price: " + item.getPrice()+"$";
            writeFile(stringToWrite,filename,false);
            it.remove();
        }
    }
    public static void writeFile(String inputText, String fileName, boolean clearFile){
        String enter = System.lineSeparator();
        String text = inputText.concat(enter); // Pieliks tekstam enteru
        StandardOpenOption options = APPEND;
        if (clearFile){
            options = TRUNCATE_EXISTING;
        }
        byte data[] = text.getBytes(); // Parveidos uz bitiem
        Path path = Paths.get(fileName); // Var izvēlēties, kādā filename likt... filename jādefinē, kā "./tekstafails.txt", līdz ar to viss notiksies src mapē.
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, options))) {
            // CREATE izveidos failu, ja tāds nebūs...
            // APPEND kabinās viņam galā visu.
            // TRUNCATE_EXISTING nodzēsīs visu un sāks no 0.
            out.write(data,0,data.length);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}

