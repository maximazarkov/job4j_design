package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ResultFileTest {
    @Test
    public void when2on2() {
        StringBuilder expect = new StringBuilder();
        final String LS = System.lineSeparator();
        expect.append(" | 1   2").append(LS);
        expect.append("-+------").append(LS);
        expect.append("1| 1   2   ").append(LS);
        expect.append("2| 2   4   ").append(LS);
        assertThat(expect.toString()).isEqualTo(ResultFile.getMaltyTable(2));
    }
}