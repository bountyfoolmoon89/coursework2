package Validates;

public class ValidateUtils {

    public static String validateString(String value) {
        if (value.isBlank() | value.isEmpty()){
            throw new RuntimeException("Введите информациию");
        } else {
            return value;
        }
    }

}
