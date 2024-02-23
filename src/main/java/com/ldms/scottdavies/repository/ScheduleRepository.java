package com.ldms.scottdavies.repository;

import com.ldms.scottdavies.entity.PaymentSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<PaymentSchedule, Long>, PagingAndSortingRepository<PaymentSchedule,Long> {
}
