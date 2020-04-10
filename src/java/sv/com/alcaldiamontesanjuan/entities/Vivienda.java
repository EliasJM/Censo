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
@Table(name = "vivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vivienda.findAll", query = "SELECT v FROM Vivienda v")
    , @NamedQuery(name = "Vivienda.findById", query = "SELECT v FROM Vivienda v WHERE v.id = :id")
    , @NamedQuery(name = "Vivienda.findByNombreJefeDeHogar", query = "SELECT v FROM Vivienda v WHERE v.nombreJefeDeHogar = :nombreJefeDeHogar")
    , @NamedQuery(name = "Vivienda.findByCantidadCuartos", query = "SELECT v FROM Vivienda v WHERE v.cantidadCuartos = :cantidadCuartos")
    , @NamedQuery(name = "Vivienda.findByCuartosDormitorio", query = "SELECT v FROM Vivienda v WHERE v.cuartosDormitorio = :cuartosDormitorio")
    , @NamedQuery(name = "Vivienda.findByPersonasTrabajan", query = "SELECT v FROM Vivienda v WHERE v.personasTrabajan = :personasTrabajan")
    , @NamedQuery(name = "Vivienda.findByPersonasAPEmpleo", query = "SELECT v FROM Vivienda v WHERE v.personasAPEmpleo = :personasAPEmpleo")
    , @NamedQuery(name = "Vivienda.findByHogaresEnLaVivienda", query = "SELECT v FROM Vivienda v WHERE v.hogaresEnLaVivienda = :hogaresEnLaVivienda")})
