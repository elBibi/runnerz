package com.tutorial.runnerz.run;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    @Autowired
    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }


    // = public List<Run> findAll(){return runRepository.findAll();}
    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();}

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        return runRepository.findById(id);
    }






    @GetMapping("/hello")
    String home(){          // public by default
        return "Hello, runnerz";
    }
}
