package com.momenton.controller;

import com.momenton.model.Employee;
import com.momenton.model.ProcessedEmployee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeController {

    /**
     * Process the specified employee list, and print it in hierarchical (TAB) format.
     *
     * @param employeeList
     * @return
     */
    public static String printEmployeeList(List<Employee> employeeList) {
        List<ProcessedEmployee> list = processEmployee(employeeList);
        return printProcessedEmployeeList(list);
    }

    protected static List<ProcessedEmployee> processEmployee(List<Employee> employeeList) {
        ArrayList<ProcessedEmployee> results = new ArrayList<ProcessedEmployee>();
        HashMap<Integer, ProcessedEmployee> map = new HashMap<Integer, ProcessedEmployee>();
        List<Employee> found = null;

        while (found == null || found.size() > 0) {
            found = new ArrayList<Employee>();

            for (Employee employee : employeeList) {
                ProcessedEmployee processedEmployee = new ProcessedEmployee(employee);
                // Invalid record, skip
                if (employee.getId() == null) {
                    continue;
                }

                if (employee.getManagerId() == null) {
                    map.put(processedEmployee.getId(), processedEmployee);
                    results.add(processedEmployee);
                    found.add(employee);
                    continue;
                }
                ProcessedEmployee manager = map.get(employee.getManagerId());
                // Invalid record, skip
                if (manager == null) {
                    continue;
                }
                processedEmployee.setLevel(manager.getLevel() + 1);
                manager.getTeamMembers().add(processedEmployee);
                map.put(processedEmployee.getId(), processedEmployee);
                found.add(employee);
            }

            if (found.size() > 0) {
                employeeList.removeAll(found);
            }
        }
        for (Employee employee : employeeList) {
            ProcessedEmployee processedEmployee = new ProcessedEmployee(employee);
            processedEmployee.setInvalid(true);
            results.add(processedEmployee);
        }

        return results;
    }

    protected static String printProcessedEmployeeList(List<ProcessedEmployee> employeeList) {
        if (employeeList == null || employeeList.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (ProcessedEmployee employee : employeeList) {
            if (employee.isInvalid()) {
                sb.append("Invalid Employee").append(employee.toString()).append("/n");
            }
            for (int i = 0; i < employee.getLevel(); i++) {
                sb.append("/t");
            }
            if (employee.isInvalid()) {
                sb.append("*");
            }
            sb.append(employee.getName());
            sb.append("/n");
            sb.append(printProcessedEmployeeList(employee.getTeamMembers()));
        }
        return sb.toString();
    }
}
