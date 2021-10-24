package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")))) {
            out.println("Multiplication table");
            out.println(System.lineSeparator());
            out.println(getMaltyTable(9));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMaltyTable(int size) {
        StringBuilder sb = new StringBuilder();
        final String LS = System.lineSeparator();
        sb.append(" | ");
        for (int i = 0; i < size; i++) {
            sb.append(i + 1);
            sb.append((i != size - 1 ? (i >= 100 ? " " : ((i >= 10) ? "  " : "   ")) : ""));
        }
        sb.append(LS);
        for (int i = 0; i < size; i++) {
            sb.append(i == 0 ? "-+--" : "----");
        }
        sb.append(LS);
        for (int i = 0; i < size; i++) {
            sb.append(i + 1).append("| ");
            for (int j = 0; j < size; j++) {
                int r = (i + 1) * (j + 1);
                sb.append(r);
                sb.append((r >= 100 ? " " : ((r >= 10) ? "  " : "   ")));
            }
            sb.append(LS);
        }
        return sb.toString();
    }
}
