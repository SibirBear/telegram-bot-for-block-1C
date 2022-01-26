package info.fermercentr.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Класс для проверки корректности(в соотвествии с установленными масками)
 * введенных данных по запросу (дата и время)
*/
public class CheckDateTime {
    //Маски ввода
    private final static String TIME_PATTERN = "(\\d{2})[:](\\d{2})";
    private final static String DATE_PATTERN = "(\\d{4})[-](\\d{2})[-](\\w{2})";

    //Константы класса
    private final static int MIN_TIME = 0;
    private final static int MAX_HOUR = 24;
    private final static int MAX_MINUTE = 59;
    private final static long ADD_MINUTES = 4;
    private final static long ADD_DAYS = 30;
    private final static long MINUS_DAYS = 1;

    //Основной метод проверки
    public static boolean validate(final String value) {
        if (value == null) return false;
        if (value.length() == 5) {
            return checkTime(value);
        } else if (value.length() == 10) {
            return checkDate(value);
        }

        return false;
    }

    //Проверяем корректность ввода времени по необходимой маске
    private static boolean checkTime(final String time) {
        Pattern pattern = Pattern.compile(TIME_PATTERN);  //инициализация маски в шаблон для проверки
        Matcher matcher = pattern.matcher(time);  //создание шаблона для проверки на основании инициализированной маски

        if (!matcher.matches()) return false;  //проверяем соответствие переданных данных маске

        int hours = Integer.parseInt(matcher.group(1));  //записываем 1ю группу данных по маске из переданного значения
        int minutes = Integer.parseInt(matcher.group(2)); //записываем 2ю группу данных по маске из переданного значения

        // в последнем выражении проверяем что введенное время больше текущего
        // более чем на ХХ минуты. ХХ определяется константой.
        return (hours >= MIN_TIME && hours < MAX_HOUR)
                && (minutes >= MIN_TIME && minutes <= MAX_MINUTE)
                && (LocalTime.parse(time).isAfter(LocalTime.now().plusMinutes(ADD_MINUTES)));

    }

    //Проверяем корректность ввода даты по необходимой маске
    private static boolean checkDate(final String date) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) return false;

        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(3));

        // в последнем выражении проверяем что введенная дата не больше текущей
        // более чем на XX дней. ХХ определяется константой.
        return (month >= LocalDate.MIN.getMonthValue() && month <= LocalDate.MAX.getMonthValue()
                || day >= LocalDate.MIN.getDayOfMonth() && day <= LocalDate.MAX.getDayOfMonth())
                && (LocalDate.parse(date).isBefore(LocalDate.now().plusDays(ADD_DAYS)))
                && (LocalDate.parse(date).isAfter(LocalDate.now().minusDays(MINUS_DAYS)));
    }


}
