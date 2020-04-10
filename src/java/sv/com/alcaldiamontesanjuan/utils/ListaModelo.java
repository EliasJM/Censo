package sv.com.alcaldiamontesanjuan.utils;

import javax.persistence.Column;

public class ListaModelo {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad")
    private String cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
