package com.ivan.mostovyi.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserStatisticsScheduler {


    @Scheduled(cron = "0 0 23 * * ?", zone = "${job.timeZone}")
    private void dailyStatistics() {
        // todo implements
        //  - send summary to user about the day
    }

}
