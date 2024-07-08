package com.tutorial.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
) {
    // validate the data
    public Run {
        final Logger log = LoggerFactory.getLogger(Run.class);
        log.info("Executing --> Run --> Constructor");
        if (completedOn.isBefore(startedOn)) {
            throw new IllegalArgumentException("Complete On must be after Started On");
        }
    }

//using validation API

}
