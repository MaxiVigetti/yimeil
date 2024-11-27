package edu.unsada.yimeil.repository;

import edu.unsada.yimeil.models.Correo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class CorreoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class CorreoRowMapper implements RowMapper<Correo> {
        @Override
        public Correo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Correo correo = new Correo();
            correo.setEmailId(rs.getInt("emailId"));
            correo.setSubject(rs.getString("subject"));
            correo.setBody(rs.getString("body"));
            correo.setReceivedAt(rs.getTimestamp("receivedAt"));
            correo.setFrom(rs.getString("from")); // Campo "from"
            correo.setUserId(rs.getString("userId"));
            return correo;
        }
    }

    public List<Correo> findAll() {
        String sql = "SELECT * FROM correo";
        return jdbcTemplate.query(sql, new CorreoRowMapper());
    }

    public Correo findById(int emailId) {
        String sql = "SELECT * FROM correo WHERE emailId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{emailId}, new CorreoRowMapper());
    }

    public int save(Correo correo) {
        String sql = "INSERT INTO correo (subject, body, receivedAt, `from`, userId) VALUES (?, ?, ?, ?, ?)"; // Campo "from"

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, correo.getSubject());
                ps.setString(2, correo.getBody());
                ps.setTimestamp(3, correo.getReceivedAt());
                ps.setString(4, correo.getFrom()); // Campo "from"
                ps.setString(5, correo.getUserId());
                return ps;
            }
        }, keyHolder);

        int newEmailId = keyHolder.getKey().intValue();
        correo.setEmailId(newEmailId);

        return newEmailId;
    }

    public int update(Correo correo) {
        String sql = "UPDATE correo SET subject = ?, body = ?, receivedAt = ?, `from` = ?, userId = ? WHERE emailId = ?"; // Campo "from"
        return jdbcTemplate.update(sql, correo.getSubject(), correo.getBody(), correo.getReceivedAt(), correo.getFrom(), correo.getUserId(), correo.getEmailId());
    }

    public int deleteById(int emailId) {
        String sql = "DELETE FROM correo WHERE emailId = ?";
        return jdbcTemplate.update(sql, emailId);
    }
}
