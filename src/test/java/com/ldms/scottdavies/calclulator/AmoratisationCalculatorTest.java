package com.ldms.scottdavies.calclulator;

import com.ldms.scottdavies.calculator.AmortisatonCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmoratisationCalculatorTest {

    @Test
    public void calculateMonthlyInterestRateTest() {
        AmortisatonCalculator calculator = new AmortisatonCalculator();

        assertEquals(0.00625, calculator.calculateMonthlyInterestRate(7.5));
        assertEquals(0.00625, calculator.calculateMonthlyInterestRate(7.5));
    }

    @Test
    public void calculatePaymentWithoutBaloonTest() {
        AmortisatonCalculator calculator = new AmortisatonCalculator();
        assertEquals(400.76, calculator.calculateMonthlyPaymentWithoutBalloon(20000.0, 7.5, 60));
    }

    @Test
    public void calculatePaymentWithBaloonTest() {
        AmortisatonCalculator calculator = new AmortisatonCalculator();
        assertEquals(262.88, calculator.calculateMonthlyPaymentWithBalloon(20000.0, 7.5, 60, 10000.0));
    }

    @Test
    public void calclulateMonthlyPaymentWithoutBaloonTest(){
        AmortisatonCalculator calculator = new AmortisatonCalculator();
        assertEquals(400.76, calculator.calculateMonthlyPayment(20000.0, 7.5, 60, null));

    }

    @Test
    public void calclulateMonthlyPaymentWithBaloonTest(){
        AmortisatonCalculator calculator = new AmortisatonCalculator();
        assertEquals(262.88, calculator.calculateMonthlyPayment(20000.0, 7.5, 60, 10000.0));

    }

    @Test
    public void calculateAmortisations(){
        AmortisatonCalculator calculator = new AmortisatonCalculator();

//        £20,000 to be financed over a 12 month period and a yearly interest rate of 7.5% without a
//        balloon payment resulting in a monthly repayment of £1735.15:

        calculator.generateAmortisations(1735.15, 20000.0, 7.5, 12);
    }
}
//    Period Payment Principal Interest Balance
//        1 1735.15 1610.15 125.00 18389.85
//        2 1735.15 1620.21 114.94 16769.64