/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.AdministradorFacadeLocal;
import EJB.ClienteFacade;
import EJB.ClienteFacadeLocal;
import EJB.EmpleadoFacadeLocal;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Empleado;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author VictorFM
 */
@Named("LoginController")
@ViewScoped
public class LoginController implements Serializable{
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public ClienteFacadeLocal getClienteEJB() {
        return clienteEJB;
    }

    public void setClienteEJB(ClienteFacadeLocal clienteEJB) {
        this.clienteEJB = clienteEJB;
    }

    public EmpleadoFacadeLocal getEmpleadoEJB() {
        return empleadoEJB;
    }

    public void setEmpleadoEJB(EmpleadoFacadeLocal empleadoEJB) {
        this.empleadoEJB = empleadoEJB;
    }

    public AdministradorFacadeLocal getAdministradorEJB() {
        return administradorEJB;
    }

    public void setAdministradorEJB(AdministradorFacadeLocal administradorEJB) {
        this.administradorEJB = administradorEJB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.cliente);
        hash = 67 * hash + Objects.hashCode(this.empleado);
        hash = 67 * hash + Objects.hashCode(this.administrador);
        hash = 67 * hash + Objects.hashCode(this.clienteEJB);
        hash = 67 * hash + Objects.hashCode(this.empleadoEJB);
        hash = 67 * hash + Objects.hashCode(this.administradorEJB);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoginController other = (LoginController) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        if (!Objects.equals(this.administrador, other.administrador)) {
            return false;
        }
        if (!Objects.equals(this.clienteEJB, other.clienteEJB)) {
            return false;
        }
        if (!Objects.equals(this.empleadoEJB, other.empleadoEJB)) {
            return false;
        }
        if (!Objects.equals(this.administradorEJB, other.administradorEJB)) {
            return false;
        }
        return true;
    }
    
    
    public String verificarCliente(){
           Cliente client;
           String direccion = null;
           try{
               client=clienteEJB.iniciarSesion(cliente);
               if(client != null){
                   direccion="privado/cliente/modificarCliente.xhtml?faces-redirect=true";
                   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                   externalContext.getSessionMap().put("cliente", client);
               } else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Usuario Incorrecto"));
               }
           }catch(Exception e){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
           }
           return direccion;
    }
    
    public String verificarEmpleado(){
           Empleado emple;
           String direccion = null;
           try{
               emple=empleadoEJB.iniciarSesion(empleado);
               if(emple != null){
                   direccion="privado/empleado/modificarEmpleado.xhtml?faces-redirect=true";
                   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                   externalContext.getSessionMap().put("empleado", emple);
               } else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Usuario Incorrecto"));
               }
           }catch(Exception e){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
           }
           return direccion;
    }
    
    public String verificarAdministrador(){
           Administrador admin;
           String direccion = null;
           try{
               admin=administradorEJB.iniciarSesion(administrador);
               if(admin != null){
                   direccion="privado/administrador/modificarUsuario.xhtml?faces-redirect=true";
                   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                   externalContext.getSessionMap().put("administrador", admin);
               } else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Usuario Incorrecto"));
               }
           }catch(Exception e){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
           }
           return direccion;
    }
}
