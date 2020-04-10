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
import javax.persistence.Lob;
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
@Table(name = "materiaDeAyuda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaDeAyuda.findAll", query = "SELECT m FROM MateriaDeAyuda m")
    , @NamedQuery(name = "MateriaDeAyuda.findByCodigo", query = "SELECT m FROM MateriaDeAyuda m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "MateriaDeAyuda.findByNombre", query = "SELECT m FROM MateriaDeAyuda m WHERE m.nombre = :nombre")})
public class MateriaDeAyuda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMaterialAyuda")
    private Collection<BeneficioPoblacion> beneficioPoblacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMaterialAyuda")
    private Collection<NecesidadesMunicipio> necesidadesMunicipioCollection;

    public MateriaDeAyuda() {
    }

    public MateriaDeAyuda(String codigo) {
        this.codigo = codigo;
    }

    public MateriaDeAyuda(String codigo, String nombre) {
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
    public Collection<BeneficioPoblacion> getBeneficioPoblacionCollection() {
        return beneficioPoblacionCollection;
    }

    public void setBeneficioPoblacionCollection(Collection<BeneficioPoblacion> beneficioPoblacionCollection) {
        this.beneficioPoblacionCollection = beneficioPoblacionCollection;
    }

    @XmlTransient
    public Collection<NecesidadesMunicipio> getNecesidadesMunicipioCollection() {
        return necesidadesMunicipioCollection;
    }

    public void setNecesidadesMunicipioCollection(Collection<NecesidadesMunicipio> necesidadesMunicipioCollection) {
        this.necesidadesMunicipioCollection = necesidadesMunicipioCollection;
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
        if (!(object instanceof MateriaDeAyuda)) {
            return false;
        }
        MateriaDeAyuda other = (MateriaDeAyuda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.MateriaDeAyuda[ codigo=" + codigo + " ]";
    }
    
}
