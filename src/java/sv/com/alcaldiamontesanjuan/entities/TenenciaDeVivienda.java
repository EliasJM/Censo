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
@Table(name = "tenenciaDeVivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TenenciaDeVivienda.findAll", query = "SELECT t FROM TenenciaDeVivienda t")
    , @NamedQuery(name = "TenenciaDeVivienda.findByCodigo", query = "SELECT t FROM TenenciaDeVivienda t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TenenciaDeVivienda.findByNombre", query = "SELECT t FROM TenenciaDeVivienda t WHERE t.nombre = :nombre")})
public class TenenciaDeVivienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTenenciaDeVivienda")
    private Collection<Vivienda> viviendaCollection;

    public TenenciaDeVivienda() {
    }

    public TenenciaDeVivienda(String codigo) {
        this.codigo = codigo;
    }

    public TenenciaDeVivienda(String codigo, String nombre) {
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
    public Collection<Vivienda> getViviendaCollection() {
        return viviendaCollection;
    }

    public void setViviendaCollection(Collection<Vivienda> viviendaCollection) {
        this.viviendaCollection = viviendaCollection;
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
        if (!(object instanceof TenenciaDeVivienda)) {
            return false;
        }
        TenenciaDeVivienda other = (TenenciaDeVivienda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.TenenciaDeVivienda[ codigo=" + codigo + " ]";
    }
    
}
