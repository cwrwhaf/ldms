package com.ldms.scottdavies.service;

import com.ldms.scottdavies.calculator.AmortisatonCalculator;
import com.ldms.scottdavies.entity.Amortisation;
import com.ldms.scottdavies.entity.PaymentSchedule;
import com.ldms.scottdavies.entity.PaymentSummary;
import com.ldms.scottdavies.exception.ApplicationException;
import com.ldms.scottdavies.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScheduleService {
    private final AmortisatonCalculator amortisatonCalculator;
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(AmortisatonCalculator amortisatonCalculator, ScheduleRepository scheduleRepository) {
        this.amortisatonCalculator = amortisatonCalculator;
        this.scheduleRepository = scheduleRepository;
    }

    public Page<PaymentSchedule> getSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public PaymentSchedule getScheduleForId(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new ApplicationException("Schedule For Id " + id + " Not Found"));
    }

    @Transactional
    public PaymentSchedule createSchedule(PaymentSchedule schedule) {
        var loan = schedule.getLoanDetails();

        Double principal = loan.getAssetCost() - loan.getDeposit();

        Double monthlyPayment = amortisatonCalculator.calculateMonthlyPayment(principal, loan.getInterestRate(), loan.getTotalMonthlyPayments(), loan.getBaloonPayment());

        List<Amortisation> amortisations = amortisatonCalculator.generateAmortisations(monthlyPayment, principal, loan.getInterestRate(), loan.getTotalMonthlyPayments());

        Double totalPayment = amortisations.stream().mapToDouble(Amortisation::getPayment).sum();
        Double totalInterest = amortisations.stream().mapToDouble(Amortisation::getInterest).sum();

        PaymentSummary paymentSummary = PaymentSummary.builder()
                .monthlyPayment(monthlyPayment)
                .totalPayment(totalPayment)
                .totalInterest(totalInterest)
                .build();

        schedule.setAmortisationSchedule(amortisations);
        schedule.setPaymentSummary(paymentSummary);

        return scheduleRepository.save(schedule);

    }

}
