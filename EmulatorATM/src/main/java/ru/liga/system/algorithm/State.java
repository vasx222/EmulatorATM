package ru.liga.system.algorithm;

import java.util.Objects;

public class State {
    private int amount;
    private Banknotes banknotes;

    public State(int amount, Banknotes banknotes) {
        this.amount = amount;
        this.banknotes = banknotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
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
