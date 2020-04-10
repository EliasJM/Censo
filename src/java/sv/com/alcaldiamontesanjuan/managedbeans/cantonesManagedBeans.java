package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.CantonFacade;
import sv.com.alcaldiamontesanjuan.entities.Canton;

@ManagedBean
@SessionScoped
public class cantonesManagedBeans {

    @EJB
    private CantonFacade cantonFacade;

    private Canton canton = new Canton();

    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (cantonFacade.find(getCanton().getCodigo()) == null) {
            cantonFacade.create(getCanton());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cantón guardo con exito", null));
            canton = new Canton();
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cantón no se puede guardar. Modifique el codigo " + getCanton().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<Canton> getListaCantones() {
        List<Canton> lista = cantonFacade.findAll();
        return lista;
    }

    public Canton getCanton() {
        return canton;
    }

    public String eliminar() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Canton tmp = cantonFacade.find(canton.getCodigo());
            if (tmp.getCaserioCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cantón no se pudo eliminar, primero borre los caseríos.", null));
                canton = new Canton();
            } else {
                cantonFacade.remove(canton);
                canton = new Canton();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cantón eliminado con exito", null));
            }

        } catch (NullPointerException e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (cantonFacade.find(canton.getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/> Eliminelo y ingrese un nuevo cantón con ese codigo.", null));
            canton = new Canton();
        } else {
            cantonFacade.edit(canton);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cantón modificado con Exito", null));
            canton = new Canton();
        }

        return "";
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

}
