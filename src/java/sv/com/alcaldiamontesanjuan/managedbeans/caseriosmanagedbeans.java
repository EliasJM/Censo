package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.CaserioFacade;
import sv.com.alcaldiamontesanjuan.entities.Caserio;
import sv.com.alcaldiamontesanjuan.entities.Canton;

@ManagedBean
@SessionScoped
public class caseriosmanagedbeans {
    
    @EJB
    private CaserioFacade caserioFacade;
    
    public caseriosmanagedbeans(){
        caserio= new Caserio();
        caserio.setCodigoCanton(new Canton());
    }
    private Caserio caserio = new Caserio();
    

    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        //(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
        int codigo=0;
        codigo=(int)(Math.random()*(3000-1+1)+1);        
        getCaserio().setCodigo(String.valueOf(codigo));
        while(caserioFacade.find(String.valueOf(codigo))!=null) {
            codigo=(int)(Math.random()*(3000-1+1)+1);
            getCaserio().setCodigo(String.valueOf(codigo));
        }       
        if (caserioFacade.find(getCaserio().getCodigo())==null) {
            caserioFacade.create(getCaserio());                            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Caserío guardo con exito", null));
            caserio= new Caserio();
            caserio.setCodigoCanton(new Canton());
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caserío no se puede guardar. Modifique el codigo " +getCaserio().getCodigo()+" antes de guardar", null));
        }
        return "";
    }

    public List<Caserio> getListaCaserio() {
        List<Caserio> lista = caserioFacade.findAll();
        return lista;
    }
    
    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        Caserio tmp = caserioFacade.find(caserio.getCodigo());        
        try{
            if (tmp.getEntrevistadoCollection().size() > 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caserío no se pudo eliminar, existe información importante sobre este.", null));
        } else {            
               caserioFacade.remove(caserio);            
            if (caserioFacade.find(caserio.getCodigo()) == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Caserío eliminado con exito", null));
                caserio= new Caserio();
                caserio.setCodigoCanton(new Canton());
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caserío no se pudo eliminar", null));
                caserio= new Caserio();
                caserio.setCodigoCanton(new Canton());
            }
        }
            
        }catch(NullPointerException e){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR!! no se ha podido eliminar", null));
        }
        
        return "";
    }
    public String modificar(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (caserioFacade.find(caserio.getCodigo())==null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo de los caserios.<br/> Eliminelo y ingrese un nuevo Caserío con este codigo "+caserio.getCodigo()+".", null));            
            caserio= new Caserio();
            caserio.setCodigoCanton(new Canton());
        }else{
            caserioFacade.edit(caserio);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Caserío modificado con Exito", null));
            caserio= new Caserio();
            caserio.setCodigoCanton(new Canton());
        }
        return "";
    }

    /**
     * @return the caserio
     */
    public Caserio getCaserio() {
        return caserio;
    }

    /**
     * @param caserio the caserio to set
     */
    public void setCaserio(Caserio caserio) {
        this.caserio = caserio;
    }

    

}

