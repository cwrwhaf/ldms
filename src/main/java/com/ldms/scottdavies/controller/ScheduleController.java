package com.ldms.scottdavies.controller;

import com.ldms.scottdavies.service.ScheduleService;
import com.ldms.scottdavies.converter.ScheduleForPostDtoToScheduleConverter;
import com.ldms.scottdavies.converter.ScheduleToScheduleDetailDtoConverter;
import com.ldms.scottdavies.converter.ScheduleToScheduleSummaryDtoConverter;
import com.ldms.scottdavies.dto.*;
import com.ldms.scottdavies.entity.PaymentSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("schedules")
public class ScheduleController {

    Logger log = LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;
    private final ScheduleToScheduleSummaryDtoConverter scheduleToScheduleSummaryDtoConverter;
    private final ScheduleForPostDtoToScheduleConverter scheduleForPostDtoToScheduleConverter;
    private final ScheduleToScheduleDetailDtoConverter scheduleToScheduleDetailDtoConverter;

    public ScheduleController(ScheduleService scheduleService,
                              ScheduleToScheduleSummaryDtoConverter scheduleToScheduleSummaryDtoConverter,
                              ScheduleForPostDtoToScheduleConverter scheduleForPostDtoToScheduleConverter,
                              ScheduleToScheduleDetailDtoConverter scheduleToScheduleDetailDtoConverter) {
        this.scheduleService = scheduleService;

        this.scheduleToScheduleSummaryDtoConverter = scheduleToScheduleSummaryDtoConverter;
        this.scheduleForPostDtoToScheduleConverter = scheduleForPostDtoToScheduleConverter;
        this.scheduleToScheduleDetailDtoConverter = scheduleToScheduleDetailDtoConverter;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ScheduleSummaryDto> getSchedules(Pageable pageable) {

        Page<PaymentSchedule> schedules = scheduleService.getSchedules(pageable);

        return schedules.map(schedule -> scheduleToScheduleSummaryDtoConverter.convert(schedule));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ScheduleDetailDto getSchedule(@PathVariable Long id) {

        PaymentSchedule schedule = scheduleService.getScheduleForId(id);

        return scheduleToScheduleDetailDtoConverter.convert(schedule);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleForPostDto scheduleForPostDto) {

        PaymentSchedule schedule = scheduleService.createSchedule(scheduleForPostDtoToScheduleConverter.convert(scheduleForPostDto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(schedule.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
