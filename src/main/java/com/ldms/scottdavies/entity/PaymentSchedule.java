package com.ldms.scottdavies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private LoanDetails loanDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PaymentSummary paymentSummary;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="amortisation_id")
    @Column(name = "amortisation_schedule", nullable = false)
    private List<Amortisation> amortisationSchedule;

}
