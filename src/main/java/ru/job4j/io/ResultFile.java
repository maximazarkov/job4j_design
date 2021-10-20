package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Multiplication table".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(getMaltyTable(9).getBytes());
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
