package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.TechoFacade;
import sv.com.alcaldiamontesanjuan.entities.Techo;

@ManagedBean
@SessionScoped
public class TechoManagedBeans {
   
    @EJB
    private TechoFacade techoFacade;  
    
    private Techo techo = new Techo();
    
    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo=0;        
         do{
            codigo=(int)(Math.random()*(99-1+1)+1);
            getTecho().setCodigo(String.valueOf(codigo));
        }while(techoFacade.find(String.valueOf(codigo))!=null);     
         
        if (techoFacade.find(getTecho().getCodigo()) == null) {
            techoFacade.create(techo);                    
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Techo se guardo con exito", null));            
            setTecho(new Techo());
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de Techo no se puede guardar. Modifique el codigo " + getTecho().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<Techo> getListaTipoTecho() {
        List<Techo> lista = techoFacade.findAll();
        return lista;
    }

    

    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Techo tmp = techoFacade.find(getTecho().getCodigo());
            if (tmp.getViviendaCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Techo no se puede eliminar, hay información referente a este tipo de techo.", null));
                setTecho(new Techo());
            } else {
                techoFacade.remove(getTecho());
                setTecho(new Techo());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Techo eliminada con exito", null));
            }

        } catch (NullPointerException e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (techoFacade.find(getTecho().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/>.", null));
            setTecho(new Techo());
        } else {
            techoFacade.edit(getTecho());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Techo modificado con Exito", null));
            setTecho(new Techo());            
        }
        return "";
    }

    public Techo getTecho() {
        return techo;
    }

    public void setTecho(Techo tipoLetrina) {
        this.techo = tipoLetrina;
    }

    



}
