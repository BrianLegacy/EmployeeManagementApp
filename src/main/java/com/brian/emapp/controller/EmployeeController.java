package com.brian.emapp.controller;

import com.brian.emapp.model.Employee;
import com.brian.emapp.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EmployeeController implements Serializable {

    @Inject
    private EmployeeService employeeService;

    private List<Employee> employees;
    private Employee selectedEmployee;
    private Employee newEmployee;

    @PostConstruct
    public void init() {
        employees = employeeService.getAllEmployees();
        newEmployee = new Employee();
    }

    public void loadEmployees() {
        employees = employeeService.getAllEmployees();
    }

    public void addEmployee() {
        employeeService.addEmployee(newEmployee);
        employees = employeeService.getAllEmployees();
        newEmployee = new Employee();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Employee added successfully!"));
    }

    public void updateEmployee() {
        try {
            employeeService.updateEmployee(selectedEmployee);
            employees = employeeService.getAllEmployees(); // Refresh list
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success", "Employee updated successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Update failed: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteEmployee() {
        employeeService.deleteEmployee(selectedEmployee.getId());
        employees = employeeService.getAllEmployees();
        selectedEmployee = null; // Clear the selection
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Employee deleted successfully!"));
    }

    // Getters and Setters
    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }
}
