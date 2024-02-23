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
@Table(name = "amortisation")
public class Amortisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "period", nullable = false)
    private Integer period;
    @Column(name = "payment", nullable = false)
    private Double payment;
    @Column(name = "principal", nullable = false)
    private Double principal;
    @Column(name = "interest", nullable = false)
    private Double interest;
    @Column(name = "balance", nullable = false)
    private Double balance;

}
