/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author VictorFM
 */

@Entity
@Table(name="categoriacomponente")
public class CategoriaComponente implements Serializable{
    //id autogenerado si es autonumerico, mirar el IDENTITY QUE PUEDE CAMBIAR
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCategoriaComponente;

    @Column(name="nombre")
    private String nombreComponente;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idCategoriaComponente;
        hash = 89 * hash + Objects.hashCode(this.nombreComponente);
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
        final CategoriaComponente other = (CategoriaComponente) obj;
        if (this.idCategoriaComponente != other.idCategoriaComponente) {
            return false;
        }
        if (!Objects.equals(this.nombreComponente, other.nombreComponente)) {
            return false;
        }
        return true;
    }


    //Con lo de arriba se mapea la tablas
    //attr privados, getters y setters, boton derecho insert code, getters y setters y equals and hash code

    public int getIdCategoriaComponente() {
        return idCategoriaComponente;
    }

    public void setIdCategoriaComponente(int idCategoriaComponente) {
        this.idCategoriaComponente = idCategoriaComponente;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

}
