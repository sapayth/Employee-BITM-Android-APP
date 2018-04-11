package com.sapayth.employeemad33;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile App Develop on 4/4/2018.
 */

public class Employee implements Serializable {
    private String empName;
    private String empAge;
    private String empPhone;
    private String gender;
    private String employeeType;
    private String dateOfBirth;
    private String officeStartTime;
    private String officeEndTime;
    private List<String> skills;

    private static ArrayList<Employee> employeeList = new ArrayList<>();

    public Employee(String empName, String empAge, String empPhone, String gender, String employeeType, List<String> skills) {
        this.empName = empName;
        this.empAge = empAge;
        this.empPhone = empPhone;
        this.gender = gender;
        this.employeeType = employeeType;
        this.skills = skills;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOfficeStartTime() {
        return officeStartTime;
    }

    public void setOfficeStartTime(String officeStartTime) {
        this.officeStartTime = officeStartTime;
    }

    public String getOfficeEndTime() {
        return officeEndTime;
    }

    public void setOfficeEndTime(String officeEndTime) {
        this.officeEndTime = officeEndTime;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpAge() {
        return empAge;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public String getGender() {
        return gender;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public static void setEmployeeList(ArrayList<Employee> employeeList) {
        Employee.employeeList = employeeList;
    }

    public static ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public static void addEmployeeToList(Employee anEmployee) {
        Employee.employeeList.add(anEmployee);
    }
}
