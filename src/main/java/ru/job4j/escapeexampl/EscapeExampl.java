package ru.job4j.escapeexampl;

public class EscapeExampl {
    public static void main(String[] args) {
        String str = "Я изучаю на \"Job4j\" экранирование символов";
        System.out.println(str);

        char escape = '\"';
        System.out.println(escape);

        System.out.println('\'');

        str = "Я изучаю на 'Job4j' экранирование символов";
        System.out.println(str);

        str = "C:\\projects\\job4j\\File.java";
        System.out.println(str);

        System.out.print("Старый длинный текст\r");
        System.out.println("Новый текст");

        System.out.print("Старый длинный текст\r");
        System.out.println("Новый текст");

        System.out.println("Job4jjj\b\b");
    }
}
