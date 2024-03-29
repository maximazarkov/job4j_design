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
     * Проверка формата заданной строки параметров
     * @param s - строка параметра
     * @return - возвращает true, если строка удовлетворяет формату -key=value,
     * где key и value - не пустые значения.
     */
    private boolean checkArguments(String s) {
        String regex = "^-[a-zA-Z]+=[-?\\w=.,\";:~\\\\\\/]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) {
            throw new IllegalArgumentException("The parameter "
                    + s + " must match the format -key=value. ");
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
