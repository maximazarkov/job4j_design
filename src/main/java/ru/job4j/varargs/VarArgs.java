package ru.job4j.varargs;

public class VarArgs {

    public String text(String a) {
        return "";
    }

    public String text(String a, String b) {
        return "";
    }

    public String text(String a, String b, String c) {
        return "";
    }

    public static String text(String[] strings) {
        return "";
    }

    public static String textVarArgs(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }
        return builder.toString();
    }

    public static String textVarArgsLength(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }
        return builder + " Количество соединенных элементов: " + strings.length;
    }

    public static String textVarArgsOverload(String str) {
        return str;
    }

    public static String textVarArgsOverload(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }
        return builder.toString();
    }

    public static String textVarArgsOverload(int x, String... strings) {
        StringBuilder builder = new StringBuilder();
        builder.append(x);
        for (String s : strings) {
            builder.append(s);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] strings = {"aaa", "bbb", "ccc"};
        String str = text(strings);
        System.out.println(str);

        System.out.println(textVarArgs());
        System.out.println(textVarArgs("aaa"));
        System.out.println(textVarArgs("aaa", "bbb", "ccc"));
        System.out.println(textVarArgs("aaa", "bbb", "ccc", "ddd", "eee"));

        System.out.println(textVarArgsLength("aaa", "bbb", "ccc"));

        System.out.println(textVarArgsOverload());
        System.out.println(textVarArgsOverload("aaa"));
        System.out.println(textVarArgsOverload("aaa", "bbb", "ccc"));
        System.out.println(textVarArgsOverload(30, "aaa", "bbb"));
        System.out.println(textVarArgsOverload("String"));
    }
}
