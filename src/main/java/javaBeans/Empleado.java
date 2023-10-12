package javaBeans;

import java.util.Date;

public class Empleado {

    private String nombre;
    private Double sueldo;
    private Date añoNacimiento, antiguedad;
    private int idDepartamento;

    public Empleado(String nombre, Double sueldo, Date añoNacimiento, Date antiguedad) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.añoNacimiento = añoNacimiento;
        this.antiguedad = antiguedad;
    }

    public Empleado(String nombre, Double sueldo, Date añoNacimiento, Date antiguedad, int idDepartamento) {
        this(nombre, sueldo, añoNacimiento, antiguedad);
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Date getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(Date añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public Date getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Date antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                ", añoNacimiento=" + añoNacimiento +
                ", antiguedad=" + antiguedad +
                ", idDepartamento=" + idDepartamento +
                '}';
    }
}
