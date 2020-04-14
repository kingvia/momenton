package com.momenton.model;

public class Employee {
    private Integer id;
    private Integer managerId;
    private String name;

    public Employee(Integer id, Integer managerId, String name) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID=[" + id + "] Name=[" + name + "] ManagerId=[" + managerId + "]";
    }
}
