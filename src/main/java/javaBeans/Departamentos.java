package javaBeans;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "departamentos")
public class Departamentos {

    private List<Departamento> departamentos;

    public Departamentos() {
        departamentos = new ArrayList<>();
    }

    public Departamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    @XmlElement(name = "departamento")
    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}