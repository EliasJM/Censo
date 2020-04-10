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
@Table(name = "seCultivanProductosAgricolas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeCultivanProductosAgricolas.findAll", query = "SELECT s FROM SeCultivanProductosAgricolas s")
    , @NamedQuery(name = "SeCultivanProductosAgricolas.findByCodigo", query = "SELECT s FROM SeCultivanProductosAgricolas s WHERE s.codigo = :codigo")})
public class SeCultivanProductosAgricolas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "codigo")
    private String codigo;
    @JoinColumn(name = "codigoEntrevistado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entrevistado codigoEntrevistado;
    @JoinColumn(name = "codigoPoducto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ProductosAgricolas codigoPoducto;

    public SeCultivanProductosAgricolas() {
    }

    public SeCultivanProductosAgricolas(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Entrevistado getCodigoEntrevistado() {
        return codigoEntrevistado;
    }

    public void setCodigoEntrevistado(Entrevistado codigoEntrevistado) {
        this.codigoEntrevistado = codigoEntrevistado;
    }

    public ProductosAgricolas getCodigoPoducto() {
        return codigoPoducto;
    }

    public void setCodigoPoducto(ProductosAgricolas codigoPoducto) {
        this.codigoPoducto = codigoPoducto;
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
        if (!(object instanceof SeCultivanProductosAgricolas)) {
            return false;
        }
        SeCultivanProductosAgricolas other = (SeCultivanProductosAgricolas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.SeCultivanProductosAgricolas[ codigo=" + codigo + " ]";
    }
    
}
