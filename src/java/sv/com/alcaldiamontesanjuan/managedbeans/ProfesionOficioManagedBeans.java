package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.ProfesionOficioFacade;
import sv.com.alcaldiamontesanjuan.entities.ProfesionOficio;

@ManagedBean
@SessionScoped
public class ProfesionOficioManagedBeans {

    @EJB
    private ProfesionOficioFacade profesionOficioFacade;
    private ProfesionOficio profesionOficio = new ProfesionOficio();
    
    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo=0;        
         do{
            codigo=(int)(Math.random()*(99-1+1)+1);
            getProfesionOficio().setCodigo(String.valueOf(codigo));
        }while(profesionOficioFacade.find(String.valueOf(codigo))!=null);     
         
        if (profesionOficioFacade.find(getProfesionOficio().getCodigo()) == null) {
            profesionOficioFacade.create(getProfesionOficio());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesión o Oficio guardo con exito", null));
            setProfesionOficio(new ProfesionOficio());
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profesión o Oficio no se puede guardar. Modifique el codigo " + getProfesionOficio().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<ProfesionOficio> getListaProfesionOficio() {
        List<ProfesionOficio> lista = profesionOficioFacade.findAll();
        return lista;
    }

    

    public String eliminar() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            ProfesionOficio tmp = profesionOficioFacade.find(getProfesionOficio().getCodigo());
            if (tmp.getEntrevistadoCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profesión o Oficio no se pudo eliminar, hay personas que muestran su información con estas profesión o oficio.", null));
                setProfesionOficio(new ProfesionOficio());
            } else {
                profesionOficioFacade.remove(getProfesionOficio());
                setProfesionOficio(new ProfesionOficio());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesión o Oficio eliminada con exito", null));
            }

        } catch (NullPointerException e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (profesionOficioFacade.find(getProfesionOficio().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/> Eliminelo y ingrese una nueva Profesión o Oficio con ese codigo.", null));
            setProfesionOficio(new ProfesionOficio());
        } else {
            profesionOficioFacade.edit(getProfesionOficio());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesión o Oficio modificado con Exito", null));
            setProfesionOficio(new ProfesionOficio());            
        }

        return "";
    }

    public ProfesionOficio getProfesionOficio() {
        return profesionOficio;
    }

    public void setProfesionOficio(ProfesionOficio profesionOficio) {
        this.profesionOficio = profesionOficio;
    }


}
