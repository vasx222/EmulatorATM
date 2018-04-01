package ru.liga;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import ru.liga.exceptions.InvalidOperationException;
import ru.liga.exceptions.NoBanknotesException;
import ru.liga.system.ATM;
import ru.liga.system.algorithm.Banknotes;

public class AppTest {

    @Test
    public void checkThatAdditionBanknotesToATMWorks() {
        ATM atm = new ATM();
        int amount = atm.getRemainingAmountOnAccount();
        Assertions.assertThat(amount).isEqualTo(0);
        atm.putBanknote(100);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(100);
        atm.putBanknote(100);
        atm.putBanknote(50);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(250);
    }

    @Test
    public void checkThatSimpleOperationsWithATMWork() {
        ATM atm = new ATM();
        atm.putBanknote(100);
        atm.putBanknote(500);
        atm.putBanknote(100);
        atm.putBanknote(50);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(750);
        Banknotes banknotes = atm.returnAllBanknotes();
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(0);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(750);
        atm.putBanknote(500);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(500);
        atm.restoreInitialState();
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(0);
        atm.putBanknote(1000);
        atm.putBanknote(100);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(1100);
        banknotes = new Banknotes();
        banknotes.putBanknote(200);
        banknotes.putBanknote(400);
        atm.setInitialState(banknotes);
        atm.restoreInitialState();
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(600);
    }

    @Test
    public void checkThatOperationsWithBanknotesWork() throws InvalidOperationException {
        Banknotes banknotes = new Banknotes();
        banknotes.putBanknote(100);
        banknotes.putBanknote(200);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(300);
        Banknotes banknotes1 = new Banknotes();
        banknotes1.putBanknote(25);
        banknotes.add(banknotes1);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(325);
        banknotes.deduct(banknotes1);
        Assertions.assertThatThrownBy(() -> banknotes.deduct(banknotes1)).
                isExactlyInstanceOf(InvalidOperationException.class);
        banknotes.removeBanknote(40);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(300);
        banknotes.removeBanknote(100);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(200);
    }

    @Test
    public void checkThatDispenserAlgorithmWorks()
            throws NoBanknotesException {
        ATM atm = new ATM();
        atm.putBanknote(100);
        atm.putBanknote(200);
        atm.putBanknote(200);
        atm.putBanknote(50);
        atm.putBanknote(50);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(600);
        Banknotes banknotes = atm.returnAmountAsMinimumBanknotesNumber(500);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(500);
        System.out.println(banknotes);
        banknotes.removeBanknote(200);
        banknotes.removeBanknote(200);
        banknotes.removeBanknote(100);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(0);
    }

    @Test
    public void oneMoreTest() throws NoBanknotesException {
        ATM atm = new ATM();
        for (int i = 1; i <= 10; i++) {
            atm.putBanknote(i);
            atm.putBanknote(i);
        }
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(110);
        Banknotes banknotes = atm.returnAmountAsMinimumBanknotesNumber(0);
        System.out.println(banknotes);
        banknotes = atm.returnAmountAsMinimumBanknotesNumber(100);
        System.out.println(banknotes);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(100);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(10);
        System.out.println(atm.returnAllBanknotes());
    }

    @Test
    public void checkFailingCases() {
        ATM atm = new ATM();
        atm.putBanknote(10);
        atm.putBanknote(10);
        atm.putBanknote(10);
        atm.putBanknote(5);
        atm.putBanknote(1);
        Assertions.assertThatThrownBy(() -> atm.returnAmountAsMinimumBanknotesNumber(40)).
                isExactlyInstanceOf(NoBanknotesException.class);
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(36);
        Assertions.assertThatThrownBy(() -> atm.returnAmountAsMinimumBanknotesNumber(34)).
                isExactlyInstanceOf(NoBanknotesException.class);
        Assertions.assertThatThrownBy(() -> atm.returnAmountAsMinimumBanknotesNumber(2)).
                isExactlyInstanceOf(NoBanknotesException.class);
    }

    @Test
    public void checkAnotherCases() throws NoBanknotesException {
        ATM atm = new ATM();
        atm.putBanknote(10);
        atm.putBanknote(1);
        atm.putBanknote(10);
        atm.putBanknote(5);
        atm.putBanknote(10);
        atm.putBanknote(5);
        Banknotes banknotes = atm.returnAmountAsMinimumBanknotesNumber(11);
        System.out.println(banknotes);
    }

    @Test
    public void checkIfManyBanknotes() throws NoBanknotesException {
        ATM atm = new ATM();
        int totalAmount = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < 100 - i; j++) {
                atm.putBanknote(i);
                totalAmount += i;
            }
        }
        Assertions.assertThat(atm.getRemainingAmountOnAccount()).isEqualTo(totalAmount);
        System.out.println(totalAmount);
        Banknotes banknotes = atm.returnAmountAsMinimumBanknotesNumber(125486);
        System.out.println(banknotes);
        Banknotes rest = atm.returnAllBanknotes();
        banknotes.add(rest);
        Assertions.assertThat(banknotes.getAmount()).isEqualTo(totalAmount);
    }
}
