package com.momenton.model;

import java.util.ArrayList;
import java.util.List;

public class ProcessedEmployee extends Employee {
    private List<ProcessedEmployee> teamMembers;
    private int level;
    private boolean invalid;


    public ProcessedEmployee(Integer id, Integer managerId, String name) {
        super(id, managerId, name);
        teamMembers = new ArrayList<ProcessedEmployee>();
        invalid = false;
    }

    public ProcessedEmployee(Employee employee) {
        this(employee, 0);
    }

    public ProcessedEmployee(Employee employee, int level) {
        this(employee.getId(), employee.getManagerId(), employee.getName());
        this.level = level;
    }

    public List<ProcessedEmployee> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<ProcessedEmployee> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }
}
