package com.tutorial.runnerz.run;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RunRowMapper implements RowMapper<Run> {
    @Override
    public Run mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Run(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getTimestamp("started_on").toLocalDateTime(),
                rs.getTimestamp("completed_on").toLocalDateTime(),
                rs.getInt("miles"),
                Location.valueOf(rs.getString("location"))
        );
    }
}
