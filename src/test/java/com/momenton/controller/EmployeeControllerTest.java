package com.momenton.controller;


import com.momenton.model.Employee;
import com.momenton.model.ProcessedEmployee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeControllerTest extends EmployeeController {

    @Test
    public void processEmployee() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        list.add(new Employee(100, 150, "Alan"));
        list.add(new Employee(220, 100, "Martin"));
        list.add(new Employee(150, null, "Jamie"));
        list.add(new Employee(275, 100, "Alex"));
        list.add(new Employee(400, 150, "Steve"));
        list.add(new Employee(190, 400, "David"));
        list.add(new Employee(500, 151, "Tara"));
        list.add(new Employee(null, 500, "Marko"));

        List<ProcessedEmployee> result = EmployeeController.processEmployee(list);
        assertEquals("Found CEO + 2 Invalid Employee", 3, result.size());
        assertEquals("Found CEO Jamie", "Jamie", result.get(0).getName());
        assertEquals("Found Invalid Employee Tara", "Tara", result.get(1).getName());
        assertEquals("Found Invalid Employee Marko", "Marko", result.get(2).getName());

        assertEquals("Found 2 manager under CEO", 2, result.get(0).getTeamMembers().size());
        HashMap<String, ProcessedEmployee> managerMap = new HashMap<String, ProcessedEmployee>();
        for (ProcessedEmployee manager : result.get(0).getTeamMembers()) {
            managerMap.put(manager.getName(), manager);
        }
        assertTrue("1 manager is Alan", managerMap.get("Alan") != null);
        assertTrue("1 manager is Steve", managerMap.get("Steve") != null);


        assertEquals("Alan team has 2 member", 2, managerMap.get("Alan").getTeamMembers().size());
        HashMap<String, ProcessedEmployee> memberMap = new HashMap<String, ProcessedEmployee>();
        for (ProcessedEmployee teamMember : managerMap.get("Alan").getTeamMembers()) {
            memberMap.put(teamMember.getName(), teamMember);
        }
        assertTrue("1 Alan team member is Matin", memberMap.get("Martin") != null);
        assertTrue("1 Alan team member is Alex", memberMap.get("Alex") != null);

        assertEquals("Steve team has 1 member", 1, managerMap.get("Steve").getTeamMembers().size());
        assertEquals("Steve team's member is David", "David", managerMap.get("Steve").getTeamMembers().get(0).getName());
    }

    @Test
    public void printEmployeeList() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        list.add(new Employee(100, 150, "Alan"));
        list.add(new Employee(220, 100, "Martin"));
        list.add(new Employee(150, null, "Jamie"));
        list.add(new Employee(275, 100, "Alex"));
        list.add(new Employee(400, 150, "Steve"));
        list.add(new Employee(190, 400, "David"));
        Employee tara = new Employee(500, 151, "Tara");
        Employee marko = new Employee(null, 500, "Marko");

        list.add(tara);
        list.add(marko);

        String result = EmployeeController.printEmployeeList(list);

        StringBuilder sb = new StringBuilder();
        sb.append("Jamie").append("/n");
        sb.append("/t").append("Alan").append("/n");
        sb.append("/t/t").append("Martin").append("/n");
        sb.append("/t/t").append("Alex").append("/n");
        sb.append("/t").append("Steve").append("/n");
        sb.append("/t/t").append(tara.toString()).append("/n");
        sb.append("Invalid Employee ").append(marko.toString()).append("/n");
    }
}