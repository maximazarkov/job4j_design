package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgsNameTest {

    @Test
    public void whenUnixPath() {
        ArgsName jvm = ArgsName.of(new String[] {"-path=/tmp/junit/source.csv"});
        assertThat(jvm.get("path")).isEqualTo("/tmp/junit/source.csv");
    }

    @Test
    public void whenWindowsPath() {
        ArgsName jvm = ArgsName.of(new String[] {"-path=c:\\tmp\\junit\\source.csv"});
        assertThat(jvm.get("path")).isEqualTo("c:\\tmp\\junit\\source.csv");
    }

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> jvm.get("Xms"));
        assertThat(thrown.getMessage()).isEqualToIgnoringCase("Invalid parameter name specified");
    }

    @Test
    public void whenWrongSomeArgument() {
        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="}));
        assertThat(thrown.getMessage())
                .isEqualTo("The parameter -Xmx= must match the format -key=value. ");
    }
}