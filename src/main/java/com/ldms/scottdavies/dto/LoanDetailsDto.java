package com.ldms.scottdavies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanDetailsDto{
    private Double assetCost;
    private Double deposit;
    private Double interestRate;
    private Integer totalMonthlyPayments;
    private Double baloonPayment;
}
