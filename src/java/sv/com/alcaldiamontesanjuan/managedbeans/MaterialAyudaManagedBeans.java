package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.MateriaDeAyudaFacade;
import sv.com.alcaldiamontesanjuan.entities.MateriaDeAyuda;

@ManagedBean
@SessionScoped
public class MaterialAyudaManagedBeans {

    @EJB
    private MateriaDeAyudaFacade materialDeAyudaFacade;

    private MateriaDeAyuda materialDeAyuda = new MateriaDeAyuda();

    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo=0;        
         do{
            codigo=(int)(Math.random()*(900-1+1)+1);
            getMaterialDeAyuda().setCodigo(String.valueOf(codigo));
        }while(materialDeAyudaFacade.find(String.valueOf(codigo))!=null);     
         
        if (materialDeAyudaFacade.find(getMaterialDeAyuda().getCodigo()) == null) {
            materialDeAyudaFacade.create(getMaterialDeAyuda());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material de ayuda se guardo con exito", null));
            setMaterialDeAyuda(new MateriaDeAyuda());
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Material de ayuda no se puede guardar. Modifique el codigo " + getMaterialDeAyuda().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<MateriaDeAyuda> getListaMaterialDeAyuda() {
        List<MateriaDeAyuda> lista = materialDeAyudaFacade.findAll();
        return lista;
    }

    

    public String eliminar() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            MateriaDeAyuda tmp = materialDeAyudaFacade.find(getMaterialDeAyuda().getCodigo());
            if (tmp.getBeneficioPoblacionCollection().size() > 0 || tmp.getNecesidadesMunicipioCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Material de ayuda no se pudo eliminar, este esta relacionado a información importante.", null));
                setMaterialDeAyuda(new MateriaDeAyuda());                
            } else {
                materialDeAyudaFacade.remove(getMaterialDeAyuda());
                setMaterialDeAyuda(new MateriaDeAyuda());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material de ayuda eliminado con exito", null));
            }

        } catch (NullPointerException e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (materialDeAyudaFacade.find(getMaterialDeAyuda().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/> Eliminelo y ingrese un nuevo Material de ayuda con ese codigo.", null));
            setMaterialDeAyuda(new MateriaDeAyuda());
        } else {
            materialDeAyudaFacade.edit(getMaterialDeAyuda());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material de ayuda modificado con Exito", null));
            setMaterialDeAyuda(new MateriaDeAyuda());
        }
        return "";
    }

    public MateriaDeAyuda getMaterialDeAyuda() {
        return materialDeAyuda;
    }

    public void setMaterialDeAyuda(MateriaDeAyuda materialDeAyuda) {
        this.materialDeAyuda = materialDeAyuda;
    }
}
