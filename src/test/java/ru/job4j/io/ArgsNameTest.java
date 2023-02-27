package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get(("Xmx")), equalTo("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get(("Xmx")), equalTo("512"));
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request"), equalTo("?msg=Exit="));
    }

    //TODO Необходимо разобраться, как написать тест для исключений в Jupiter5
    // Не понятно как вызвать правильно метод, в котором генерится исключение.
//    @Test
//    public void whenGetNotExist() {
//        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
//        RuntimeException exception = assertThrows(IllegalArgumentException.class, jvm.get("Xms"));
//        assertThat(exception.getMessage(), equalToIgnoringCase("Invalid parameter name specified"));
//    }
//
//    @Test
//    public void whenWrongSomeArgument() {
//        RuntimeException exception = assertThrows(IllegalArgumentException.class, ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="}));
//        assertThat(exception.getMessage(), equalToIgnoringCase("Invalid parameter name specified"));
//    }
}