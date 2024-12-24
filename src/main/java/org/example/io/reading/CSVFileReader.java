package org.example.io.reading;

import org.example.models.Report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CSVFileReader {
    private final String filePath;

    public CSVFileReader(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Report> readFile() {
        ArrayList<Report> reports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length == 3) {
                    String guid = parts[0].trim();
                    String codeName = parts[1].trim();
                    String description = parts[2].trim();
                    reports.add(new Report(guid, codeName, description));
                }
                else {
                    throw new IOException("Не коректные данные в файле");
                }
            }
        }
        catch (IOException exception) {
            System.out.println("Ошибка чтения CSV файла: " + exception);
        }
        return reports;
    }

}
