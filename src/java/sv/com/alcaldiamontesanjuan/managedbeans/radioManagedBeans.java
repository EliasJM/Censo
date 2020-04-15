package sv.com.alcaldiamontesanjuan.managedbeans;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sv.com.alcaldiamontesanjuan.entities.Aplica;
import sv.com.alcaldiamontesanjuan.entities.EstadoFamiliar;
import sv.com.alcaldiamontesanjuan.entities.Integrantes;
import sv.com.alcaldiamontesanjuan.entities.NivelesDeEducacion;
import sv.com.alcaldiamontesanjuan.entities.Parentesco;
import sv.com.alcaldiamontesanjuan.entities.Sexo;
import sv.com.alcaldiamontesanjuan.entities.TipoUsuario;
import sv.com.alcaldiamontesanjuan.facades.AplicaFacade;
import sv.com.alcaldiamontesanjuan.facades.EstadoFamiliarFacade;
import sv.com.alcaldiamontesanjuan.facades.IntegrantesFacade;
import sv.com.alcaldiamontesanjuan.facades.NivelesDeEducacionFacade;
import sv.com.alcaldiamontesanjuan.facades.ParentescoFacade;
import sv.com.alcaldiamontesanjuan.facades.SexoFacade;
import sv.com.alcaldiamontesanjuan.facades.TipoUsuarioFacade;
@ManagedBean
@SessionScoped
public class radioManagedBeans {

    @EJB
    private AplicaFacade aplicaFacade;
     @EJB
    private NivelesDeEducacionFacade nivelesFacade;
      @EJB
    private EstadoFamiliarFacade estadofamiliarFacade;
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private SexoFacade sexoFacade;
    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;
    @EJB
    private IntegrantesFacade integranteFacade;
    //atributos para las busquedas personalizadas
    private int edadMinima;
    private int edadMaxima;
    private String codigoCanton;
    private String codigoCaserio;
    private String codigoGenero;
    
    private Aplica aplica = new Aplica();
    private NivelesDeEducacion niveles = new NivelesDeEducacion();
    private EstadoFamiliar estadoFamiliar = new EstadoFamiliar();
    List<Object[]> listaPersonalizada=new ArrayList<Object[]>();
    public List<Aplica> getListaAplica(){
        List<Aplica> lista = aplicaFacade.findAll();
        return lista;
    }
    public List<EstadoFamiliar> getListaEstadoFamiliar(){
        List<EstadoFamiliar> lista = estadofamiliarFacade.findAll();
        return lista;
    }
    public List<NivelesDeEducacion> getListaNivelesEducacion(){
        List<NivelesDeEducacion> lista = nivelesFacade.findAll();
        return lista;
    }
    public List<Parentesco> getListaParentesco(){
        List<Parentesco> lista = parentescoFacade.findAll();
        return lista;
    }
    public List<Sexo> getListaSexo(){
        List<Sexo> lista = sexoFacade.findAll();
        return lista;
    }
    public List<TipoUsuario> getListaTipoUsuario(){
        List<TipoUsuario> lista= tipoUsuarioFacade.findAll();
        return lista;
    }
    public List<Object[]> getListaPersonalizada(){
        if (listaPersonalizada.isEmpty()) {
         listaPersonalizada=integranteFacade.getListarBusquedaPersonalizadaFindAll();
        }
        return listaPersonalizada;
    }
    public String busquedaPersonalizadaIntegrantes(){
        listaPersonalizada=integranteFacade.getListarBusquedaPersonalizada(codigoCanton, edadMinima, edadMaxima);
        return "";
    }
    
    /**
     * @return the edadMinima
     */
    public int getEdadMinima() {
        return edadMinima;
    }

    /**
     * @param edadMinima the edadMinima to set
     */
    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    /**
     * @return the edadMaxima
     */
    public int getEdadMaxima() {
        return edadMaxima;
    }

    /**
     * @param edadMaxima the edadMaxima to set
     */
    public void setEdadMaxima(int edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    /**
     * @return the codigoCanton
     */
    public String getCodigoCanton() {
        return codigoCanton;
    }

    /**
     * @param codigoCanton the codigoCanton to set
     */
    public void setCodigoCanton(String codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    /**
     * @return the codigoCaserio
     */
    public String getCodigoCaserio() {
        return codigoCaserio;
    }

    /**
     * @param codigoCaserio the codigoCaserio to set
     */
    public void setCodigoCaserio(String codigoCaserio) {
        this.codigoCaserio = codigoCaserio;
    }

    /**
     * @return the codigoGenero
     */
    public String getCodigoGenero() {
        return codigoGenero;
    }

    /**
     * @param codigoGenero the codigoGenero to set
     */
    public void setCodigoGenero(String codigoGenero) {
        this.codigoGenero = codigoGenero;
    }
    
}
