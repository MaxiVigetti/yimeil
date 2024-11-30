package edu.unsada.yimeil.repository;

import edu.unsada.yimeil.models.DestinatariosFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DestinatariosFromRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class DestinatariosFromRowMapper implements RowMapper<DestinatariosFrom> {
        @Override
        public DestinatariosFrom mapRow(ResultSet rs, int rowNum) throws SQLException {
            DestinatariosFrom destinatariosFrom = new DestinatariosFrom();
            destinatariosFrom.setIdfrom(rs.getInt("idfrom"));
            destinatariosFrom.setEmail(rs.getString("email"));
            destinatariosFrom.setCorreoEmailId(rs.getInt("correo_emailId"));
            return destinatariosFrom;
        }
    }

    public List<DestinatariosFrom> findAll() {
        String sql = "SELECT * FROM `from`";
        return jdbcTemplate.query(sql, new DestinatariosFromRowMapper());
    }

    public DestinatariosFrom findById(int id) {
        String sql = "SELECT * FROM `from` WHERE idfrom = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DestinatariosFromRowMapper());
    }

    // Método para encontrar destinatarios por correo_emailId
    public List<DestinatariosFrom> findByCorreoEmailId(int correoEmailId) {
        String sql = "SELECT * FROM `from` WHERE correo_emailId = ?";
        return jdbcTemplate.query(sql, new Object[]{correoEmailId}, new DestinatariosFromRowMapper());
    }

    public int save(DestinatariosFrom destinatariosFrom) {
        String sql = "INSERT INTO `from` (email, correo_emailId) VALUES (?, ?)";
        return jdbcTemplate.update(sql, destinatariosFrom.getEmail(), destinatariosFrom.getCorreoEmailId());
    }

    public int update(DestinatariosFrom destinatariosFrom) {
        String sql = "UPDATE `from` SET email = ?, correo_emailId = ? WHERE idfrom = ?";
        return jdbcTemplate.update(sql, destinatariosFrom.getEmail(), destinatariosFrom.getCorreoEmailId(), destinatariosFrom.getIdfrom());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM `from` WHERE idfrom = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Método para eliminar destinatarios por correo_emailId
    public void deleteByCorreoEmailId(int correoEmailId) {
        String sql = "DELETE FROM `from` WHERE correo_emailId = ?";
        jdbcTemplate.update(sql, correoEmailId);
    }
}
