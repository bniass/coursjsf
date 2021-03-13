/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mac
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
    , @NamedQuery(name = "Location.findByIdlocation", query = "SELECT l FROM Location l WHERE l.idlocation = :idlocation")
    , @NamedQuery(name = "Location.findByDate", query = "SELECT l FROM Location l WHERE l.date = :date")
    , @NamedQuery(name = "Location.findByNbjour", query = "SELECT l FROM Location l WHERE l.nbjour = :nbjour")
    , @NamedQuery(name = "Location.findByCommentaire", query = "SELECT l FROM Location l WHERE l.commentaire = :commentaire")
    , @NamedQuery(name = "Location.findByMontantlocation", query = "SELECT l FROM Location l WHERE l.montantlocation = :montantlocation")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlocation")
    private Integer idlocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbjour")
    private int nbjour;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "commentaire")
    private String commentaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montantlocation")
    private float montantlocation;
    @JoinColumn(name = "idvehicule", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Véhicule idvehicule;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Client idclient;

    public Location() {
    }

    public Location(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public Location(Integer idlocation, Date date, int nbjour, String commentaire, float montantlocation) {
        this.idlocation = idlocation;
        this.date = date;
        this.nbjour = nbjour;
        this.commentaire = commentaire;
        this.montantlocation = montantlocation;
    }

    public Integer getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbjour() {
        return nbjour;
    }

    public void setNbjour(int nbjour) {
        this.nbjour = nbjour;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public float getMontantlocation() {
        return montantlocation;
    }

    public void setMontantlocation(float montantlocation) {
        this.montantlocation = montantlocation;
    }

    public Véhicule getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(Véhicule idvehicule) {
        this.idvehicule = idvehicule;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocation != null ? idlocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.idlocation == null && other.idlocation != null) || (this.idlocation != null && !this.idlocation.equals(other.idlocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Location[ idlocation=" + idlocation + " ]";
    }
    
}
