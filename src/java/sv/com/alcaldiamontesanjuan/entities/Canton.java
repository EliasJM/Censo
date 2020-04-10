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
@Table(name = "canton")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canton.findAll", query = "SELECT c FROM Canton c")
    , @NamedQuery(name = "Canton.findByCodigo", query = "SELECT c FROM Canton c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Canton.findByNombre", query = "SELECT c FROM Canton c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Canton.findByDescripcion", query = "SELECT c FROM Canton c WHERE c.descripcion = :descripcion")})
public class Canton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCanton")
    private Collection<Entrevistado> entrevistadoCollection;
    @OneToMany(mappedBy = "codigoCanton")
    private Collection<Caserio> caserioCollection;

    public Canton() {
    }

    public Canton(String codigo) {
        this.codigo = codigo;
    }

    public Canton(String codigo, String nombre) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Entrevistado> getEntrevistadoCollection() {
        return entrevistadoCollection;
    }

    public void setEntrevistadoCollection(Collection<Entrevistado> entrevistadoCollection) {
        this.entrevistadoCollection = entrevistadoCollection;
    }

    @XmlTransient
    public Collection<Caserio> getCaserioCollection() {
        return caserioCollection;
    }

    public void setCaserioCollection(Collection<Caserio> caserioCollection) {
        this.caserioCollection = caserioCollection;
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
        if (!(object instanceof Canton)) {
            return false;
        }
        Canton other = (Canton) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.Canton[ codigo=" + codigo + " ]";
    }
    
}
