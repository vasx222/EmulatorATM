package ru.liga.system.algorithm;

import ru.liga.exceptions.InvalidOperationException;
import ru.liga.exceptions.NoBanknotesException;

import java.util.*;

public class Banknotes {
    private SortedMap<Integer, Integer> banknotes;
    private Set<State> usedStates;

    public Banknotes() {
        this.banknotes = new TreeMap<>(Comparator.reverseOrder());
    }

    public void putBanknote(int denomination) {
        if (banknotes.containsKey(denomination)) {
            Integer amount = banknotes.get(denomination);
            banknotes.put(denomination, amount + 1);
            return;
        }
        banknotes.put(denomination, 1);
    }
    public int getAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            amount += entry.getKey() * entry.getValue();
        }
        return amount;
    }
    public void removeBanknote(int denomination) {
        if (banknotes.containsKey(denomination)) {
            int amount = banknotes.get(denomination);
            if (amount > 1) {
                banknotes.put(denomination, amount - 1);
            } else {
                banknotes.remove(denomination);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banknotes banknotes1 = (Banknotes) o;
        return Objects.equals(banknotes, banknotes1.banknotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banknotes);
    }

    public Banknotes returnAmountAsMinimumBanknotesNumber(int amount)
            throws NoBanknotesException, InvalidOperationException {
        Banknotes banknotes = this.getCopy();
        usedStates = new HashSet<>();
        usedStates.add(new State(amount, banknotes));
        Banknotes resultBanknotes = search(amount, banknotes);
        if (resultBanknotes == null) {
            throw new NoBanknotesException();
        }
        this.deduct(resultBanknotes);
        return resultBanknotes;
    }

    private Banknotes search(int amount, Banknotes banknotes) throws InvalidOperationException {
        if (amount == 0) {
            Banknotes resultBanknotes = this.getCopy();
            resultBanknotes.deduct(banknotes);
            return resultBanknotes;
        }
        for (Integer denomination : banknotes.banknotes.keySet()) {
            int newAmount = amount - denomination;
            if (newAmount < 0) {
                continue;
            }
            Banknotes newBanknotes = banknotes.getCopy();
            int reserve = newBanknotes.getAmount() - denomination;
            if (reserve < 0) {
                continue;
            }
            newBanknotes.removeBanknote(denomination);
            State newState = new State(newAmount, newBanknotes);
            if (!usedStates.contains(newState)) {
                usedStates.add(newState);
                Banknotes resultBanknotes = search(newAmount, newBanknotes);
                if (resultBanknotes == null) {
                    continue;
                }
                return resultBanknotes;
            }
        }
        return null;
    }

    public void deduct(Banknotes banknotes) throws InvalidOperationException {
        for (Map.Entry<Integer, Integer> entry : banknotes.banknotes.entrySet()) {
            if (!this.banknotes.containsKey(entry.getKey())) {
                throw new InvalidOperationException();
            }
            Integer rest = this.banknotes.get(entry.getKey()) - entry.getValue();
            if (rest < 0) {
                throw new InvalidOperationException();
            }
            if (rest == 0) {
                this.banknotes.remove(entry.getKey());
                continue;
            }
            this.banknotes.put(entry.getKey(), rest);
        }
    }
    public void add(Banknotes banknotes) {
        for (Map.Entry<Integer, Integer> entry : banknotes.banknotes.entrySet()) {
            if (!this.banknotes.containsKey(entry.getKey())) {
                this.banknotes.put(entry.getKey(), entry.getValue());
                continue;
            }
            Integer value = this.banknotes.get(entry.getKey());
            this.banknotes.put(entry.getKey(), entry.getValue() + value);
        }
    }
    public Banknotes getCopy() {
        Banknotes banknotes = new Banknotes();
        banknotes.add(this);
        return banknotes;
    }

    @Override
    public String toString() {
        return banknotes.toString();
    }
}
