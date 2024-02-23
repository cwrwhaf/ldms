package com.ldms.scottdavies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleSummaryDto {
    private Long id;
    private LoanDetailsDto loanDetails;
    private PaymentSummaryDto paymentSummary;
}
