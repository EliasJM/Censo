package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.alcaldiamontesanjuan.facades.ProductosAgricolasFacade;
import sv.com.alcaldiamontesanjuan.entities.ProductosAgricolas;

@ManagedBean
@SessionScoped
public class productosAgricolasManagedBeans {

    @EJB
    private ProductosAgricolasFacade productosAgricolasFacade;

    private ProductosAgricolas productosAgricolas = new ProductosAgricolas();

    public String insert() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo=0;        
         do{
            codigo=(int)(Math.random()*(900-1+1)+1);
            getProductosAgricolas().setCodigo(String.valueOf(codigo));
        }while(productosAgricolasFacade.find(String.valueOf(codigo))!=null);     
         
        if (productosAgricolasFacade.find(getProductosAgricolas().getCodigo()) == null) {
            productosAgricolasFacade.create(getProductosAgricolas());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Agrícola guardo con exito", null));
            setProductosAgricolas(new ProductosAgricolas());
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto Agrícola no se puede guardar. Modifique el codigo " + getProductosAgricolas().getCodigo() + " para poder guardar", null));
        }
        return "";
    }

    public List<ProductosAgricolas> getListaProductosAgricolas() {
        List<ProductosAgricolas> lista = productosAgricolasFacade.findAll();
        return lista;
    }

    

    public String eliminar() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            ProductosAgricolas tmp = productosAgricolasFacade.find(getProductosAgricolas().getCodigo());
            if (tmp.getSeCultivanProductosAgricolasCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto Agrícola no se pudo eliminar, hay muchas familias que lo cultivan.", null));
                setProductosAgricolas(new ProductosAgricolas());
            } else {
                productosAgricolasFacade.remove(getProductosAgricolas());
                setProductosAgricolas(new ProductosAgricolas());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Agrícola  eliminado con exito", null));
            }

        } catch (NullPointerException e) {
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (productosAgricolasFacade.find(getProductosAgricolas().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/> Eliminelo y ingrese un nuevo Producto Agrícola con ese codigo.", null));
            setProductosAgricolas(new ProductosAgricolas());
        } else {
            productosAgricolasFacade.edit(getProductosAgricolas());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Agrícola modificado con Exito", null));
            setProductosAgricolas(new ProductosAgricolas());
        }

        return "";
    }

    /**
     * @return the productosAgricolas
     */
    public ProductosAgricolas getProductosAgricolas() {
        return productosAgricolas;
    }

    /**
     * @param productosAgricolas the productosAgricolas to set
     */
    public void setProductosAgricolas(ProductosAgricolas productosAgricolas) {
        this.productosAgricolas = productosAgricolas;
    }

    

}
