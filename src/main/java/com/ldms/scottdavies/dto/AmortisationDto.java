package com.ldms.scottdavies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AmortisationDto {
    private Integer period;
    private Double payment;
    private Double principal;
    private Double interest;
    private Double balance;
}
