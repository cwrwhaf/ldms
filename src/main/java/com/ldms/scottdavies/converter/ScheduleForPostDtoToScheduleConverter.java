package com.ldms.scottdavies.converter;

import com.ldms.scottdavies.dto.ScheduleForPostDto;
import com.ldms.scottdavies.entity.LoanDetails;
import com.ldms.scottdavies.entity.PaymentSchedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScheduleForPostDtoToScheduleConverter implements Converter<ScheduleForPostDto, PaymentSchedule> {
    @Override
    public PaymentSchedule convert(ScheduleForPostDto source) {

        LoanDetails loanDetails = LoanDetails.builder()
                .assetCost(source.getLoanDetailsDto().getAssetCost())
                .deposit(source.getLoanDetailsDto().getDeposit())
                .interestRate(source.getLoanDetailsDto().getInterestRate())
                .totalMonthlyPayments(source.getLoanDetailsDto().getTotalMonthlyPayments())
                .baloonPayment(source.getLoanDetailsDto().getBaloonPayment())
                .build();

        PaymentSchedule target = PaymentSchedule.builder()
                .loanDetails(loanDetails)
                .build();

        return target;
    }
}
