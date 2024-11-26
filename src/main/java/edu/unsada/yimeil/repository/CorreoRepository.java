package edu.unsada.yimeil.repository;

import edu.unsada.yimeil.models.Correo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            return correo;
        }
    }

    public List<Correo> findAll() {
        String sql = "SELECT * FROM Correo";
        return jdbcTemplate.query(sql, new CorreoRowMapper());
    }

    public Correo findById(int emailId) {
        String sql = "SELECT * FROM Correo WHERE emailId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{emailId}, new CorreoRowMapper());
    }

    public int save(Correo correo) {
        String sql = "INSERT INTO Correo (subject, body, receivedAt) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, correo.getSubject(), correo.getBody(), new Timestamp(System.currentTimeMillis()));
    }

    public int update(Correo correo) {
        String sql = "UPDATE Correo SET subject = ?, body = ?, receivedAt = ? WHERE emailId = ?";
        return jdbcTemplate.update(sql, correo.getSubject(), correo.getBody(), correo.getReceivedAt(), correo.getEmailId());
    }

    public int deleteById(int emailId) {
        String sql = "DELETE FROM Correo WHERE emailId = ?";
        return jdbcTemplate.update(sql, emailId);
    }
}
