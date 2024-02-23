package com.ldms.scottdavies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentSummaryDto {
    private Double monthlyPayment;
    private Double totalInterest;
    private Double totalPayment;
}
