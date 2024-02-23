package com.ldms.scottdavies.calculator;

import com.ldms.scottdavies.entity.Amortisation;
import com.ldms.scottdavies.entity.PaymentSchedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AmortisatonCalculator {
    public Double calculateMonthlyPayment(Double principal, Double annualInterestRate, Integer totalMonthlyPayments, Double balloonPayment) {

        return balloonPayment == null ?
                calculateMonthlyPaymentWithoutBalloon(principal, annualInterestRate, totalMonthlyPayments) :
                calculateMonthlyPaymentWithBalloon(principal, annualInterestRate, totalMonthlyPayments, balloonPayment);
    }

    public Double calculateMonthlyInterestRate(Double interestRate) {
        return toDecimalPlace((interestRate / 100) / 12, 5);
    }

    private static Double toDecimalPlace(Double operand, Integer scale) {
        double factor = Math.pow(10, scale);
        return Math.round(operand * factor) / factor;
    }

    public Double calculateMonthlyPaymentWithoutBalloon(Double principal, Double annualInterestRate, Integer totalPayments) {
        Double monthlyInterestRate = calculateMonthlyInterestRate(annualInterestRate);

        Double monthlyPayment = (principal * monthlyInterestRate * (float) Math.pow(1 + monthlyInterestRate, totalPayments))
                / (float) (Math.pow(1 + monthlyInterestRate, totalPayments) - 1);

        return toDecimalPlace(monthlyPayment, 2);
    }

    public Double calculateMonthlyPaymentWithBalloon(Double principal, Double annualInterestRate, Integer totalPayments, Double balloon) {
        Double monthlyInterestRate = calculateMonthlyInterestRate(annualInterestRate);

        Double monthlyPayment = (principal - (balloon / Math.pow((1 + monthlyInterestRate), totalPayments))) * (monthlyInterestRate / (1 - Math.pow((1 + monthlyInterestRate), -totalPayments)));

        return toDecimalPlace(monthlyPayment, 2);
    }


    public List<Amortisation> generateAmortisations(Double monthlyPayment, Double assetCost, Double interestRate, Integer totalPayments) {
        List<Amortisation> amortisations = new ArrayList<>();

        Double remainingBalance = assetCost;
        Double monthlyRate = calculateMonthlyInterestRate(interestRate);
        for (int period = 1; period < totalPayments + 1; period++) {

            Double interest = toDecimalPlace(remainingBalance * monthlyRate, 2);

            Double paymentTowardsPrincipal = toDecimalPlace(monthlyPayment - interest, 2);
            remainingBalance = toDecimalPlace((remainingBalance + interest) - monthlyPayment, 2);

            System.out.println("Period: " + period + " payment: " + monthlyPayment + " principal " + paymentTowardsPrincipal + " interest: " + interest + " remaining Balance " + remainingBalance);

            Amortisation amortisation = Amortisation.builder()
                    .balance(remainingBalance)
                    .payment(monthlyPayment)
                    .period(period)
                    .interest(interest)
                    .principal(paymentTowardsPrincipal)
                    .build();

            amortisations.add(amortisation);
        }

        return amortisations;

    }
}
