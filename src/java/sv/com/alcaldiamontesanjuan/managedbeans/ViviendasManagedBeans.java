package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sv.com.alcaldiamontesanjuan.entities.Aplica;
import sv.com.alcaldiamontesanjuan.entities.Entrevistado;
import sv.com.alcaldiamontesanjuan.entities.FuentesDeIngreso;
import sv.com.alcaldiamontesanjuan.entities.MaterialDeVivienda;
import sv.com.alcaldiamontesanjuan.entities.Techo;
import sv.com.alcaldiamontesanjuan.entities.TenenciaDeVivienda;
import sv.com.alcaldiamontesanjuan.entities.TipoLetrina;
import sv.com.alcaldiamontesanjuan.entities.Vivienda;
import sv.com.alcaldiamontesanjuan.facades.ViviendaFacade;

@ManagedBean
@SessionScoped
public class ViviendasManagedBeans {

    @EJB
    private ViviendaFacade viviendaFacade;

    private Vivienda vivienda = new Vivienda();

    public ViviendasManagedBeans() {
        vivienda = new Vivienda();
        vivienda.setCodigoEntrevistado(new Entrevistado());
        vivienda.setPoseeCuartos(new Aplica());
        vivienda.setPoseeEscrituras(new Aplica());
        vivienda.setPredioMunicipal(new Aplica());
        vivienda.setRegistrada(new Aplica());
        vivienda.setConstruidaPorChilden(new Aplica());
        vivienda.setTechoDanado(new Aplica());
        vivienda.setVentanasDanadas(new Aplica());
        vivienda.setPuertasDanadas(new Aplica());
        vivienda.setAguaPotable(new Aplica());
        vivienda.setEnergiaElectrica(new Aplica());
        vivienda.setServicioAseo(new Aplica());
        vivienda.setParedesDanadas(new Aplica());
        vivienda.setCodigoTipoLetrina(new TipoLetrina());
        vivienda.setCodigoTenenciaDeVivienda(new TenenciaDeVivienda());
        vivienda.setCodigoTecho(new Techo());
        vivienda.setCodigoMaterial(new MaterialDeVivienda());
    }
    public void reiniciarValores() {
        vivienda = new Vivienda();
        vivienda.setCodigoEntrevistado(new Entrevistado());
        vivienda.setPoseeCuartos(new Aplica());
        vivienda.setPoseeEscrituras(new Aplica());
        vivienda.setPredioMunicipal(new Aplica());
        vivienda.setRegistrada(new Aplica());
        vivienda.setConstruidaPorChilden(new Aplica());
        vivienda.setTechoDanado(new Aplica());
        vivienda.setVentanasDanadas(new Aplica());
        vivienda.setPuertasDanadas(new Aplica());
        vivienda.setAguaPotable(new Aplica());
        vivienda.setEnergiaElectrica(new Aplica());
        vivienda.setServicioAseo(new Aplica());
        vivienda.setParedesDanadas(new Aplica());
        vivienda.setCodigoTipoLetrina(new TipoLetrina());
        vivienda.setCodigoTenenciaDeVivienda(new TenenciaDeVivienda());
        vivienda.setCodigoTecho(new Techo());
        vivienda.setCodigoMaterial(new MaterialDeVivienda());
    }

    public List<Vivienda> getListaViviendas() {
        List<Vivienda> lista = viviendaFacade.findAll();
        return lista;
    }

    public String insert() {
        int codigo = 0;
        FacesContext context = FacesContext.getCurrentInstance();
        if (getVivienda().getCodigoEntrevistado().getId() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!!! Intenta ingresando desde la tabla de control de personas    Entrevistados", null));
        } else {
            do {
                codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                getVivienda().setId(String.valueOf(codigo));
            } while (viviendaFacade.find(String.valueOf(codigo)) != null);
            if (viviendaFacade.find(getVivienda().getId()) == null) {
                try {
                    if (getVivienda().getCantidadCuartos()==null || getVivienda().getCantidadCuartos()<=0) {
                        getVivienda().setPoseeCuartos(new Aplica("2"));
                    }else{
                        getVivienda().setPoseeCuartos(new Aplica("1"));
                    }
                    viviendaFacade.create(getVivienda());
                    reiniciarValores();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vivienda se  guardo con exito", null));
                } catch (EJBException e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error abortado!! " + e.getMessage(), null));
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vivienda  no se puede guardar.", null));
            }
        }
        return "integrantes";
    }
    

    public String modificar(){
    FacesContext context = FacesContext.getCurrentInstance();
        if (viviendaFacade.find(getVivienda().getId()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar.", null));
            reiniciarValores();
        } else {
            viviendaFacade.edit(getVivienda());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vivienda modificada con Exito", null));
            reiniciarValores();
        }    
        
        
        return "completarNuevamenteDatos";
    }
    public String cargarDatos(){
        FacesContext context = FacesContext.getCurrentInstance();
        Vivienda tmp= viviendaFacade.getListarViviendaPorEntrevistado(getVivienda().getCodigoEntrevistado().getId());
        if (tmp==null) {
            setVivienda(new Vivienda());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!!!! no se han encrontrado registros", null));            
        }else{
            setVivienda(tmp);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifique los datos que desea", null));
        }        
        return "";
    }
    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();        
        try {           
                viviendaFacade.remove(getVivienda());
                if (viviendaFacade.find(getVivienda().getId()) == null) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vivienda eliminada con exito", null));
                    reiniciarValores();
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vivienda no se pudo eliminar", null));
                    reiniciarValores();
                }} catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR!! no se ha podido eliminar esta Vivienda", null));
        }

        return "personaEntrevista";
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

}
