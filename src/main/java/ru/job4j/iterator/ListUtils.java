package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    /**
     * Вставляет до индекса.
     * @param list - список объектов, над которым выполняется работа.
     * @param index - место, перед которым выполняется вставка.
     * @param value - значение (объект), которое необходимо вставить.
     * @param <T> - тип объектов, над которыми проводятся объекты.
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        var i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Вставляет после индекса.
     * @param list - список объектов, над которым выполняется работа.
     * @param index - место, перед которым выполняется вставка.
     * @param value - значение (объект), которое необходимо вставить.
     * @param <T> - тип объектов, над которыми проводятся объекты.
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        var i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.next();
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Удаляет все элементы, которые удовлетворяют предикату.
     * Запрещено использовать метод List.removeIf.
     * @param list - список объектов, над которым выполняется работа.
     * @param filter - Условие для фильтрации по индексу.
     * @param <T> - тип объектов, над которыми проводятся объекты.
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        var i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test((T) Integer.valueOf(i.nextIndex()))) {
                i.remove();
                break;
            }
            i.next();
        }
    }

    /**
     * Заменяет все элементы, которые удовлетворяют предикату.
     * @param list - список объектов, над которым выполняется работа.
     * @param filter - условие для поиска заменяемого элемента в формате Predicate.
     * @param value - элемент для замены.
     * @param <T> - тип объектов, над которыми проводятся объекты.
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        var i = list.listIterator();
        while (i.hasNext()) {
            int index = i.nextIndex();
            if (filter.test((T) Integer.valueOf(index))) {
                list.set(index, value);
                break;
            }
            i.next();
        }
    }

    /**
     * Удаляет из списка те элементы, которые есть в elements.
     * Запрещено использовать метод List.removeAll().
     * @param list - список объектов, над которым выполняется работа.
     * @param elements - список элементов для удаления.
     * @param <T> - тип объектов, над которыми проводятся объекты.
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        var i = list.listIterator();
        while (i.hasNext()) {
            T lst = i.next();
            for (T element : elements) {
                if (lst.equals(element)) {
                    i.remove();
                }
            }
        }
    }
}