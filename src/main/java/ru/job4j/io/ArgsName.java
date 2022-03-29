package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The list of arguments is missing");
        }
        for (String s : args) {
            if (checkArguments(s)) {
                int index1 = s.indexOf("-");
                int index2 = s.indexOf("=");
                values.put(s.substring(index1 + 1, index2), s.substring(index2 + 1));
            }
        }
    }

    /**
     * Парcинг по элементам выполняется по уже разобранной строке параметров
     * @param key - ключ
     * @param value - значение
     * @return - возвращает true, если аргументы проходят успешную проверку
     * @deprecated - Рекомендуется воспользоваться private boolean checkArguments(String[] args) {}
     */
    private boolean checkArguments(String key, String value) {
        if (key.length() == 0) {
            throw new IllegalArgumentException("The name of the parameter is not defined");
        }
        if (value.length() == 0) {
            throw new IllegalArgumentException("The parameter value is not defined");
        }
        return true;
    }

    /**
     * Проверка формата заданной строки параметров
     * @param s - строка параметра
     * @return - возвращает true, если строка удовлетворяет формату -key=value, где key и value - не пустые значения.
     */
    private boolean checkArguments(String s) {
        Pattern pattern = Pattern.compile("^-[a-zA-Z]+=[-?\\w=]+$");
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) {
            throw new IllegalArgumentException("The parameter " + s + " must match the format -key=value. ");
        }
        return true;
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Invalid parameter name specified");
        }
        return values.get(key);
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
