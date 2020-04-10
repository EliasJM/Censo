/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "necesidadesMunicipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NecesidadesMunicipio.findAll", query = "SELECT n FROM NecesidadesMunicipio n")
    , @NamedQuery(name = "NecesidadesMunicipio.findByCodigo", query = "SELECT n FROM NecesidadesMunicipio n WHERE n.codigo = :codigo")
    , @NamedQuery(name = "NecesidadesMunicipio.findByCantidadPide", query = "SELECT n FROM NecesidadesMunicipio n WHERE n.cantidadPide = :cantidadPide")})
public class NecesidadesMunicipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "cantidadPide")
    private Integer cantidadPide;
    @JoinColumn(name = "codigoMaterialAyuda", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private MateriaDeAyuda codigoMaterialAyuda;
    @JoinColumn(name = "codigoEntrevistado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entrevistado codigoEntrevistado;

    public NecesidadesMunicipio() {
    }

    public NecesidadesMunicipio(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidadPide() {
        return cantidadPide;
    }

    public void setCantidadPide(Integer cantidadPide) {
        this.cantidadPide = cantidadPide;
    }

    public MateriaDeAyuda getCodigoMaterialAyuda() {
        return codigoMaterialAyuda;
    }

    public void setCodigoMaterialAyuda(MateriaDeAyuda codigoMaterialAyuda) {
        this.codigoMaterialAyuda = codigoMaterialAyuda;
    }

    public Entrevistado getCodigoEntrevistado() {
        return codigoEntrevistado;
    }

    public void setCodigoEntrevistado(Entrevistado codigoEntrevistado) {
        this.codigoEntrevistado = codigoEntrevistado;
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
        if (!(object instanceof NecesidadesMunicipio)) {
            return false;
        }
        NecesidadesMunicipio other = (NecesidadesMunicipio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.NecesidadesMunicipio[ codigo=" + codigo + " ]";
    }
    
}
