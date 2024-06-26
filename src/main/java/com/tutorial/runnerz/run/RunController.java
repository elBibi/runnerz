package com.tutorial.runnerz.run;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RunController {

    private final RunRepository runRepository;

    @Autowired
    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }


    @GetMapping("/api/runs")
    List<Run> findAll(){
        return runRepository.findAll();}








    @GetMapping("/hello")
    String home(){          // public by default
        return "Hello, runnerz";
    }
}
