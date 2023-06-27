 /*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.OrdenadorFacadeLocal;
import EJB.ReparacionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Ordenador;
import modelo.Reparacion;

/**
 *
 * @author VictorFM
 */

@Named("ModificarEmpleadoController")
@ViewScoped
public class ModificarEmpleadoController implements Serializable {
    private Empleado empleado;
    private List<Reparacion> reparacion;
    private Reparacion reparacionAgregar;
    //lista para visualizar todos los ordenadores
    private List<Ordenador> ordenadores;
    private Ordenador ordenadoR;

    @EJB
    private ReparacionFacadeLocal reparacionEJB;
    
    @EJB
    private OrdenadorFacadeLocal ordenadorEJB;
    
    @PostConstruct
    public void init() {
        // Obtener el empleado autenticado de la sesión
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        empleado = (Empleado) externalContext.getSessionMap().get("empleado");
        
        reparacion = empleado.getReparaciones();
        
        reparacionAgregar=new Reparacion();
        ordenadores = ordenadorEJB.findAll();
        ordenadoR=new Ordenador();
    }
    

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Reparacion> getReparaciones() {
        reparacion = reparacionEJB.findByEmpleado(empleado);
        return reparacion;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparacion = reparacion;
    }

    public List<Reparacion> getReparacion() {
        return reparacion;
    }

    public void setReparacion(List<Reparacion> reparacion) {
        this.reparacion = reparacion;
    }

    public ReparacionFacadeLocal getReparacionEJB() {
        return reparacionEJB;
    }

    public void setReparacionEJB(ReparacionFacadeLocal reparacionEJB) {
        this.reparacionEJB = reparacionEJB;
    }

    public Reparacion getReparacionAgregar() {
        return reparacionAgregar;
    }

    public void setReparacionAgregar(Reparacion reparacionAgregar) {
        this.reparacionAgregar = reparacionAgregar;
    }

    public List<Ordenador> getOrdenadores() {
        return ordenadores;
    }

    public void setOrdenadores(List<Ordenador> ordenadores) {
        this.ordenadores = ordenadores;
    }

    public OrdenadorFacadeLocal getOrdenadorEJB() {
        return ordenadorEJB;
    }

    public void setOrdenadorEJB(OrdenadorFacadeLocal ordenadorEJB) {
        this.ordenadorEJB = ordenadorEJB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.empleado);
        hash = 83 * hash + Objects.hashCode(this.reparacion);
        hash = 83 * hash + Objects.hashCode(this.reparacionAgregar);
        hash = 83 * hash + Objects.hashCode(this.ordenadores);
        hash = 83 * hash + Objects.hashCode(this.reparacionEJB);
        hash = 83 * hash + Objects.hashCode(this.ordenadorEJB);
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
        final ModificarEmpleadoController other = (ModificarEmpleadoController) obj;
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        if (!Objects.equals(this.reparacion, other.reparacion)) {
            return false;
        }
        if (!Objects.equals(this.reparacionAgregar, other.reparacionAgregar)) {
            return false;
        }
        if (!Objects.equals(this.ordenadores, other.ordenadores)) {
            return false;
        }
        if (!Objects.equals(this.reparacionEJB, other.reparacionEJB)) {
            return false;
        }
        if (!Objects.equals(this.ordenadorEJB, other.ordenadorEJB)) {
            return false;
        }
        return true;
    }
    
    

    

    
    
    
    
    
    public void eliminarReparacion(Reparacion rep) {
        try {
            //Borra mientras no este linkeado con ninguna reparacion
            reparacionEJB.remove(rep);
            reparacion.remove(rep);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reparacion eliminada", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar la reparacion", null));
        }
    }
    
    public void agregarReparacion() {
        Reparacion nuevaReparacion = new Reparacion();
        nuevaReparacion.setDescripcion(reparacionAgregar.getDescripcion());
        nuevaReparacion.setFechaInicio(reparacionAgregar.getFechaInicio());
        nuevaReparacion.setFechaFin(reparacionAgregar.getFechaFin());
        nuevaReparacion.setOrdenador(reparacionAgregar.getOrdenador());
        nuevaReparacion.setEmpleado(empleado);

        // Guardar la nueva reparacion en la base de datos utilizando el EJB ReparacionFacade
        reparacionEJB.create(nuevaReparacion);

        // Actualizar la lista de reparaciones desde la base de datos
        reparacion = reparacionEJB.findByEmpleado(empleado);

        // Limpiar los datos del formulario o crear un nuevo objeto Repracion
        reparacionAgregar = new Reparacion();
    }
    
}
