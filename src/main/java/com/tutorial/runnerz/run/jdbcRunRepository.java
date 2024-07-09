package com.tutorial.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Qualifier("jdbcRunRepository")
public class jdbcRunRepository implements RunRepository {
    private static final Logger log = LoggerFactory.getLogger(jdbcRunRepository.class);

    // eventually replace by DB

    private final JdbcClient jdbcClient;


    public jdbcRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    //GET findAll
    public List<Run> findAll() {
        log.info("Executing --> jdbcRunRepository --> findAll()");
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    //GET findById
    public Optional<Run> findById(Integer id) {
        log.info("Executing --> jdbcRunRepository --> findById()");
        return jdbcClient.sql("select * from run where id = :id")
                        .param("id", id)
                        .query(Run.class)
                        .optional();
    }

    //POST Create a new run
    public void create(Run run) {
        log.info("Executing --> jdbcRunRepository --> Creating Run =" + run);
        int updated = jdbcClient.sql("insert into run (id, title, started_on, completed_on,miles, location) values (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(),
                        run.title(),
                        run.startedOn(),
                        run.completedOn(),
                        run.miles(),
                        run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed To Create run "+run.title());


    }

    //Update A Run
    public void update(Run run, Integer id) {
        log.info("Executing --> jdbcRunRepository --> Updating Run with id: " + id);
        int updated = jdbcClient.sql("update run set title = ?" +
                        ", started_on = ?" +
                        ", completed_on = ?" +
                        ", miles = ?" +
                        ", location = ? " +
                        "where id = ?")
                .params(List.of(run.title(),
                        run.startedOn(),
                        run.completedOn(),
                        run.miles(),
                        run.location().toString(),
                        id))
                .update();
        Assert.state(updated == 1, "Failed to update run with id: " + id);

    }

    //Delete
    public void delete(Integer id) {
        log.info("Executing -->  jdbcRunRepository -->   Deleting Run with id: " + id);
       int updated = jdbcClient.sql("delete from run where id = ?")
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to delete run with id: " + id);
    }


    //Count
    public int count() {
        log.info("Executing --> jdbcRunRepository --> count()");
        return jdbcClient.sql("select count(*) from run").query().listOfRows().size();
    }

    //Save All
    public void saveAll(List<Run> runs) {
        log.info("Executing --> jdbcRunRepository --> saveAll()");
        runs.forEach(this::create);
    }

    //find by location
    public List<Run> findByLocation(String location) {
        log.info("Executing --> jdbcRunRepository --> findByLocation()");
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }
}
