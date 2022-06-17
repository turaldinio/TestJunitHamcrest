import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTest {

    private List<Employee> employeeList;


    @BeforeEach
    public void listInit() {
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "John", "Smith", "USA", 25));
        employeeList.add(new Employee(2, "Ivan", "Petrov", "RU", 23));
    }

    @Test
    public void parseCsv() {
        String fileName = "data.csv";
        String[] columnMapping = new String[]{"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = Main.parseCSV(columnMapping, fileName);

        Assertions.assertEquals(employeeList, list);

    }

    @Test
    public void listToJson() {
        String result = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                "{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        String currentResult = Main.listToJson(employeeList);

        Assertions.assertEquals(result, currentResult);

    }


    @Test
    public void writeString() throws FileNotFoundException {
        String json = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                "{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        Main.writeString("data.json", json);

        Assertions.assertTrue(Files.exists(Paths.get("data.json")));

        Scanner fileReader = new Scanner(new FileReader("data.json"));

        String text = fileReader.nextLine();
        Assertions.assertEquals(json, text);

    }


}
