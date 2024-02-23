package com.ldms.scottdavies.converter;

import com.ldms.scottdavies.dto.LoanDetailsDto;
import com.ldms.scottdavies.dto.PaymentSummaryDto;
import com.ldms.scottdavies.dto.ScheduleSummaryDto;
import com.ldms.scottdavies.entity.PaymentSchedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScheduleToScheduleSummaryDtoConverter implements Converter<PaymentSchedule, ScheduleSummaryDto> {
    @Override
    public ScheduleSummaryDto convert(PaymentSchedule source) {

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

        ScheduleSummaryDto target = ScheduleSummaryDto
                .builder()
                .id(source.getId())
                .loanDetails(loanDetails)
                .paymentSummary(paymentSummary)
                .build();


        return target;
    }
}
