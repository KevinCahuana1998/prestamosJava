/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author USER
 */
public class Prestamo {
    String codigo;
    String prestamo;
    String fecha;
    String mora;
    String fechaTotal;

    public Prestamo(String codigo, String prestamo, String fecha, String fechaTotal, String mora) {
        this.codigo = codigo;
        this.prestamo = prestamo;
        this.fecha = fecha;
        this.mora = mora;
        this.fechaTotal = fechaTotal;
    }

    public Prestamo() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMora() {
        return mora;
    }

    public void setMora(String mora) {
        this.mora = mora;
    }

    public String getFechaTotal() {
        return fechaTotal;
    }

    public void setFechaTotal(String fechaTotal) {
        this.fechaTotal = fechaTotal;
    }

    
    
           
}
