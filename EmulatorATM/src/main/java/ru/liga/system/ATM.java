package ru.liga.system;

import ru.liga.system.algorithm.Banknotes;

public class ATM {
    private Banknotes banknotes = new Banknotes();
    private Banknotes initialBanknotes;

    public ATM() {
        this.initialBanknotes = new Banknotes();
    }
    public ATM(Banknotes initialBanknotes) {
        this.initialBanknotes = initialBanknotes;
    }

    public void putBanknote(int denomination) {
        banknotes.putBanknote(denomination);
    }
    public Banknotes returnAmountAsLargeBanknotes(int amount) {
        return banknotes.returnAmountAsLargeBanknotes(amount);
    }
    public Banknotes returnAmountAsSmallBanknotes(int amount) {
        return banknotes.returnAmountAsSmallBanknotes(amount);
    }
    public int getRemainingAmount() {
        return banknotes.getAmount();
    }
    public Banknotes returnAllBanknotes() {
        Banknotes banknotes = this.banknotes;
        this.banknotes = new Banknotes();
        return banknotes;
    }
    public void restoreInitialState() {
        banknotes = initialBanknotes.getCopy();
    }
}
