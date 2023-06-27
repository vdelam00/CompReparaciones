/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author VictorFM
 */
@Entity
@Table(name="reparacion")
public class Reparacion implements Serializable{
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Column(name="fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    @ManyToOne
    @JoinColumn(name = "idOrdenador")
    private Ordenador ordenador;
    
    /*@ManyToOne
    @JoinColumn(name = "idEstado")
    private Estado estado;*/
    
    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;
    
    /*@ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Ordenador getOrdenador() {
        return ordenador;
    }

    public void setOrdenador(Ordenador ordenador) {
        this.ordenador = ordenador;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /*public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.descripcion);
        hash = 89 * hash + Objects.hashCode(this.fechaInicio);
        hash = 89 * hash + Objects.hashCode(this.fechaFin);
        hash = 89 * hash + Objects.hashCode(this.ordenador);
        hash = 89 * hash + Objects.hashCode(this.empleado);
        //hash = 89 * hash + Objects.hashCode(this.factura);
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
        final Reparacion other = (Reparacion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        if (!Objects.equals(this.ordenador, other.ordenador)) {
            return false;
        }
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        /*if (!Objects.equals(this.factura, other.factura)) {
            return false;
        }*/
        return true;
    }

    

    
}
