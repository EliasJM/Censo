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
@Table(name = "aplica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aplica.findAll", query = "SELECT a FROM Aplica a")
    , @NamedQuery(name = "Aplica.findByCodigo", query = "SELECT a FROM Aplica a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Aplica.findByNombre", query = "SELECT a FROM Aplica a WHERE a.nombre = :nombre")})
public class Aplica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "energiaElectrica")
    private Collection<Vivienda> viviendaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paredesDanadas")
    private Collection<Vivienda> viviendaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioAseo")
    private Collection<Vivienda> viviendaCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "techoDanado")
    private Collection<Vivienda> viviendaCollection3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registrada")
    private Collection<Vivienda> viviendaCollection4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aguaPotable")
    private Collection<Vivienda> viviendaCollection5;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "predioMunicipal")
    private Collection<Vivienda> viviendaCollection6;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "construidaPorChilden")
    private Collection<Vivienda> viviendaCollection7;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puertasDanadas")
    private Collection<Vivienda> viviendaCollection8;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventanasDanadas")
    private Collection<Vivienda> viviendaCollection9;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poseeCuartos")
    private Collection<Vivienda> viviendaCollection10;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poseeEscrituras")
    private Collection<Vivienda> viviendaCollection11;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "desertoDelEstudio")
    private Collection<Integrantes> integrantesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gustariaSeguirEstudiando")
    private Collection<Integrantes> integrantesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recibioAyudaAsusNecesidades")
    private Collection<Entrevistado> entrevistadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poseeDiscapacidadFisica")
    private Collection<Entrevistado> entrevistadoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recibeInsumoAgricolas")
    private Collection<Entrevistado> entrevistadoCollection2;

    public Aplica() {
    }

    public Aplica(String codigo) {
        this.codigo = codigo;
    }

    public Aplica(String codigo, String nombre) {
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

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection1() {
        return viviendaCollection1;
    }

    public void setViviendaCollection1(Collection<Vivienda> viviendaCollection1) {
        this.viviendaCollection1 = viviendaCollection1;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection2() {
        return viviendaCollection2;
    }

    public void setViviendaCollection2(Collection<Vivienda> viviendaCollection2) {
        this.viviendaCollection2 = viviendaCollection2;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection3() {
        return viviendaCollection3;
    }

    public void setViviendaCollection3(Collection<Vivienda> viviendaCollection3) {
        this.viviendaCollection3 = viviendaCollection3;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection4() {
        return viviendaCollection4;
    }

    public void setViviendaCollection4(Collection<Vivienda> viviendaCollection4) {
        this.viviendaCollection4 = viviendaCollection4;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection5() {
        return viviendaCollection5;
    }

    public void setViviendaCollection5(Collection<Vivienda> viviendaCollection5) {
        this.viviendaCollection5 = viviendaCollection5;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection6() {
        return viviendaCollection6;
    }

    public void setViviendaCollection6(Collection<Vivienda> viviendaCollection6) {
        this.viviendaCollection6 = viviendaCollection6;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection7() {
        return viviendaCollection7;
    }

    public void setViviendaCollection7(Collection<Vivienda> viviendaCollection7) {
        this.viviendaCollection7 = viviendaCollection7;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection8() {
        return viviendaCollection8;
    }

    public void setViviendaCollection8(Collection<Vivienda> viviendaCollection8) {
        this.viviendaCollection8 = viviendaCollection8;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection9() {
        return viviendaCollection9;
    }

    public void setViviendaCollection9(Collection<Vivienda> viviendaCollection9) {
        this.viviendaCollection9 = viviendaCollection9;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection10() {
        return viviendaCollection10;
    }

    public void setViviendaCollection10(Collection<Vivienda> viviendaCollection10) {
        this.viviendaCollection10 = viviendaCollection10;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection11() {
        return viviendaCollection11;
    }

    public void setViviendaCollection11(Collection<Vivienda> viviendaCollection11) {
        this.viviendaCollection11 = viviendaCollection11;
    }

    @XmlTransient
    public Collection<Integrantes> getIntegrantesCollection() {
        return integrantesCollection;
    }

    public void setIntegrantesCollection(Collection<Integrantes> integrantesCollection) {
        this.integrantesCollection = integrantesCollection;
    }

    @XmlTransient
    public Collection<Integrantes> getIntegrantesCollection1() {
        return integrantesCollection1;
    }

    public void setIntegrantesCollection1(Collection<Integrantes> integrantesCollection1) {
        this.integrantesCollection1 = integrantesCollection1;
    }

    @XmlTransient
    public Collection<Entrevistado> getEntrevistadoCollection() {
        return entrevistadoCollection;
    }

    public void setEntrevistadoCollection(Collection<Entrevistado> entrevistadoCollection) {
        this.entrevistadoCollection = entrevistadoCollection;
    }

    @XmlTransient
    public Collection<Entrevistado> getEntrevistadoCollection1() {
        return entrevistadoCollection1;
    }

    public void setEntrevistadoCollection1(Collection<Entrevistado> entrevistadoCollection1) {
        this.entrevistadoCollection1 = entrevistadoCollection1;
    }

    @XmlTransient
    public Collection<Entrevistado> getEntrevistadoCollection2() {
        return entrevistadoCollection2;
    }

    public void setEntrevistadoCollection2(Collection<Entrevistado> entrevistadoCollection2) {
        this.entrevistadoCollection2 = entrevistadoCollection2;
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
        if (!(object instanceof Aplica)) {
            return false;
        }
        Aplica other = (Aplica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.Aplica[ codigo=" + codigo + " ]";
    }
    
}
