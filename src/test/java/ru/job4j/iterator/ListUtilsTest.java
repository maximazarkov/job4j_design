package ru.job4j.iterator;

import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertEquals(input, Arrays.asList(1, 2, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        Assert.assertEquals(input, Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        ListUtils.addAfter(input, 1, 2);

        Assert.assertEquals(input, Arrays.asList(1, 3, 2, 4));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        Predicate<Integer> predicate = (p) -> Objects.equals(p, 2);
        ListUtils.removeIf(input, predicate);

        Assert.assertEquals(input, Arrays.asList(1, 4));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        Predicate<Integer> predicate = (p) -> Objects.equals(p, 2);
        ListUtils.replaceIf(input, predicate, 5);

        Assert.assertEquals(input, Arrays.asList(1, 3, 5));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(3, 4));
        ListUtils.removeAll(input, elements);

        Assert.assertEquals(input, Arrays.asList(1, 5));
    }
}