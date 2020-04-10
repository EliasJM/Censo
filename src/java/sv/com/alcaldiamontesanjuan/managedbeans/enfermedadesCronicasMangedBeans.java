package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.EnfermedadesCronicasFacade;
import sv.com.alcaldiamontesanjuan.entities.EnfermedadesCronicas;


@ManagedBean
@SessionScoped
public class enfermedadesCronicasMangedBeans {
    
    @EJB
    private EnfermedadesCronicasFacade enfermedadesCronicasFacade;    
    private EnfermedadesCronicas enfermedades = new EnfermedadesCronicas();    

    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        //(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
        int codigo=0;        
         do{
            codigo=(int)(Math.random()*(100-1+1)+1);
            getEnfermedades().setCodigo(String.valueOf(codigo));
        }while(enfermedadesCronicasFacade.find(String.valueOf(codigo))!=null);     
        if (enfermedadesCronicasFacade.find(getEnfermedades().getCodigo())==null) {
            enfermedadesCronicasFacade.create(getEnfermedades());                            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Enfermedad Crónica se guardo con exito", null));
            setEnfermedades(new EnfermedadesCronicas());
            
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enfermedad Crónica no se puede guardar. Modifique el codigo " +getEnfermedades().getCodigo()+" antes de guardar", null));
        }
        return "";
    }

    public List<EnfermedadesCronicas> getListar() {
        List<EnfermedadesCronicas> lista = enfermedadesCronicasFacade.findAll();
        return lista;
    }


    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        EnfermedadesCronicas tmp = enfermedadesCronicasFacade.find(getEnfermedades().getCodigo());        
        try{
            if (tmp.getFamiliasConEnfermedadesCronicasCollection().size() > 0 || tmp.getIntegrantesEnfermosCollection().size()>0 ) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enfermedad Crónica no se pudo eliminar, existen familias con esta enfermedad", null));
        } else {
               enfermedadesCronicasFacade.remove(getEnfermedades());            
            if (enfermedadesCronicasFacade.find(getEnfermedades().getCodigo()) == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Enfermedad Crónica eliminada con exito", null));
                    setEnfermedades(new EnfermedadesCronicas());                
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enfermedad Crónica no se pudo eliminar", null));
                    setEnfermedades(new EnfermedadesCronicas());
            }
        }
            
        }catch(NullPointerException e){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR!! no se ha podido eliminar esta Enfermedad Crónica", null));
        }
        
        return "";
    }
    public String modificar(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (enfermedadesCronicasFacade.find(getEnfermedades().getCodigo())==null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo de las Enfermedad Crónica.<br/> Eliminelo y ingrese una nueva Enfermedad Crónica con este codigo "+getEnfermedades().getCodigo()+".", null));            
            setEnfermedades(new EnfermedadesCronicas());            
        }else{
            enfermedadesCronicasFacade.edit(getEnfermedades());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Enfermedad Crónica modificada con Exito", null));
            setEnfermedades(new EnfermedadesCronicas());            
        }
        return "";
    }

    public EnfermedadesCronicas getEnfermedades() {
        return enfermedades;
    }
    public void setEnfermedades(EnfermedadesCronicas enfermedades) {
        this.enfermedades = enfermedades;
    }
}

