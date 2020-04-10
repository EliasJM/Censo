/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "entrevistado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrevistado.findAll", query = "SELECT e FROM Entrevistado e")
    , @NamedQuery(name = "Entrevistado.findById", query = "SELECT e FROM Entrevistado e WHERE e.id = :id")
    , @NamedQuery(name = "Entrevistado.findByCodigoEntrevista", query = "SELECT e FROM Entrevistado e WHERE e.codigoEntrevista = :codigoEntrevista")
    , @NamedQuery(name = "Entrevistado.findByNombre", query = "SELECT e FROM Entrevistado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Entrevistado.findByFecha", query = "SELECT e FROM Entrevistado e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Entrevistado.findByDui", query = "SELECT e FROM Entrevistado e WHERE e.dui = :dui")
    , @NamedQuery(name = "Entrevistado.findByNit", query = "SELECT e FROM Entrevistado e WHERE e.nit = :nit")
    , @NamedQuery(name = "Entrevistado.findByTelefono", query = "SELECT e FROM Entrevistado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Entrevistado.findByDireccion", query = "SELECT e FROM Entrevistado e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Entrevistado.findByDetallesInformacionEducativa", query = "SELECT e FROM Entrevistado e WHERE e.detallesInformacionEducativa = :detallesInformacionEducativa")
    , @NamedQuery(name = "Entrevistado.findByDetalleDiscapacidadFisica", query = "SELECT e FROM Entrevistado e WHERE e.detalleDiscapacidadFisica = :detalleDiscapacidadFisica")
    , @NamedQuery(name = "Entrevistado.findByCantidadCultivan", query = "SELECT e FROM Entrevistado e WHERE e.cantidadCultivan = :cantidadCultivan")
    , @NamedQuery(name = "Entrevistado.findByNumeroIntegrantes", query = "SELECT e FROM Entrevistado e WHERE e.numeroIntegrantes = :numeroIntegrantes")
    , @NamedQuery(name = "Entrevistado.findByMenoresDeEdad", query = "SELECT e FROM Entrevistado e WHERE e.menoresDeEdad = :menoresDeEdad")
    , @NamedQuery(name = "Entrevistado.findByMayoresDeEdad", query = "SELECT e FROM Entrevistado e WHERE e.mayoresDeEdad = :mayoresDeEdad")
    , @NamedQuery(name = "Entrevistado.findByPermisos", query = "SELECT e FROM Entrevistado e WHERE e.permisos = :permisos")
    , @NamedQuery(name = "Entrevistado.findByFechaRegistro", query = "SELECT e FROM Entrevistado e WHERE e.fechaRegistro = :fechaRegistro")})
