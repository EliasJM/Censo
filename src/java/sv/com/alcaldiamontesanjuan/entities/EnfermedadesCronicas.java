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
@Table(name = "enfermedadesCronicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfermedadesCronicas.findAll", query = "SELECT e FROM EnfermedadesCronicas e")
    , @NamedQuery(name = "EnfermedadesCronicas.findByCodigo", query = "SELECT e FROM EnfermedadesCronicas e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "EnfermedadesCronicas.findByNombre", query = "SELECT e FROM EnfermedadesCronicas e WHERE e.nombre = :nombre")})
public class EnfermedadesCronicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEnfermedadCronica")
    private Collection<FamiliasConEnfermedadesCronicas> familiasConEnfermedadesCronicasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discapacidadEnfermedad")
    private Collection<IntegrantesEnfermos> integrantesEnfermosCollection;

    public EnfermedadesCronicas() {
    }

    public EnfermedadesCronicas(String codigo) {
        this.codigo = codigo;
    }

    public EnfermedadesCronicas(String codigo, String nombre) {
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
    public Collection<FamiliasConEnfermedadesCronicas> getFamiliasConEnfermedadesCronicasCollection() {
        return familiasConEnfermedadesCronicasCollection;
    }

    public void setFamiliasConEnfermedadesCronicasCollection(Collection<FamiliasConEnfermedadesCronicas> familiasConEnfermedadesCronicasCollection) {
        this.familiasConEnfermedadesCronicasCollection = familiasConEnfermedadesCronicasCollection;
    }

    @XmlTransient
    public Collection<IntegrantesEnfermos> getIntegrantesEnfermosCollection() {
        return integrantesEnfermosCollection;
    }

    public void setIntegrantesEnfermosCollection(Collection<IntegrantesEnfermos> integrantesEnfermosCollection) {
        this.integrantesEnfermosCollection = integrantesEnfermosCollection;
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
        if (!(object instanceof EnfermedadesCronicas)) {
            return false;
        }
        EnfermedadesCronicas other = (EnfermedadesCronicas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.EnfermedadesCronicas[ codigo=" + codigo + " ]";
    }
    
}
