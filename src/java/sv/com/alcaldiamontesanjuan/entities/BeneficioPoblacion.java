/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "BeneficioPoblacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeneficioPoblacion.findAll", query = "SELECT b FROM BeneficioPoblacion b")
    , @NamedQuery(name = "BeneficioPoblacion.findByCodigo", query = "SELECT b FROM BeneficioPoblacion b WHERE b.codigo = :codigo")
    , @NamedQuery(name = "BeneficioPoblacion.findByCantidad", query = "SELECT b FROM BeneficioPoblacion b WHERE b.cantidad = :cantidad")
    , @NamedQuery(name = "BeneficioPoblacion.findByPrecio", query = "SELECT b FROM BeneficioPoblacion b WHERE b.precio = :precio")
    , @NamedQuery(name = "BeneficioPoblacion.findByFechaDeEntrega", query = "SELECT b FROM BeneficioPoblacion b WHERE b.fechaDeEntrega = :fechaDeEntrega")
    , @NamedQuery(name = "BeneficioPoblacion.findByFechaRegistro", query = "SELECT b FROM BeneficioPoblacion b WHERE b.fechaRegistro = :fechaRegistro")})
public class BeneficioPoblacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "fechaDeEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaDeEntrega;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @JoinColumn(name = "codigoEntrevistado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entrevistado codigoEntrevistado;
    @JoinColumn(name = "codigoMaterialAyuda", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private MateriaDeAyuda codigoMaterialAyuda;

    public BeneficioPoblacion() {
    }

    public BeneficioPoblacion(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Entrevistado getCodigoEntrevistado() {
        return codigoEntrevistado;
    }

    public void setCodigoEntrevistado(Entrevistado codigoEntrevistado) {
        this.codigoEntrevistado = codigoEntrevistado;
    }

    public MateriaDeAyuda getCodigoMaterialAyuda() {
        return codigoMaterialAyuda;
    }

    public void setCodigoMaterialAyuda(MateriaDeAyuda codigoMaterialAyuda) {
        this.codigoMaterialAyuda = codigoMaterialAyuda;
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
        if (!(object instanceof BeneficioPoblacion)) {
            return false;
        }
        BeneficioPoblacion other = (BeneficioPoblacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.BeneficioPoblacion[ codigo=" + codigo + " ]";
    }
    
}
