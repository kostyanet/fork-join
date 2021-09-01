package pojo;

import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Laureate {
    private final static String NOW = "0000-00-00";
    private final static long DEFAULT_AGE = 79;
    private String born;
    private String died;

    public long getAge() {
        if (died == null || born == null) return DEFAULT_AGE;

        var diedDate = died.equals(NOW) ? LocalDate.now() : LocalDate.parse(died);

        return ChronoUnit.YEARS.between(
                LocalDate.parse(born.replaceAll("-00", "-01")),
                diedDate
        );
    }
}
