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
        log.info("Executing --> InMemoryRepository --> count()");
        return runs.size();
    }

    public List<Run> findAll(){
        log.info("Executing --> InMemoryRepository --> findAll()");
        return runs;
    }

    public Optional<Run> findById(Integer id){
        log.info("Executing --> InMemoryRepository --> findById()");
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    //private int calculateDaysBetweenDate(Date date1,)
//POST Create a new run
    public void create(Run run){
        log.info("Executing --> InMemoryRepository --> Creating Run =" + run);
        runs.add(run);
    }

//Update
public void update(Run run, Integer id) {
        log.info("Executing --> InMemoryRepository --> Updating Run with id: " + id);
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()),run);
        }
    }

//Delete
public void delete(Integer id){
        log.info("Executing -->  InMemoryRepository -->   Deleting Run with id: " + id);
        runs.removeIf(run -> run.id().equals(id));
    }


}
