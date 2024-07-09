package com.tutorial.runnerz;

import com.tutorial.runnerz.run.Location;
import com.tutorial.runnerz.run.Run;
import com.tutorial.runnerz.run.RunRepository;
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

//   Use this to see the name of all the BEAN that arte getting created.
//        for (String s : apc.getBeanDefinitionNames()) {
//            log.info("Load into IOC Container->"+s);
//        }
        log.info("Application loaded Successfully");
    }

    @Bean
    CommandLineRunner runner(RunRepository runRepository    ) {
        return args -> {
            Run run = new Run(
                    1,
                    "Mik first Run",
                    LocalDateTime.now(),
                    LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                    5,
                    Location.OUTDOOR);

            runRepository.create(run);

            log.info("Application ->Runner: " + run);

        };
    }

}