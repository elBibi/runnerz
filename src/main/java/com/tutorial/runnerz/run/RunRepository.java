package com.tutorial.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    // eventually replace by DB
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }

    Optional<Run> findById(Integer id){
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    //private int calculateDaysBetweenDate(Date date1,)
//POST Create a new run
    void create(Run run){
        runs.add(run);
    }

//Update
    void update(Run run,Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()),run);
        }
    }

//Delete
    void delete (Integer id){
        runs.removeIf(run -> run.id().equals(id));
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
