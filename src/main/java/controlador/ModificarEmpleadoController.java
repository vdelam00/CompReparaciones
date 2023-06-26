 /*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        // Obtener el empleado autenticado de la sesión
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        empleado = (Empleado) externalContext.getSessionMap().get("empleado");
        
        reparacion = empleado.getReparaciones();

    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Reparacion> getReparaciones() {
        return reparacion;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparacion = reparacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.empleado);
        hash = 61 * hash + Objects.hashCode(this.reparacion);
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
        return true;
    }
    
}
