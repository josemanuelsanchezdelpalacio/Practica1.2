package javaBeans;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "empresa")
@XmlType(propOrder = {"empleados", "departamentos"})
public class Empresa {

    private List<Empleado> empleados;
    private List<Departamento> departamentos;

    public Empresa() {
        empleados = new ArrayList<>();
        departamentos = new ArrayList<>();
    }

    @XmlElementWrapper(name = "empleados")
    @XmlElement(name = "empleado")
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @XmlElementWrapper(name = "departamentos")
    @XmlElement(name = "departamento")
    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                " empleados=" + empleados +
                ", departamentos=" + departamentos +
                '}';
    }
}
