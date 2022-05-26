package ru.job4j.inheritance;

public class PizzaExtraCheesaExtraTomato extends PizzaExtraCheese {
    @Override
    public String name() {
        return super.name() + " + Extra Tomato";
    }
}
