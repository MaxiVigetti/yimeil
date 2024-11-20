package edu.unsada.yimeil.models;

import javax.persistence.*;

@Entity
@Table(name = "destinatarios_from", schema = "yimeil", catalog = "")
public class DestinatariosFrom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idfrom")
    private int idfrom;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "correo_emailId")
    private int correoEmailId;

    @ManyToOne
    @JoinColumn(name = "correo_emailId", referencedColumnName = "emailId", nullable = false, insertable = false, updatable = false)
    private Correo correoByCorreoEmailId;

    // Constructor sin parámetros
    public DestinatariosFrom() {}

    // Constructor con todos los parámetros
    public DestinatariosFrom(int idfrom, String email, int correoEmailId, Correo correoByCorreoEmailId) {
        this.idfrom = idfrom;
        this.email = email;
        this.correoEmailId = correoEmailId;
        this.correoByCorreoEmailId = correoByCorreoEmailId;
    }

    // Getters y setters
    public int getIdfrom() {
        return idfrom;
    }

    public void setIdfrom(int idfrom) {
        this.idfrom = idfrom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCorreoEmailId() {
        return correoEmailId;
    }

    public void setCorreoEmailId(int correoEmailId) {
        this.correoEmailId = correoEmailId;
    }

    public Correo getCorreoByCorreoEmailId() {
        return correoByCorreoEmailId;
    }

    public void setCorreoByCorreoEmailId(Correo correoByCorreoEmailId) {
        this.correoByCorreoEmailId = correoByCorreoEmailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DestinatariosFrom that = (DestinatariosFrom) o;

        if (idfrom != that.idfrom) return false;
        if (correoEmailId != that.correoEmailId) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = idfrom;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + correoEmailId;
        return result;
    }
}