public class Vivienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombreJefeDeHogar")
    private String nombreJefeDeHogar;
    @Column(name = "cantidadCuartos")
    private Integer cantidadCuartos;
    @Column(name = "cuartosDormitorio")
    private Integer cuartosDormitorio;
    @Column(name = "personasTrabajan")
    private Integer personasTrabajan;
    @Column(name = "personasAPEmpleo")
    private Integer personasAPEmpleo;
    @Column(name = "hogaresEnLaVivienda")
    private Integer hogaresEnLaVivienda;
    @JoinColumn(name = "energiaElectrica", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica energiaElectrica;
    @JoinColumn(name = "paredesDanadas", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica paredesDanadas;
    @JoinColumn(name = "servicioAseo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica servicioAseo;
    @JoinColumn(name = "techoDanado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica techoDanado;
    @JoinColumn(name = "registrada", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica registrada;
    @JoinColumn(name = "codigoTipoLetrina", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoLetrina codigoTipoLetrina;
    @JoinColumn(name = "codigoMaterial", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private MaterialDeVivienda codigoMaterial;
    @JoinColumn(name = "codigoTecho", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Techo codigoTecho;
    @JoinColumn(name = "aguaPotable", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica aguaPotable;
    @JoinColumn(name = "predioMunicipal", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica predioMunicipal;
    @JoinColumn(name = "construidaPorChilden", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica construidaPorChilden;
    @JoinColumn(name = "puertasDanadas", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica puertasDanadas;
    @JoinColumn(name = "codigoTenenciaDeVivienda", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TenenciaDeVivienda codigoTenenciaDeVivienda;
    @JoinColumn(name = "ventanasDanadas", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica ventanasDanadas;
    @JoinColumn(name = "poseeCuartos", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica poseeCuartos;
    @JoinColumn(name = "codigoEntrevistado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entrevistado codigoEntrevistado;
    @JoinColumn(name = "poseeEscrituras", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica poseeEscrituras;

    public Vivienda() {
    }

    public Vivienda(String id) {
        this.id = id;
    }

    public Vivienda(String id, String nombreJefeDeHogar) {
        this.id = id;
        this.nombreJefeDeHogar = nombreJefeDeHogar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreJefeDeHogar() {
        return nombreJefeDeHogar;
    }

    public void setNombreJefeDeHogar(String nombreJefeDeHogar) {
        this.nombreJefeDeHogar = nombreJefeDeHogar;
    }

    public Integer getCantidadCuartos() {
        return cantidadCuartos;
    }

    public void setCantidadCuartos(Integer cantidadCuartos) {
        this.cantidadCuartos = cantidadCuartos;
    }

    public Integer getCuartosDormitorio() {
        return cuartosDormitorio;
    }

    public void setCuartosDormitorio(Integer cuartosDormitorio) {
        this.cuartosDormitorio = cuartosDormitorio;
    }

    public Integer getPersonasTrabajan() {
        return personasTrabajan;
    }

    public void setPersonasTrabajan(Integer personasTrabajan) {
        this.personasTrabajan = personasTrabajan;
    }

    public Integer getPersonasAPEmpleo() {
        return personasAPEmpleo;
    }

    public void setPersonasAPEmpleo(Integer personasAPEmpleo) {
        this.personasAPEmpleo = personasAPEmpleo;
    }

    public Integer getHogaresEnLaVivienda() {
        return hogaresEnLaVivienda;
    }

    public void setHogaresEnLaVivienda(Integer hogaresEnLaVivienda) {
        this.hogaresEnLaVivienda = hogaresEnLaVivienda;
    }

    public Aplica getEnergiaElectrica() {
        return energiaElectrica;
    }

    public void setEnergiaElectrica(Aplica energiaElectrica) {
        this.energiaElectrica = energiaElectrica;
    }

    public Aplica getParedesDanadas() {
        return paredesDanadas;
    }

    public void setParedesDanadas(Aplica paredesDanadas) {
        this.paredesDanadas = paredesDanadas;
    }

    public Aplica getServicioAseo() {
        return servicioAseo;
    }

    public void setServicioAseo(Aplica servicioAseo) {
        this.servicioAseo = servicioAseo;
    }

    public Aplica getTechoDanado() {
        return techoDanado;
    }

    public void setTechoDanado(Aplica techoDanado) {
        this.techoDanado = techoDanado;
    }

    public Aplica getRegistrada() {
        return registrada;
    }

    public void setRegistrada(Aplica registrada) {
        this.registrada = registrada;
    }

    public TipoLetrina getCodigoTipoLetrina() {
        return codigoTipoLetrina;
    }

    public void setCodigoTipoLetrina(TipoLetrina codigoTipoLetrina) {
        this.codigoTipoLetrina = codigoTipoLetrina;
    }

    public MaterialDeVivienda getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(MaterialDeVivienda codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public Techo getCodigoTecho() {
        return codigoTecho;
    }

    public void setCodigoTecho(Techo codigoTecho) {
        this.codigoTecho = codigoTecho;
    }

    public Aplica getAguaPotable() {
        return aguaPotable;
    }

    public void setAguaPotable(Aplica aguaPotable) {
        this.aguaPotable = aguaPotable;
    }

    public Aplica getPredioMunicipal() {
        return predioMunicipal;
    }

    public void setPredioMunicipal(Aplica predioMunicipal) {
        this.predioMunicipal = predioMunicipal;
    }

    public Aplica getConstruidaPorChilden() {
        return construidaPorChilden;
    }

    public void setConstruidaPorChilden(Aplica construidaPorChilden) {
        this.construidaPorChilden = construidaPorChilden;
    }

    public Aplica getPuertasDanadas() {
        return puertasDanadas;
    }

    public void setPuertasDanadas(Aplica puertasDanadas) {
        this.puertasDanadas = puertasDanadas;
    }

    public TenenciaDeVivienda getCodigoTenenciaDeVivienda() {
        return codigoTenenciaDeVivienda;
    }

    public void setCodigoTenenciaDeVivienda(TenenciaDeVivienda codigoTenenciaDeVivienda) {
        this.codigoTenenciaDeVivienda = codigoTenenciaDeVivienda;
    }

    public Aplica getVentanasDanadas() {
        return ventanasDanadas;
    }

    public void setVentanasDanadas(Aplica ventanasDanadas) {
        this.ventanasDanadas = ventanasDanadas;
    }

    public Aplica getPoseeCuartos() {
        return poseeCuartos;
    }

    public void setPoseeCuartos(Aplica poseeCuartos) {
        this.poseeCuartos = poseeCuartos;
    }

    public Entrevistado getCodigoEntrevistado() {
        return codigoEntrevistado;
    }

    public void setCodigoEntrevistado(Entrevistado codigoEntrevistado) {
        this.codigoEntrevistado = codigoEntrevistado;
    }

    public Aplica getPoseeEscrituras() {
        return poseeEscrituras;
    }

    public void setPoseeEscrituras(Aplica poseeEscrituras) {
        this.poseeEscrituras = poseeEscrituras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vivienda)) {
            return false;
        }
        Vivienda other = (Vivienda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.Vivienda[ id=" + id + " ]";
    }
    
}
