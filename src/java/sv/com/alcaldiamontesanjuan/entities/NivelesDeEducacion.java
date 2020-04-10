/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "nivelesDeEducacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelesDeEducacion.findAll", query = "SELECT n FROM NivelesDeEducacion n")
    , @NamedQuery(name = "NivelesDeEducacion.findByCodigo", query = "SELECT n FROM NivelesDeEducacion n WHERE n.codigo = :codigo")
    , @NamedQuery(name = "NivelesDeEducacion.findByNombre", query = "SELECT n FROM NivelesDeEducacion n WHERE n.nombre = :nombre")})
public class NivelesDeEducacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelAcademico")
    private Collection<Integrantes> integrantesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoNivelesEducacion")
    private Collection<Entrevistado> entrevistadoCollection;

    public NivelesDeEducacion() {
    }

    public NivelesDeEducacion(String codigo) {
        this.codigo = codigo;
    }

    public NivelesDeEducacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Integrantes> getIntegrantesCollection() {
        return integrantesCollection;
    }

    public void setIntegrantesCollection(Collection<Integrantes> integrantesCollection) {
        this.integrantesCollection = integrantesCollection;
    }

    @XmlTransient
    public Collection<Entrevistado> getEntrevistadoCollection() {
        return entrevistadoCollection;
    }

    public void setEntrevistadoCollection(Collection<Entrevistado> entrevistadoCollection) {
        this.entrevistadoCollection = entrevistadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelesDeEducacion)) {
            return false;
        }
        NivelesDeEducacion other = (NivelesDeEducacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.NivelesDeEducacion[ codigo=" + codigo + " ]";
    }
    
}
