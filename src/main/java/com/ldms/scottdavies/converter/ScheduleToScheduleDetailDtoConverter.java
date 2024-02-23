package com.ldms.scottdavies.converter;

import com.ldms.scottdavies.dto.AmortisationDto;
import com.ldms.scottdavies.dto.LoanDetailsDto;
import com.ldms.scottdavies.dto.PaymentSummaryDto;
import com.ldms.scottdavies.dto.ScheduleDetailDto;
import com.ldms.scottdavies.entity.PaymentSchedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleToScheduleDetailDtoConverter implements Converter<PaymentSchedule, ScheduleDetailDto> {
    @Override
    public ScheduleDetailDto convert(PaymentSchedule source) {
        LoanDetailsDto loanDetails = LoanDetailsDto.builder()
                .assetCost(source.getLoanDetails().getAssetCost())
                .deposit(source.getLoanDetails().getDeposit())
                .interestRate(source.getLoanDetails().getInterestRate())
                .totalMonthlyPayments(source.getLoanDetails().getTotalMonthlyPayments())
                .baloonPayment(source.getLoanDetails().getBaloonPayment())
                .build();

        PaymentSummaryDto paymentSummary = PaymentSummaryDto.builder()
                .monthlyPayment(source.getPaymentSummary().getMonthlyPayment())
                .totalPayment(source.getPaymentSummary().getTotalPayment())
                .totalInterest(source.getPaymentSummary().getTotalInterest())
                .build();

        List<AmortisationDto> amortisations = new ArrayList<>();

        source.getAmortisationSchedule().forEach(a -> {
            amortisations.add(AmortisationDto.builder()
                    .balance(a.getBalance())
                    .interest(a.getInterest())
                    .payment(a.getPayment())
                    .period(a.getPeriod())
                    .principal(a.getPrincipal())
                    .build());
        });

        ScheduleDetailDto target = ScheduleDetailDto
                .builder()
                .id(source.getId())
                .loanDetails(loanDetails)
                .paymentSummary(paymentSummary)
                .amortisationSchedule(amortisations)
                .build();

        return target;
    }
}
