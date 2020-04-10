package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.TenenciaDeViviendaFacade;
import sv.com.alcaldiamontesanjuan.entities.TenenciaDeVivienda;

@ManagedBean
@SessionScoped
public class TenenciaViviendaManagedBeans {
   
    @EJB
    private TenenciaDeViviendaFacade tenenciaFacade;
    private TenenciaDeVivienda tenencia = new TenenciaDeVivienda();
    
    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo=0;        
         do{
            codigo=(int)(Math.random()*(99-1+1)+1);
            getTenencia().setCodigo(String.valueOf(codigo));
        }while(tenenciaFacade.find(String.valueOf(codigo))!=null);     
         
        if (tenenciaFacade.find(getTenencia().getCodigo()) == null) {
            tenenciaFacade.create(tenencia);                    
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tenencia de Vivienda se guardo con exito", null));            
            setTenencia(new TenenciaDeVivienda());
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tenencia de Vivienda no se puede guardar. Modifique el codigo " + getTenencia().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<TenenciaDeVivienda> getListaTenenciaVivienda() {
        List<TenenciaDeVivienda> lista = tenenciaFacade.findAll();
        return lista;
    }
    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            TenenciaDeVivienda tmp = tenenciaFacade.find(getTenencia().getCodigo());
            if (tmp.getViviendaCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tenencia de Vivienda no se pudo eliminar, hay personas información importante aun.", null));
                setTenencia(new TenenciaDeVivienda());
            } else {
                tenenciaFacade.remove(getTenencia());
                setTenencia(new TenenciaDeVivienda());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tenencia de Vivienda eliminada con exito", null));
            }

        } catch (NullPointerException e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (tenenciaFacade.find(getTenencia().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/>", null));
            setTenencia(new TenenciaDeVivienda());
        } else {
            tenenciaFacade.edit(getTenencia());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tenencia de Vivienda modificado con Exito", null));
            setTenencia(new TenenciaDeVivienda());            
        }
        return "";
    }

    public TenenciaDeVivienda getTenencia() {
        return tenencia;
    }

    public void setTenencia(TenenciaDeVivienda tenencia) {
        this.tenencia = tenencia;
    }
}
