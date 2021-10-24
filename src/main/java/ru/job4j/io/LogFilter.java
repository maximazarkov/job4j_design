package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rlt = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rlt = in.lines()
                    .filter(l -> {
                        var row = l.split(" ");
                        return "404".equals(row[row.length - 2]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rlt;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}