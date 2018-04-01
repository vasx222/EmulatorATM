package ru.liga.system;

import ru.liga.system.algorithm.Banknotes;

public class ATM {
    private Banknotes banknotes = new Banknotes();
    private Banknotes initialBanknotes = new Banknotes();
    public void putBanknote(int denomination) {
        banknotes.putBanknote(denomination);
    }
    public Banknotes returnAmountAsMinimumBanknotesNumber(int amount) {
        try {
            return banknotes.returnAmountAsMinimumBanknotesNumber(amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Banknotes();
    }
    public int getRemainingAmountOnAccount() {
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
    public void setInitialState(Banknotes banknotes) {
        initialBanknotes = banknotes;
    }
}
