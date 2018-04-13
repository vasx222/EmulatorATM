package ru.liga.system.algorithm;

import java.util.HashSet;
import java.util.Objects;

/**
 * Для реализации алгоритма выдачи суммы минимальным числом купюр
 * используется граф состояний.
 * Класс GraphState реализует состояние графа, где
 * amount - сумма, которую осталось выдать,
 * banknotes - оставшееся число купюр.
 */
class GraphState {
    private final int amount;
    private final Banknotes banknotes;

    GraphState(int amount, Banknotes banknotes) {
        this.amount = amount;
        this.banknotes = banknotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphState state = (GraphState) o;
        return amount == state.amount &&
                Objects.equals(banknotes, state.banknotes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, banknotes);
    }

    public int getAmount() {
        return amount;
    }

    public Banknotes getBanknotes() {
        return banknotes;
    }
}
