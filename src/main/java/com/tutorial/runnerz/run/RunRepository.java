package com.tutorial.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }




    //Lets load some data without Injection
    @PostConstruct
    private void init(){
        runs.add(new  Run(
                1,
                "Monday Morning",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                3,
                Location.INDOOR));
        runs.add(new Run(
                2,
                "Wednesday Evening",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
                6,
                Location.INDOOR
        ));

    }


}
