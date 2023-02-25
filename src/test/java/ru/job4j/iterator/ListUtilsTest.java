package ru.job4j.iterator;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);

        assertThat(input.containsAll(Arrays.asList(1, 2, 3)), is(true));
    }

    //TODO Необходимо разобраться, как написать тест для исключений в Jupiter5.
    // Не понятно как вызвать правильно метод, в котором генерится исключение.
//    @Test
//    public void whenAddBeforeWithInvalidIndex() {
//        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
//        RuntimeException exception = assertThrows(IndexOutOfBoundsException.class, ListUtils.addBefore(input, 3, 2));
//        assertThat(exception.getMessage(), equalToIgnoringCase("Invalid parameter name specified"));
//    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input.equals(Arrays.asList(0, 1, 2, 3)), is(true));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        ListUtils.addAfter(input, 1, 2);

        assertThat(input.equals(Arrays.asList(1, 3, 2, 4)), is(true));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        Predicate<Integer> predicate = (p) -> Objects.equals(p, 3);
        ListUtils.removeIf(input, predicate);

        assertThat(input.equals(Arrays.asList(1, 4)), is(true));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        Predicate<Integer> predicate = (p) -> Objects.equals(p, 4);
        ListUtils.replaceIf(input, predicate, 5);

        assertThat(input.equals(Arrays.asList(1, 3, 5)), is(true));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(3, 4));
        ListUtils.removeAll(input, elements);

       assertThat(input.equals(Arrays.asList(1, 5)), is(true));
    }

    @Test
    public void whenRemoveAllWhereListRandom() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 4, 3, 2, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(3, 4));
        ListUtils.removeAll(input, elements);

        assertThat(input.equals(Arrays.asList(1, 2, 5)), is(true));
    }
}