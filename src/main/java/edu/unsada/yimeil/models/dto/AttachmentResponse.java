package edu.unsada.yimeil.models.dto;

public class AttachmentResponse {
    private String filename;
    private String url;

    public AttachmentResponse(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }

    // Getters y setters
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
