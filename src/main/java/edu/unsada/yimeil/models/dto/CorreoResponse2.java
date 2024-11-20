package edu.unsada.yimeil.models.dto;

import java.util.List;

public class CorreoResponse2 {
    private int emailId;
    private List<String> to;
    private String from;
    private String subject;
    private boolean hasAttachments;
    private String receivedAt;

    public CorreoResponse2(int emailId, List<String> to, String from, String subject, boolean hasAttachments, String receivedAt) {
        this.emailId = emailId;
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.hasAttachments = hasAttachments;
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

    public boolean isHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }
}
