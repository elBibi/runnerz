package com.tutorial.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryRunRepository implements RunRepository{
    private static final Logger log = LoggerFactory.getLogger(InMemoryRunRepository.class);

    // eventually replace by DB
    private List<Run> runs = new ArrayList<>();

    public int count(){
        log.info("Executing --> RunRepository --> count()");
        return runs.size();
    }

    public List<Run> findAll(){
        log.info("Executing --> RunRepository --> findAll()");
        return runs;
    }

    public Optional<Run> findById(Integer id){
        log.info("Executing --> RunRepository --> findById()");
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    //private int calculateDaysBetweenDate(Date date1,)
//POST Create a new run
    public void create(Run run){
        log.info("Executing --> RunRepository --> Creating Run =" + run);
        runs.add(run);
    }

//Update
public void update(Run run, Integer id) {
        log.info("Executing --> RunRepository --> Updating Run with id: " + id);
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()),run);
        }
    }

//Delete
public void delete(Integer id){
        log.info("Executing -->  RunRepository -->   Deleting Run with id: " + id);
        runs.removeIf(run -> run.id().equals(id));
    }


    //Lets load some data without Injection
    @PostConstruct
    private void init(){
        log.info("Executing --> RunRepository--> @PostConstruct init()");
        runs.add(new  Run(
                1,
                "Monday Morning",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                3,
                Location.INDOOR));
        log.info("   Adding Run: " + runs.get(0));
        runs.add(new Run(
                2,
                "Wednesday Evening",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
                6,
                Location.INDOOR
        ));
        log.info("   Adding Run: " + runs.get(1));
    }


}
