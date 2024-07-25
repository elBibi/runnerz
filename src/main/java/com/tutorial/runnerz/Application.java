package com.tutorial.runnerz;

import com.tutorial.runnerz.run.Location;
import com.tutorial.runnerz.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {

    //Log capability.
    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        ApplicationContext apc = SpringApplication.run(Application.class, args);

        for (String s : apc.getBeanDefinitionNames()) {
            log.info("-->Load into IOC Container->"+s);
        }
        log.info("-->Application loaded Successfully");
    }

}