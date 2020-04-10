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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "integrantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Integrantes.findAll", query = "SELECT i FROM Integrantes i")
    , @NamedQuery(name = "Integrantes.findByCodigo", query = "SELECT i FROM Integrantes i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "Integrantes.findByNombre", query = "SELECT i FROM Integrantes i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Integrantes.findByEdad", query = "SELECT i FROM Integrantes i WHERE i.edad = :edad")
    , @NamedQuery(name = "Integrantes.findByDetalleDePDeserto", query = "SELECT i FROM Integrantes i WHERE i.detalleDePDeserto = :detalleDePDeserto")
    , @NamedQuery(name = "Integrantes.findByIngresosMensuales", query = "SELECT i FROM Integrantes i WHERE i.ingresosMensuales = :ingresosMensuales")})
public class Integrantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edad")
    private Integer edad;
    @Size(max = 150)
    @Column(name = "detalleDePDeserto")
    private String detalleDePDeserto;
    @Column(name = "ingresosMensuales")
    private Integer ingresosMensuales;
    @JoinColumn(name = "codigoEntrevistado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entrevistado codigoEntrevistado;
    @JoinColumn(name = "desertoDelEstudio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica desertoDelEstudio;
    @JoinColumn(name = "nivelAcademico", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private NivelesDeEducacion nivelAcademico;
    @JoinColumn(name = "parentesco", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Parentesco parentesco;
    @JoinColumn(name = "sexo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Sexo sexo;
    @JoinColumn(name = "gustariaSeguirEstudiando", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica gustariaSeguirEstudiando;
    @JoinColumn(name = "sePuedeDesempenar", referencedColumnName = "codigo")
    @ManyToOne
    private ProfesionOficio sePuedeDesempenar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoIntegrante")
    private Collection<IntegrantesEnfermos> integrantesEnfermosCollection;

    public Integrantes() {
    }

    public Integrantes(String codigo) {
        this.codigo = codigo;
    }

    public Integrantes(String codigo, String nombre) {
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDetalleDePDeserto() {
        return detalleDePDeserto;
    }

    public void setDetalleDePDeserto(String detalleDePDeserto) {
        this.detalleDePDeserto = detalleDePDeserto;
    }

    public Integer getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(Integer ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    public Entrevistado getCodigoEntrevistado() {
        return codigoEntrevistado;
    }

    public void setCodigoEntrevistado(Entrevistado codigoEntrevistado) {
        this.codigoEntrevistado = codigoEntrevistado;
    }

    public Aplica getDesertoDelEstudio() {
        return desertoDelEstudio;
    }

    public void setDesertoDelEstudio(Aplica desertoDelEstudio) {
        this.desertoDelEstudio = desertoDelEstudio;
    }

    public NivelesDeEducacion getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(NivelesDeEducacion nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Aplica getGustariaSeguirEstudiando() {
        return gustariaSeguirEstudiando;
    }

    public void setGustariaSeguirEstudiando(Aplica gustariaSeguirEstudiando) {
        this.gustariaSeguirEstudiando = gustariaSeguirEstudiando;
    }

    public ProfesionOficio getSePuedeDesempenar() {
        return sePuedeDesempenar;
    }

    public void setSePuedeDesempenar(ProfesionOficio sePuedeDesempenar) {
        this.sePuedeDesempenar = sePuedeDesempenar;
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
        if (!(object instanceof Integrantes)) {
            return false;
        }
        Integrantes other = (Integrantes) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.Integrantes[ codigo=" + codigo + " ]";
    }
    
}
