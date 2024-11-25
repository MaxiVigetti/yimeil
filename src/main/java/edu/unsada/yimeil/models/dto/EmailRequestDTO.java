package edu.unsada.yimeil.models.dto;

import java.util.List;

public class EmailRequestDTO {
    private String token;         // Token de autenticación.
    private String systemId;      // ID del sistema.
    private String from;          // Dirección de correo del remitente.
    private List<String> to;      // Lista de destinatarios.
    private String subject;       // Asunto del correo, opcional.
    private String body;          // Cuerpo del correo, opcional.
    private List<AttachmentResponse> attachments; // Nombre y URLs de los archivos subidos a Draiv, opcional.

    // Constructor
    public EmailRequestDTO() {}

    public EmailRequestDTO(String token, String systemId, String from, List<String> to, String subject, String body, List<AttachmentResponse> attachments) {
        this.token = token;
        this.systemId = systemId;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
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

    public List<AttachmentResponse> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentResponse> attachments) {
        this.attachments = attachments;
    }
}
