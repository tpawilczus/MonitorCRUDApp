package pl.edu.tpawilczus.jee.MonitorCRUDApp.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producer {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @Min(1900)
    @Max(2022)
    private int yof;

    private List<Monitor> monitors;

    public Producer(String name, String country, int yof) {
        this.name = name;
        this.country = country;
        this.yof = yof;
    }

    public Producer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYof() {
        return yof;
    }

    public void setYof(int yof) {
        this.yof = yof;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    public void addMonitor(Monitor monitor) {
        monitors.add(monitor);
    }

    public void removeMonitor(Monitor monitor) {
        monitors.remove(monitor);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", yof=" + yof +
                ", monitors=" + monitors +
                '}';
    }
}
