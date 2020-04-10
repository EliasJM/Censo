package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.TipoLetrinaFacade;
import sv.com.alcaldiamontesanjuan.entities.TipoLetrina;

@ManagedBean
@SessionScoped
public class TipoLetrinaManagedBeans {
   
    @EJB
    private TipoLetrinaFacade tipoLetrinaFacade;
    
    
    private TipoLetrina tipoLetrina = new TipoLetrina();
    
    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo=0;        
         do{
            codigo=(int)(Math.random()*(9-1+1)+1);
            getTipoLetrina().setCodigo(String.valueOf(codigo));
        }while(tipoLetrinaFacade.find(String.valueOf(codigo))!=null);     
         
        if (tipoLetrinaFacade.find(getTipoLetrina().getCodigo()) == null) {
            tipoLetrinaFacade.create(tipoLetrina);                    
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Letrina se guardo con exito", null));            
            setTipoLetrina(new TipoLetrina());
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de Letrina no se puede guardar. Modifique el codigo " + getTipoLetrina().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<TipoLetrina> getListaTipoLetrina() {
        List<TipoLetrina> lista = tipoLetrinaFacade.findAll();
        return lista;
    }

    

    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            TipoLetrina tmp = tipoLetrinaFacade.find(getTipoLetrina().getCodigo());
            if (tmp.getViviendaCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de Letrina no se pudo eliminar, hay personas que muestran su información con estas profesión o oficio.", null));
                setTipoLetrina(new TipoLetrina());
            } else {
                tipoLetrinaFacade.remove(getTipoLetrina());
                setTipoLetrina(new TipoLetrina());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Letrina eliminada con exito", null));
            }

        } catch (NullPointerException e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (tipoLetrinaFacade.find(getTipoLetrina().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/> Eliminelo y ingrese un nuevo Tipo de Letrina con ese codigo.", null));
            setTipoLetrina(new TipoLetrina());
        } else {
            tipoLetrinaFacade.edit(getTipoLetrina());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Letrina modificado con Exito", null));
            setTipoLetrina(new TipoLetrina());            
        }
        return "";
    }

    public TipoLetrina getTipoLetrina() {
        return tipoLetrina;
    }

    public void setTipoLetrina(TipoLetrina tipoLetrina) {
        this.tipoLetrina = tipoLetrina;
    }

    



}
