package edu.unsada.yimeil.repository;

import edu.unsada.yimeil.models.Attachments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AttachmentsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deleteAll(List<Attachments> attachments) {
        for (Attachments attachment : attachments) {
            deleteById(attachment.getIdattachments());
        }
    }

    public void deleteByCorreoEmailId(int correoEmailId) {
        String sql = "DELETE FROM attachments WHERE correo_emailId = ?";
        jdbcTemplate.update(sql, correoEmailId);
    }

    private static class AttachmentsRowMapper implements RowMapper<Attachments> {
        @Override
        public Attachments mapRow(ResultSet rs, int rowNum) throws SQLException {
            Attachments attachment = new Attachments();
            attachment.setIdattachments(rs.getInt("idattachments"));
            attachment.setFilename(rs.getString("filename"));
            attachment.setUrl(rs.getString("url"));
            attachment.setCorreoEmailId(rs.getInt("correo_emailId"));
            return attachment;
        }
    }

    public List<Attachments> findAll() {
        String sql = "SELECT * FROM attachments";
        return jdbcTemplate.query(sql, new AttachmentsRowMapper());
    }

    public Attachments findById(int id) {
        String sql = "SELECT * FROM attachments WHERE idattachments = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AttachmentsRowMapper());
    }

    public List<Attachments> findByCorreoEmailId(int correoEmailId) {
        String sql = "SELECT * FROM attachments WHERE correo_emailId = ?";
        return jdbcTemplate.query(sql, new Object[]{correoEmailId}, new AttachmentsRowMapper());
    }

    public int save(Attachments attachment) {
        String sql = "INSERT INTO attachments (filename, url, correo_emailId) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, attachment.getFilename(), attachment.getUrl(), attachment.getCorreoEmailId());
    }

    public int update(Attachments attachment) {
        String sql = "UPDATE attachments SET filename = ?, url = ?, correo_emailId = ? WHERE idattachments = ?";
        return jdbcTemplate.update(sql, attachment.getFilename(), attachment.getUrl(), attachment.getCorreoEmailId(), attachment.getIdattachments());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM attachments WHERE idattachments = ?";
        return jdbcTemplate.update(sql, id);
    }
}
