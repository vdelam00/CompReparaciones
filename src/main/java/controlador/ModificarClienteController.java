/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.OrdenadorFacadeLocal;
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

/**
 *
 * @author VictorFM
 */

@Named("ModificarClienteController")
@ViewScoped
public class ModificarClienteController implements Serializable {
    private Cliente cliente;
    private List<Ordenador> ordenadores;

    @EJB
    private OrdenadorFacadeLocal ordenadorEJB;
    
    @PostConstruct
    public void init() {
        // Obtener el cliente autenticado de la sesión
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        cliente = (Cliente) externalContext.getSessionMap().get("cliente");
        
        ordenadores = cliente.getOrdenadores();

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.cliente);
        hash = 37 * hash + Objects.hashCode(this.ordenadores);
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
        return true;
    }
    
    
    
    public List<Ordenador> getOrdenadores(){
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
}