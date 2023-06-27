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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author VictorFM
 */
@Entity
@Table(name="factura")
public class Factura implements Serializable{
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name="importe")
    private String importe;
    
    @Column(name="impuesto")
    private String impuesto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.fecha);
        hash = 13 * hash + Objects.hashCode(this.importe);
        hash = 13 * hash + Objects.hashCode(this.impuesto);
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
        final Factura other = (Factura) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.importe, other.importe)) {
            return false;
        }
        if (!Objects.equals(this.impuesto, other.impuesto)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }
    
    
    
}
