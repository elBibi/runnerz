package com.tutorial.runnerz.run;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private static final Logger log = LoggerFactory.getLogger(RunController.class);

    private final InMemoryRepository runRepository;

    @Autowired
    public RunController(InMemoryRepository runRepository){
        log.info("Executing --> RunController --> @AUtowired RunController Constructor =" + runRepository.getClass().getName()  );
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll(){
        log.info("Executing --> RunController --> findAll()");
        return runRepository.findAll();}

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        log.info("Executing --> RunController --> findById() ="+id);
        Optional<Run> run = runRepository.findById(id);
        log.info("--> RunController --> findById() ="+run);
        if(run.isEmpty()){
            log.info("--> RunController --> RunNotFoundException()-> Run not found");
            throw new RunNotFoundException();
        }
        return run.get();
    }

    //POST : Parameter pass thru the Body -- CREATE
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run){
        log.info("Executing --> RunController --> create() ="+run);
        runRepository.create(run);
    }

    //PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody  Run run, @PathVariable Integer id){
        log.info("Executing --> RunController --> update() ="+run + " id ="+id);
        runRepository.update(run,id);
    }

    //DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        log.info("Executing --> RunController --> delete() ="+id);
        runRepository.delete(id);
    }


    @GetMapping("/hello")
    String home(){          // public by default
        log.info("Executing --> RunController --> home()");
        return "Hello, runnerz From MiK";
    }
}
