package com.ldms.scottdavies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan_details")
public class LoanDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "asset_cost", nullable = false)
    private Double assetCost;

    @Column(name = "deposit", nullable = false)
    private Double deposit;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Column(name = "total_monthly_payments", nullable = false)
    private Integer totalMonthlyPayments;

    @Column(name = "baloon_payment", nullable = true)
    private Double baloonPayment;

}