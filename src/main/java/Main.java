import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        String fileName = "data.csv";
        String[] columnMapping = new String[]{"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = parseCSV(columnMapping, fileName);

        String json = listToJson(list);

        writeString("data.json", json);


    }

    public static List<Employee> parseCSV(String[] columnMapping, String filename) {
        List<Employee> list = null;

        try (CSVReader csvReader = new CSVReader(new FileReader(filename))) {

            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            list = csv.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static <T> String listToJson(List<T> list) {
        Type listType = new TypeToken<List<T>>() {
        }.getType();

        return new Gson().toJson(list, listType);

    }

    public static void writeString(String path, String json) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
