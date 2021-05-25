package com.elismarket.eshop.eshopelis.utility;

import java.time.LocalDate;
import java.util.regex.Pattern;

//checkers for mail and password
public class Checkers {

    private final String NUMBERS = "1234567890", LETTERS = "qwertyuiopasdfghjklmnbvcxz";

    public static Boolean mailChecker(String email) {
        if (email == null)
            return false;

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\" +
                "[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\" +
                ".)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }

    public static Boolean passwordChecker(String password) {
        if (password == null)
            throw new RuntimeException("Missing parameter");

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";

        Pattern pat = Pattern.compile(passwordRegex);

        return pat.matcher(password).matches();
    }

    public static Boolean isDateGreater(LocalDate date1, LocalDate date2) {
        if (date1 == null)
            throw new RuntimeException("Missing parameter");

        date2 = date2 == null ? LocalDate.now() : date2;
        return date1.isAfter(date2) || date1.isEqual(date2);
    }

    public static Boolean siglaChecker(Integer siglaResidenza) {
        return siglaResidenza < 201 && siglaResidenza > 0;
    }
}
