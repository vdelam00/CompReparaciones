/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.EmpleadoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Administrador;
import modelo.Empleado;

/**
 *
 * @author VictorFM
 */

@Named("EmpleadoController")
@ViewScoped
public class EmpleadoController implements Serializable{
    
    private Administrador administrador;
    private Empleado empleado;
    private List<Empleado> listaDeEmpleados;
    private Empleado empleadoModificacion;
    
    @EJB
    private EmpleadoFacadeLocal empleadoEJB;
    
    @PostConstruct
    public void init(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        administrador = (Administrador) externalContext.getSessionMap().get("administrador");
        empleado = new Empleado();
        empleadoModificacion = null;
        listaDeEmpleados = empleadoEJB.findAll();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoFacadeLocal getEmpleadoEJB() {
        return empleadoEJB;
    }

    public void setEmpleadoEJB(EmpleadoFacadeLocal empleadoEJB) {
        this.empleadoEJB = empleadoEJB;
    }

    public List<Empleado> getListaDeEmpleados() {
        return listaDeEmpleados;
    }

    public void setListaDeEmpleados(List<Empleado> listaDeEmpleados) {
        this.listaDeEmpleados = listaDeEmpleados;
    }

    public Empleado getEmpleadoModificacion() {
        return empleadoModificacion;
    }

    public void setEmpleadoModificacion(Empleado empleadoModificacion) {
        this.empleadoModificacion = empleadoModificacion;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.administrador);
        hash = 47 * hash + Objects.hashCode(this.empleado);
        hash = 47 * hash + Objects.hashCode(this.listaDeEmpleados);
        hash = 47 * hash + Objects.hashCode(this.empleadoModificacion);
        hash = 47 * hash + Objects.hashCode(this.empleadoEJB);
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
        final EmpleadoController other = (EmpleadoController) obj;
        if (!Objects.equals(this.administrador, other.administrador)) {
            return false;
        }
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        if (!Objects.equals(this.listaDeEmpleados, other.listaDeEmpleados)) {
            return false;
        }
        if (!Objects.equals(this.empleadoModificacion, other.empleadoModificacion)) {
            return false;
        }
        if (!Objects.equals(this.empleadoEJB, other.empleadoEJB)) {
            return false;
        }
        return true;
    }

    
    
    public void eliminarEmpleado(Empleado empleado) {
        try {
            empleadoEJB.remove(empleado);
            listaDeEmpleados.remove(empleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado eliminado", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el empleado", null));
        }
    }
    
    public void agregarEmpleado() {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setUser(empleado.getUser());
        nuevoEmpleado.setPassword(empleado.getPassword());
        nuevoEmpleado.setNombre(empleado.getNombre());
        nuevoEmpleado.setApellidos(empleado.getApellidos());
        nuevoEmpleado.setEstado("0");

        // Guardar el nuevo empleado en la base de datos utilizando el EJB EmpleadoFacade
        empleadoEJB.create(nuevoEmpleado);

        // Actualizar la lista de empleados desde la base de datos
        listaDeEmpleados = empleadoEJB.findAll();

        // Limpiar los datos del formulario o crear un nuevo objeto Empleado
        empleado = new Empleado();
    }
    
    public void seleccionarEmpleado(Empleado empleado) {
        empleadoModificacion = empleado;
    }
    
    public void modificarEmpleado() {
        try {
            empleadoModificacion = empleado;
            empleadoEJB.edit(empleadoModificacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado modificado", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar el empleado", null));
        }
    }
    
}
