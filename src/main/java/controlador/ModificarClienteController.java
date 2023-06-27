/*
 * To change this license header, choose License Headers in Project Properties.
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
import modelo.Ordenador;
import modelo.Reparacion;

/**
 *
 * @author VictorFM
 */

@Named("ModificarClienteController")
@ViewScoped
public class ModificarClienteController implements Serializable {
    private Cliente cliente;
    private List<Ordenador> ordenadores;
    private Ordenador ordenadorAgregar;
    private List<Reparacion> reparacionesList;

    @EJB
    private OrdenadorFacadeLocal ordenadorEJB;
    
    @EJB
    private ReparacionFacadeLocal reparacionEJB;
    
    @PostConstruct
    public void init() {
        // Obtener el cliente autenticado de la sesión
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        cliente = (Cliente) externalContext.getSessionMap().get("cliente");
        
        ordenadores = cliente.getOrdenadores();
        ordenadorAgregar = new Ordenador();
        reparacionesList = reparacionEJB.findAll();

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Ordenador getOrdenadorAgregar() {
        return ordenadorAgregar;
    }

    public void setOrdenadorAgregar(Ordenador ordenadorAgregar) {
        this.ordenadorAgregar = ordenadorAgregar;
    }

    public OrdenadorFacadeLocal getOrdenadorEJB() {
        return ordenadorEJB;
    }

    public void setOrdenadorEJB(OrdenadorFacadeLocal ordenadorEJB) {
        this.ordenadorEJB = ordenadorEJB;
    }

    public List<Reparacion> getReparacionesList() {
        return reparacionesList;
    }

    public void setReparacionesList(List<Reparacion> reparacionesList) {
        this.reparacionesList = reparacionesList;
    }

    public ReparacionFacadeLocal getReparacionEJB() {
        return reparacionEJB;
    }

    public void setReparacionEJB(ReparacionFacadeLocal reparacionEJB) {
        this.reparacionEJB = reparacionEJB;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.cliente);
        hash = 97 * hash + Objects.hashCode(this.ordenadores);
        hash = 97 * hash + Objects.hashCode(this.ordenadorAgregar);
        hash = 97 * hash + Objects.hashCode(this.reparacionesList);
        hash = 97 * hash + Objects.hashCode(this.ordenadorEJB);
        hash = 97 * hash + Objects.hashCode(this.reparacionEJB);
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
        final ModificarClienteController other = (ModificarClienteController) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.ordenadores, other.ordenadores)) {
            return false;
        }
        if (!Objects.equals(this.ordenadorAgregar, other.ordenadorAgregar)) {
            return false;
        }
        if (!Objects.equals(this.reparacionesList, other.reparacionesList)) {
            return false;
        }
        if (!Objects.equals(this.ordenadorEJB, other.ordenadorEJB)) {
            return false;
        }
        if (!Objects.equals(this.reparacionEJB, other.reparacionEJB)) {
            return false;
        }
        return true;
    }

    
    
    

    
    
    

    
    public List<Reparacion> getReparacionesCliente() {
        return reparacionEJB.findByCliente(cliente);
    }
    
    
    
    public List<Ordenador> getOrdenadores(){
        ordenadores = ordenadorEJB.findByCliente(cliente);
        return ordenadores;
    }
    
    public void eliminarOrdenador(Ordenador ordenador) {
        try {
            //Borra mientras no este linkeado con ninguna reparacion
            ordenadorEJB.remove(ordenador);
            ordenadores.remove(ordenador);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ordenador eliminado", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el ordenador", null));
        }
    }
    
    public void agregarOrdenador() {
        Ordenador nuevoOrdenador = new Ordenador();
        nuevoOrdenador.setNombre(ordenadorAgregar.getNombre());
        nuevoOrdenador.setDescripcion(ordenadorAgregar.getDescripcion());
        nuevoOrdenador.setCliente(cliente);

        // Guardar el nuevo ordenador en la base de datos utilizando el EJB OrdenadorFacade
        ordenadorEJB.create(nuevoOrdenador);

        // Actualizar la lista de ordenadores desde la base de datos
        ordenadores = ordenadorEJB.findByCliente(cliente);

        // Limpiar los datos del formulario o crear un nuevo objeto Ordenador
        ordenadorAgregar = new Ordenador();
    }
}