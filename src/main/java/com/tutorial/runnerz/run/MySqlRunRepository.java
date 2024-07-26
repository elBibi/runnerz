package com.tutorial.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Qualifier("mySqlRunRepository")
public class MySqlRunRepository implements RunRepository {
    private static final Logger log = LoggerFactory.getLogger(jdbcRunRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public MySqlRunRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deleteAll() {
        log.info("Executing --> MySqlRepository --> deleteAll()");
        jdbcTemplate.update("DELETE FROM run");
    }

    @Override
    public List<Run> findAll() {
        log.info("Executing --> MySqlRepository --> findAll()");
        return jdbcTemplate.query("SELECT * FROM run", new RunRowMapper());
    }

    @Override
    public Optional<Run> findById(Integer id) {
        log.info("Executing --> MySqlRepository --> findById()");
        return jdbcTemplate.query("SELECT * FROM run WHERE id = ?", new Object[]{id}, new RunRowMapper())
                .stream()
                .findFirst();
    }


    // POST Create a new run
    @Override
    public void create(Run run) {
        log.info("Executing --> MySqlRunRepository --> Create Run =>>" + run);
        String sql = "INSERT INTO run (id, title, started_on, completed_on, miles, location) VALUES (?, ?, ?, ?, ?, ?)";
        log.info("-->SQL Statement:-------> " + sql);
        String locationS = run.location().name();
        log.info("-->Parameters: id = " + run.id() + ", title = " + run.title() + ", startedOn = " + run.startedOn() + ", completedOn = " + run.completedOn() + ", miles = " + run.miles() + ", location = " + locationS);
        int updated = jdbcTemplate.update(sql, run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), locationS);
        Assert.state(updated == 1, "Failed to create run " + run.id());
    }

    // Update RUN
    @Override
    public void update(Run run, Integer id) {
        log.info("Executing --> MySqlRepository --> Updating Run with id: " + id);
        String sql = "UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?";
        log.info("-->SQL Statement:-------> " + sql);
        String locationS = run.location().name();
        int updated = jdbcTemplate.update("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?",
                run.title(), run.startedOn(), run.completedOn(), run.miles(), locationS, id);
        Assert.state(updated == 1, "Failed to update run with id: " + id);
    }

    @Override
    public void delete(Integer id) {
        log.info("Executing -->  MySqlRepository -->   Deleting Run with id: " + id);
        int updated = jdbcTemplate.update("DELETE FROM run WHERE id = ?", id);
        Assert.state(updated == 1, "Failed to delete run with id: " + id);
    }

    @Override
    public int count() {
        log.info("Executing --> MySqlRepository --> count()="+jdbcTemplate.queryForObject("SELECT COUNT(*) FROM run", Integer.class));
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM run", Integer.class);
    }

    @Override
    public void saveAll(List<Run> runs) {
        log.info("Executing --> MySqlRepository --> saveAll()");
        runs.forEach(this::create);
    }
}