public class Entrevistado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigoEntrevista")
    private String codigoEntrevista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 110)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 10)
    @Column(name = "dui")
    private String dui;
    @Size(max = 17)
    @Column(name = "nit")
    private String nit;
    @Size(max = 8)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 150)
    @Column(name = "detallesInformacionEducativa")
    private String detallesInformacionEducativa;
    @Size(max = 150)
    @Column(name = "detalleDiscapacidadFisica")
    private String detalleDiscapacidadFisica;
    @Size(max = 50)
    @Column(name = "cantidadCultivan")
    private String cantidadCultivan;
    @Column(name = "numeroIntegrantes")
    private Integer numeroIntegrantes;
    @Column(name = "menoresDeEdad")
    private Integer menoresDeEdad;
    @Column(name = "mayoresDeEdad")
    private Integer mayoresDeEdad;
    @Lob
    @Size(max = 65535)
    @Column(name = "Observaciones")
    private String observaciones;
    @Size(max = 5)
    @Column(name = "permisos")
    private String permisos;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEntrevistado")
    private Collection<Vivienda> viviendaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEntrevistado")
    private Collection<SeCultivanProductosAgricolas> seCultivanProductosAgricolasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEntrevistado")
    private Collection<BeneficioPoblacion> beneficioPoblacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEntrevistado")
    private Collection<Integrantes> integrantesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEntrevistado")
    private Collection<FamiliasConEnfermedadesCronicas> familiasConEnfermedadesCronicasCollection;
    @JoinColumn(name = "codigoCaserio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Caserio codigoCaserio;
    @JoinColumn(name = "codigoCanton", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Canton codigoCanton;
    @JoinColumn(name = "fuenteDeIngreso", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private FuentesDeIngreso fuenteDeIngreso;
    @JoinColumn(name = "codigoEstadoFamiliar", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoFamiliar codigoEstadoFamiliar;
    @JoinColumn(name = "codigoNivelesEducacion", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private NivelesDeEducacion codigoNivelesEducacion;
    @JoinColumn(name = "codigoProfesionOficio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ProfesionOficio codigoProfesionOficio;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario codigoUsuario;
    @JoinColumn(name = "recibioAyudaAsusNecesidades", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica recibioAyudaAsusNecesidades;
    @JoinColumn(name = "poseeDiscapacidadFisica", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica poseeDiscapacidadFisica;
    @JoinColumn(name = "recibeInsumoAgricolas", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aplica recibeInsumoAgricolas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEntrevistado")
    private Collection<NecesidadesMunicipio> necesidadesMunicipioCollection;

    public Entrevistado() {
    }

    public Entrevistado(String id) {
        this.id = id;
    }

    public Entrevistado(String id, String codigoEntrevista, String nombre, Date fecha) {
        this.id = id;
        this.codigoEntrevista = codigoEntrevista;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoEntrevista() {
        return codigoEntrevista;
    }

    public void setCodigoEntrevista(String codigoEntrevista) {
        this.codigoEntrevista = codigoEntrevista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDetallesInformacionEducativa() {
        return detallesInformacionEducativa;
    }

    public void setDetallesInformacionEducativa(String detallesInformacionEducativa) {
        this.detallesInformacionEducativa = detallesInformacionEducativa;
    }

    public String getDetalleDiscapacidadFisica() {
        return detalleDiscapacidadFisica;
    }

    public void setDetalleDiscapacidadFisica(String detalleDiscapacidadFisica) {
        this.detalleDiscapacidadFisica = detalleDiscapacidadFisica;
    }

    public String getCantidadCultivan() {
        return cantidadCultivan;
    }

    public void setCantidadCultivan(String cantidadCultivan) {
        this.cantidadCultivan = cantidadCultivan;
    }

    public Integer getNumeroIntegrantes() {
        return numeroIntegrantes;
    }

    public void setNumeroIntegrantes(Integer numeroIntegrantes) {
        this.numeroIntegrantes = numeroIntegrantes;
    }

    public Integer getMenoresDeEdad() {
        return menoresDeEdad;
    }

    public void setMenoresDeEdad(Integer menoresDeEdad) {
        this.menoresDeEdad = menoresDeEdad;
    }

    public Integer getMayoresDeEdad() {
        return mayoresDeEdad;
    }

    public void setMayoresDeEdad(Integer mayoresDeEdad) {
        this.mayoresDeEdad = mayoresDeEdad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    public Collection<Vivienda> getViviendaCollection() {
        return viviendaCollection;
    }

    public void setViviendaCollection(Collection<Vivienda> viviendaCollection) {
        this.viviendaCollection = viviendaCollection;
    }

    @XmlTransient
    public Collection<SeCultivanProductosAgricolas> getSeCultivanProductosAgricolasCollection() {
        return seCultivanProductosAgricolasCollection;
    }

    public void setSeCultivanProductosAgricolasCollection(Collection<SeCultivanProductosAgricolas> seCultivanProductosAgricolasCollection) {
        this.seCultivanProductosAgricolasCollection = seCultivanProductosAgricolasCollection;
    }

    @XmlTransient
    public Collection<BeneficioPoblacion> getBeneficioPoblacionCollection() {
        return beneficioPoblacionCollection;
    }

    public void setBeneficioPoblacionCollection(Collection<BeneficioPoblacion> beneficioPoblacionCollection) {
        this.beneficioPoblacionCollection = beneficioPoblacionCollection;
    }

    @XmlTransient
    public Collection<Integrantes> getIntegrantesCollection() {
        return integrantesCollection;
    }

    public void setIntegrantesCollection(Collection<Integrantes> integrantesCollection) {
        this.integrantesCollection = integrantesCollection;
    }

    @XmlTransient
    public Collection<FamiliasConEnfermedadesCronicas> getFamiliasConEnfermedadesCronicasCollection() {
        return familiasConEnfermedadesCronicasCollection;
    }

    public void setFamiliasConEnfermedadesCronicasCollection(Collection<FamiliasConEnfermedadesCronicas> familiasConEnfermedadesCronicasCollection) {
        this.familiasConEnfermedadesCronicasCollection = familiasConEnfermedadesCronicasCollection;
    }

    public Caserio getCodigoCaserio() {
        return codigoCaserio;
    }

    public void setCodigoCaserio(Caserio codigoCaserio) {
        this.codigoCaserio = codigoCaserio;
    }

    public Canton getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(Canton codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public FuentesDeIngreso getFuenteDeIngreso() {
        return fuenteDeIngreso;
    }

    public void setFuenteDeIngreso(FuentesDeIngreso fuenteDeIngreso) {
        this.fuenteDeIngreso = fuenteDeIngreso;
    }

    public EstadoFamiliar getCodigoEstadoFamiliar() {
        return codigoEstadoFamiliar;
    }

    public void setCodigoEstadoFamiliar(EstadoFamiliar codigoEstadoFamiliar) {
        this.codigoEstadoFamiliar = codigoEstadoFamiliar;
    }

    public NivelesDeEducacion getCodigoNivelesEducacion() {
        return codigoNivelesEducacion;
    }

    public void setCodigoNivelesEducacion(NivelesDeEducacion codigoNivelesEducacion) {
        this.codigoNivelesEducacion = codigoNivelesEducacion;
    }

    public ProfesionOficio getCodigoProfesionOficio() {
        return codigoProfesionOficio;
    }

    public void setCodigoProfesionOficio(ProfesionOficio codigoProfesionOficio) {
        this.codigoProfesionOficio = codigoProfesionOficio;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Aplica getRecibioAyudaAsusNecesidades() {
        return recibioAyudaAsusNecesidades;
    }

    public void setRecibioAyudaAsusNecesidades(Aplica recibioAyudaAsusNecesidades) {
        this.recibioAyudaAsusNecesidades = recibioAyudaAsusNecesidades;
    }

    public Aplica getPoseeDiscapacidadFisica() {
        return poseeDiscapacidadFisica;
    }

    public void setPoseeDiscapacidadFisica(Aplica poseeDiscapacidadFisica) {
        this.poseeDiscapacidadFisica = poseeDiscapacidadFisica;
    }

    public Aplica getRecibeInsumoAgricolas() {
        return recibeInsumoAgricolas;
    }

    public void setRecibeInsumoAgricolas(Aplica recibeInsumoAgricolas) {
        this.recibeInsumoAgricolas = recibeInsumoAgricolas;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrevistado)) {
            return false;
        }
        Entrevistado other = (Entrevistado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.alcaldiamontesanjuan.entities.Entrevistado[ id=" + id + " ]";
    }
    
}
