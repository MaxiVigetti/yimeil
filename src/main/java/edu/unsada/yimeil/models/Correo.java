
package edu.unsada.yimeil.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Correo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "emailId")
    private int emailId;

    @Basic
    @Column(name = "subject")
    private String subject;

    @Basic
    @Column(name = "body")
    private String body;

    @Basic
    @Column(name = "receivedAt")
    private Timestamp receivedAt;

    @OneToMany(mappedBy = "correoByCorreoEmailId")
    private List<Attachments> attachmentsByEmailId;

    @OneToMany(mappedBy = "correoByCorreoEmailId")
    private List<DestinatariosFrom> destinatariosFromsByEmailId;

    @Basic
    @Column(name = "userId")
    private String userId;

    @Transient
    private String from;

    @Transient
    private List<String> to;

    // Constructor sin parámetros
    public Correo() {}

    // Constructor con todos los parámetros
    public Correo(int emailId, String subject, String body, Timestamp receivedAt, String userId, List<Attachments> attachmentsByEmailId, List<DestinatariosFrom> destinatariosFromsByEmailId) {
        this.emailId = emailId;
        this.subject = subject;
        this.body = body;
        this.receivedAt = receivedAt;
        this.userId = userId;
        this.attachmentsByEmailId = attachmentsByEmailId;
        this.destinatariosFromsByEmailId = destinatariosFromsByEmailId;
    }

    // Getters y setters
    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Timestamp receivedAt) {
        this.receivedAt = receivedAt;
    }

    public List<Attachments> getAttachmentsByEmailId() {
        return attachmentsByEmailId;
    }

    public void setAttachmentsByEmailId(List<Attachments> attachmentsByEmailId) {
        this.attachmentsByEmailId = attachmentsByEmailId;
    }

    public List<DestinatariosFrom> getDestinatariosFromsByEmailId() {
        return destinatariosFromsByEmailId;
    }

    public void setDestinatariosFromsByEmailId(List<DestinatariosFrom> destinatariosFromsByEmailId) {
        this.destinatariosFromsByEmailId = destinatariosFromsByEmailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }
}
