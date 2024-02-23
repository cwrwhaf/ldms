package com.ldms.scottdavies.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleDetailDto {
    private Long id;
    private LoanDetailsDto loanDetails;
    private PaymentSummaryDto paymentSummary;
    private List<AmortisationDto> amortisationSchedule;
}
