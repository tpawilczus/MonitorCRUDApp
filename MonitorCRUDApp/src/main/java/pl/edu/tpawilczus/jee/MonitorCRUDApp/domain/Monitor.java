package pl.edu.tpawilczus.jee.MonitorCRUDApp.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Monitor {

    private Long id;

    @NotBlank
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Min(8)
    private int size;

    @NotNull
    @Min(30)
    private int refreshRate;

    private User user;

    public Monitor(String name, int size, int refreshRate) {
        this.name = name;
        this.size = size;
        this.refreshRate = refreshRate;
    }

    public Monitor() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "monitor")
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", refreshRate=" + refreshRate +
                '}';
    }
}
