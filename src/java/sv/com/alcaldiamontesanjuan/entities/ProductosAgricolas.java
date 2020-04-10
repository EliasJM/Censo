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
@Table(name = "productosAgricolas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosAgricolas.findAll", query = "SELECT p FROM ProductosAgricolas p")
    , @NamedQuery(name = "ProductosAgricolas.findByCodigo", query = "SELECT p FROM ProductosAgricolas p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "ProductosAgricolas.findByNombre", query = "SELECT p FROM ProductosAgricolas p WHERE p.nombre = :nombre")})
public class ProductosAgricolas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPoducto")
    private Collection<SeCultivanProductosAgricolas> seCultivanProductosAgricolasCollection;

    public ProductosAgricolas() {
    }

    public ProductosAgricolas(String codigo) {
        this.codigo = codigo;
    }

    public ProductosAgricolas(String codigo, String nombre) {
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
    public Collection<SeCultivanProductosAgricolas> getSeCultivanProductosAgricolasCollection() {
        return seCultivanProductosAgricolasCollection;
    }

    public void setSeCultivanProductosAgricolasCollection(Collection<SeCultivanProductosAgricolas> seCultivanProductosAgricolasCollection) {
        this.seCultivanProductosAgricolasCollection = seCultivanProductosAgricolasCollection;
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
        if (!(object instanceof ProductosAgricolas)) {
            return false;
        }
        ProductosAgricolas other = (ProductosAgricolas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.ProductosAgricolas[ codigo=" + codigo + " ]";
    }
    
}
