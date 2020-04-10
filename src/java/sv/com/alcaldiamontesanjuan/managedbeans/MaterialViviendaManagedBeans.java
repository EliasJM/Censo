package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.MaterialDeViviendaFacade;
import sv.com.alcaldiamontesanjuan.entities.MaterialDeVivienda;

@ManagedBean
@SessionScoped
public class MaterialViviendaManagedBeans {
    @EJB
    private MaterialDeViviendaFacade materialDeVivienda;
    private MaterialDeVivienda materialVivienda = new MaterialDeVivienda();

    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo = 0;
        do {
            codigo = (int) (Math.random() * (900 - 1 + 1) + 1);
            getMaterialVivienda().setCodigo(String.valueOf(codigo));            
        } while (materialDeVivienda.find(String.valueOf(codigo)) != null);

        if (materialDeVivienda.find(getMaterialVivienda().getCodigo()) == null) {
            materialDeVivienda.create(getMaterialVivienda());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material de vivienda se guardo con exito", null));
            setMaterialVivienda(new MaterialDeVivienda());            
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Material de vivienda no se puede guardar. Modifique el codigo " + getMaterialVivienda().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<MaterialDeVivienda> getListaMaterialDeVivienda() {
        List<MaterialDeVivienda> lista = materialDeVivienda.findAll();
        return lista;
    }

    public String eliminar() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            MaterialDeVivienda tmp = materialDeVivienda.find(getMaterialVivienda().getCodigo());
            if (tmp.getViviendaCollection().size() > 0 ) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Material de vivienda no se pudo eliminar, este esta relacionado a información importante.", null));
                setMaterialVivienda(new MaterialDeVivienda());
                
            } else {
                materialDeVivienda.remove(getMaterialVivienda());
                setMaterialVivienda(new MaterialDeVivienda());                                          
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material de vivienda eliminado con exito", null));
            }

        } catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (materialDeVivienda.find(getMaterialVivienda().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/> Eliminelo y ingrese un nuevo Material de vivienda con ese codigo.", null));
            setMaterialVivienda(new MaterialDeVivienda());            
        } else {
            materialDeVivienda.edit(getMaterialVivienda());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material de vivienda modificado con Exito", null));
            setMaterialVivienda(new MaterialDeVivienda());            
        }
        return "";
    }
    public MaterialDeVivienda getMaterialVivienda() {
        return materialVivienda;
    }

    public void setMaterialVivienda(MaterialDeVivienda materialVivienda) {
        this.materialVivienda = materialVivienda;
    }
}
