package ru.job4j.inheritance;

public class PizzaShop {
    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        PizzaExtraCheese pizzaExtraCheese = new PizzaExtraCheese();
        PizzaExtraCheesaExtraTomato pizzaExtraCheesaExtraTomato = new PizzaExtraCheesaExtraTomato();
        System.out.println(pizza.name());
        System.out.println(pizzaExtraCheese.name());
        System.out.println(pizzaExtraCheesaExtraTomato.name());
        System.out.println("НЕ ИСПОЛЬЗУЙТЕ НАСЛЕДОВАНИЕ - НЕ ЕШЬТЕ ПИЦЦУ");
    }
}
