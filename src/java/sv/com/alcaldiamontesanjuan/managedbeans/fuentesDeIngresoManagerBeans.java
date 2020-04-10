package sv.com.alcaldiamontesanjuan.managedbeans;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sv.com.alcaldiamontesanjuan.facades.FuentesDeIngresoFacade;
import sv.com.alcaldiamontesanjuan.entities.FuentesDeIngreso;

@ManagedBean
@SessionScoped
public class fuentesDeIngresoManagerBeans {
    @EJB
    private FuentesDeIngresoFacade fuentesDeIngresoFacade;
    private FuentesDeIngreso fuente = new FuentesDeIngreso();

    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        //(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
        int codigo = 0;
        
        do{
            codigo = (int) (Math.random() * (99 - 1 + 1) + 1);
            getFuente().setCodigo(String.valueOf(codigo));
        }while (fuentesDeIngresoFacade.find(String.valueOf(codigo)) != null);
        if (fuentesDeIngresoFacade.find(getFuente().getCodigo()) == null) {
            fuentesDeIngresoFacade.create(getFuente());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fuente de Ingreso guarda con exito", null));
            fuente = new FuentesDeIngreso();

        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fuente de Ingreso no se puede guardar. Modifique el codigo " + getFuente().getCodigo() + " antes de guardar", null));
        }
        return "";
    }

    public List<FuentesDeIngreso> getListaFuentesdeIngreso() {
        List<FuentesDeIngreso> lista = fuentesDeIngresoFacade.findAll();
        return lista;
    }

    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FuentesDeIngreso tmp = fuentesDeIngresoFacade.find(getFuente().getCodigo());
        try {
            if (tmp.getEntrevistadoCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fuente de Ingreso no se pudo eliminar, existe información importante sobre este.", null));
            } else {
                fuentesDeIngresoFacade.remove(getFuente());
                if (fuentesDeIngresoFacade.find(getFuente().getCodigo()) == null) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fuente de Ingreso eliminada con exito", null));
                    fuente = new FuentesDeIngreso();

                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fuente de Ingreso no se pudo eliminar", null));
                    fuente = new FuentesDeIngreso();
                }
            }

        } catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR!! no se ha podido eliminar esta Fuente de Ingreso", null));
        }

        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (fuentesDeIngresoFacade.find(getFuente().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo de las Fuente de Ingreso.<br/> Eliminela y ingrese una nueva Fuente de Ingreso con este codigo " + getFuente().getCodigo() + ".", null));
            fuente = new FuentesDeIngreso();
        } else {
            fuentesDeIngresoFacade.edit(getFuente());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fuente de Ingreso modificada con Exito", null));
            fuente = new FuentesDeIngreso();
        }
        return "";
    }

    public FuentesDeIngreso getFuente() {
        return fuente;
    }

    public void setFuente(FuentesDeIngreso fuente) {
        this.fuente = fuente;
    }

}
