
package edu.unsada.yimeil.models.dto;

import java.util.List;

public class CorreoResponse {
    private int emailId;
    private List<String> to;
    private String from;
    private String subject;
    private String body;
    private List<AttachmentResponse> attachments;
    private String receivedAt;

    public CorreoResponse(int emailId, List<String> to, String from, String subject, String body, List<AttachmentResponse> attachments, String receivedAt) {
        this.emailId = emailId;
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
        this.receivedAt = receivedAt;
    }

    // Getters y setters
    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }
}
