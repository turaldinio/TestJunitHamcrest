import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class MainTest extends TestCase {


    private List<Employee> employeeList;


    @BeforeEach
    public void listInit() {
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "John", "Smith", "USA", 25));
        employeeList.add(new Employee(2, "Ivan", "Petrov", "RU", 23));

    }

    @Test
    public void testParseCSV() {

        String[] columnMapping = new String[]{"id", "firstName", "lastName", "country", "age"};

        List<Employee> list = Main.parseCSV(columnMapping, "data.csv");

        Assert.assertEquals(list, employeeList);

    }

    public void testListToJson() {
        String result = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                "{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";
        String currentResult = Main.listToJson(employeeList);

        Assert.assertEquals(result, currentResult);
    }

    public void testWriteString() {
    }
}