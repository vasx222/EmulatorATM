package ru.liga.system;

import ru.liga.system.algorithm.Banknotes;

import java.util.ArrayList;
import java.util.List;

public class ATMDepartment {
    private List<ATM> atms;
    private String companyName;

    public ATMDepartment(String companyName) {
        this.atms = new ArrayList<>();
        this.companyName = companyName;
    }
    public void addATM(ATM atm) {
        atms.add(atm);
    }
    public Banknotes collectBanknotesFromAllATMs() {
        Banknotes totalBanknotes = new Banknotes();
        for (ATM atm : atms) {
            totalBanknotes.add(atm.returnAllBanknotes());
        }
        return totalBanknotes;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void restoreAllATMs() {
        for (ATM atm : atms) {
            atm.restoreInitialState();
        }
    }
}
