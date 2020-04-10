package sv.com.alcaldiamontesanjuan.managedbeans;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sv.com.alcaldiamontesanjuan.entities.TipoUsuario;
import sv.com.alcaldiamontesanjuan.entities.Usuario;
import sv.com.alcaldiamontesanjuan.facades.UsuarioFacade;
import sv.com.alcaldiamontesanjuan.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class UsuarioManagedBeans {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario = new Usuario();
    private Usuario usuarioOperador = new Usuario();

    public UsuarioManagedBeans() {
        usuario = new Usuario();
        usuario.setCodigoTipoUsuario(new TipoUsuario());
    }

    public String insert() throws NoSuchAlgorithmException {
        FacesContext context = FacesContext.getCurrentInstance();
        int codigo = 0;
        do {
            codigo = (int) (Math.random() * (99999 - 1 + 1) + 1);
            getUsuario().setCodigo(String.valueOf(codigo));
        } while (usuarioFacade.find(String.valueOf(codigo)) != null);
        String tmp = setearHash(getUsuario().getContrasena());
        if (tmp.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no se puede guardar. Intente nuevamente.", null));
        } else {
            if (usuarioFacade.find(getUsuario().getCodigo()) == null) {
                getUsuario().setContrasena(tmp);
                usuarioFacade.create(getUsuario());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario se guardo con exito", null));
                reiniciarValores();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no se puede guardar. Intente nuevamente.", null));
            }
        }
        return "";
    }

    public List<Usuario> getListaUsuarios() {
        List<Usuario> lista = usuarioFacade.findAll();
        return lista;
    }

    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Usuario tmp = usuarioFacade.find(getUsuario().getCodigo());
            if (tmp.getEntrevistadoCollection().size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este usuario no se puede borrar, este ha registrado datos de entrevistado", null));
                reiniciarValores();
            } else {
                usuarioFacade.remove(getUsuario());
                reiniciarValores();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado con exito", null));
            }

        } catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String setearHash(String plainText) throws NoSuchAlgorithmException {

        String s = plainText;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    public String iniciarSesion() throws NoSuchAlgorithmException, IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (getUsuario().getEmail().isEmpty() || getUsuario().getContrasena().isEmpty()) {

        } else {
            String hash = "";
            hash = setearHash(getUsuario().getContrasena());
            getUsuario().setContrasena(hash);
            Usuario tmp = usuarioFacade.consultarDatosIniciarSesion(getUsuario().getContrasena(), getUsuario().getEmail());            
            if (tmp == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique los datos de inicio de sesión", null));
                //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                return "";
            } else {
                if (tmp.getEmail().equals(getUsuario().getEmail()) && tmp.getContrasena().equals(getUsuario().getContrasena())) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido " + tmp.getNombre(), null));
                    setUsuarioOperador(tmp);
                    //aqui se debe agregar la parte para que genere una sesión y poder controlarla
                    HttpSession session = SessionUtils.getSession();
                    session.setAttribute("codigoUsuario", tmp.getCodigo());
                    if (tmp.getCodigoTipoUsuario().getCodigo().equals("1")||tmp.getCodigoTipoUsuario().getCodigo().equals("3")) {
                        session.setAttribute("codigoTipoUsuario", tmp.getCodigoTipoUsuario().getCodigo());
                    }else{
                        session.setAttribute("codigoTipoUsuario", null);
                    }                    
                    session.setAttribute("autenticado", true);
                    if ("1".equals(tmp.getCodigoTipoUsuario().getCodigo())) {
                        //Alcalde secretario                                                
                        FacesContext.getCurrentInstance().getExternalContext().redirect("administrador/panelAlcalde.xhtml");
                    } else {
                        if ("2".equals(tmp.getCodigoTipoUsuario().getCodigo())) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("administrador/personaEntrevista.xhtml");
                        } else {
                            if ("3".equals(tmp.getCodigoTipoUsuario().getCodigo())) {
                                FacesContext.getCurrentInstance().getExternalContext().redirect("administrador/personaEntrevista.xhtml");
                            }
                        }
                    }

                }
            }
        }

        return "";

    }

    public String loguearse() {
        return "../";
    }

    public String modificar() throws NoSuchAlgorithmException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuarioFacade.find(getUsuario().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede modificar este usuario.", null));
            reiniciarValores();
        } else {
            String tmp = setearHash(getUsuario().getContrasena());
            if (tmp.isEmpty()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede modificar la contraseña.", null));
            } else {
                getUsuario().setContrasena(tmp);
                usuarioFacade.edit(getUsuario());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario modificado con Exito", null));
                reiniciarValores();
            }

        }
        return "";
    }

    public void reiniciarValores() {
        usuario = new Usuario();
        usuario.setCodigoTipoUsuario(new TipoUsuario());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioOperador() {
        return usuarioOperador;
    }

    public void setUsuarioOperador(Usuario usuarioOperador) {
        this.usuarioOperador = usuarioOperador;
    }
}
