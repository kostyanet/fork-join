import pojo.Laureate;
import service.ApiService;
import service.StatService;

import java.util.List;

public class App {

    public static void main(String[] args) {
        try {
            List<Laureate> list = ApiService.fetchLaureates().getLaureates();
            System.out.println(
                    "Average age is " + StatService.calcAvgAgeSequentiallyBenchmarked(list) + "years"
            );
            System.out.println(
                    "Average age is " + StatService.calcAvgAgeConcurrentlyBenchmarked(list) + "years"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
