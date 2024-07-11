package com.tutorial.runnerz.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);

    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(ObjectMapper objectMapper, @Qualifier("jdbcRunRepository") RunRepository runRepository) {
        this.objectMapper = objectMapper;
        this.runRepository = runRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading Runs from JSON data.--->>>>>>>>>>>>"+runRepository.toString());
        // Supprimer les données existantes
        runRepository.deleteAll();
        log.info("Existing runs deleted.");

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
            Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
            log.info("Reading {} runs from JSON data and saving it to the database.", allRuns.runs().size());
            runRepository.saveAll(allRuns.runs());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
    }
}
