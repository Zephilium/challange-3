import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Challange3Test {
    String file;
    List<Integer> dataCsvTest;
    List<Integer> data;

    @BeforeEach
    void setup() {
        file = "src/test/java/data_sekolah_test.csv";
        dataCsvTest = Arrays.asList(7, 7, 8, 9, 7, 8, 9, 10, 7, 7, 8, 8, 9, 7, 8, 8, 7, 7, 9, 10, 7, 7, 7, 8, 8, 8, 9, 7, 8, 10);
        data = new ArrayList<>(dataCsvTest);
    }

    @Test
    @DisplayName("Positive Test Case")
    void positiveTest() throws FileNotFoundException {

//        Find Data
        Assertions.assertEquals(data, Challange3.getValue(file));

//        Average
        double sum = 0;
        for (int a : data) {
            sum += a;
        }

        String duaKoma = String.format("%.2f", sum / data.size());
        double avg = Double.parseDouble(duaKoma);
        Assertions.assertEquals(avg, Challange3.findAverage(data));

//        Median
        Collections.sort(data);

        double median;
        if (data.size() % 2 == 0)
            median = ((double) data.get(data.size() / 2) + (double) data.get(data.size() / 2 - 1)) / 2;
        else
            median = (double) data.get(data.size() / 2);
        median = (int) median;
        Assertions.assertEquals(median, Challange3.findMedian(data));

//        Modus
        int max = 1;
        int max_value = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int n : data) {
            if (hm.get(n) != null) {
                hm.put(n, hm.get(n) + 1);

                if (hm.get(n) > max) {
                    max = hm.get(n);
                    max_value = n;
                }
            } else {
                hm.put(n, 1);
            }
        }

        Assertions.assertEquals(max_value, Challange3.findModus(data));

    }

    @Test
    @DisplayName("Negative Test Case")
    void negativeTest() {
        Exception e = Assertions.assertThrows(FileNotFoundException.class, () -> Challange3.getValue("asd.csv"));
        Assertions.assertEquals("File tidak ditemukan", e.getMessage());
    }
}
