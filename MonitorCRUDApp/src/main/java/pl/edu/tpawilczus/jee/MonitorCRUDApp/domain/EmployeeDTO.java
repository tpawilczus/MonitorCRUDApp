package pl.edu.tpawilczus.jee.MonitorCRUDApp.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Second Name is mandatory")
    private String lastName;

    @Min(2005)
    @Max(2022)
    private int yoe;

    private List<Monitor> monitors;

    public EmployeeDTO(String firstName, String lastName, int yoe) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yoe = yoe;
    }

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYoe() {
        return yoe;
    }

    public void setYoe(int yoe) {
        this.yoe = yoe;
    }

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
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yoe='" + yoe + '\'' +
                ", monitors=" + monitors +
                '}';
    }
}

