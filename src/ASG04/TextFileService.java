package ASG04;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";
    public static ArrayList<String[]> readFile(String filePath) {
        ArrayList<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(COMMA_DELIMITER);
                rows.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static void writeFile(String filePath, ArrayList<String[]> data) {
        File file = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String[] row : data) {
                String line = String.join(COMMA_DELIMITER, row);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyFile(String filePath, String[] newData, int row, int column) {
        ArrayList<String[]> data = readFile(filePath);
        data.get(row)[column] = Arrays.toString(newData);
        writeFile(filePath, data);
    }
}

