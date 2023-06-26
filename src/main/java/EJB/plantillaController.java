/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Administrador;
import modelo.Empleado;
import modelo.Cliente;

/**
 *
 * @author VictorFM
 */
@Named("plantillaController")
@ViewScoped
public class plantillaController implements Serializable{
    private Cliente cliente;
    private Empleado empleado;
    private Administrador administrador;
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    
    @EJB
    private EmpleadoFacadeLocal empleadoEJB;
    
    @EJB
    private AdministradorFacadeLocal administradorEJB;
    
    @PostConstruct
    public void init(){
        cliente = new Cliente();
        empleado = new Empleado();
        administrador = new Administrador();
    }
    
    public String verificarAdministrador(){
           Administrador admin;
           String direccion = null;
           try{
               admin=administradorEJB.iniciarSesion(administrador);
               if(admin != null){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrador");
               direccion="privado/administrador/modificarUsuario.xhtml?faces-redirect=true";
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", admin);
               } else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Usuario Incorrecto"));
               }
           }catch(Exception e){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
           }
           return direccion;
    }
}
