package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void parse(String[] args) {
        for (String s : args) {
            int index1 = s.indexOf("-");
            int index2 = s.indexOf("=");
            String key = s.substring(index1 + 1, index2);
            String value = s.substring(index2 + 1);
            if (key.length() == 0) {
                throw new IllegalArgumentException("The name of the parameter is not defined");
            }
            if (value.length() == 0) {
                throw new IllegalArgumentException("The parameter value is not defined");
            }
            values.put(key, value);
        }
    }

    public String get(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException("Invalid parameter name specified");
        }
        return result;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
