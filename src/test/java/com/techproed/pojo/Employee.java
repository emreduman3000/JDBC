package com.techproed.pojo;

import java.util.List;
import java.util.Map;



public class Employee {

    private int empID;
    private String empName;
    private int empAge;
    static int i=0;

    public Employee()
    {

    }
    public Employee(int empID, String empName, int empAge) {
        this.empID = empID;
        this.empName = empName;
        this.empAge = empAge;
    }

    public Employee(Map<String, String> map, List<String> list) {
        this.empID =Integer.parseInt(map.get(list.get(i++)));
        this.empName = map.get(list.get(i++));
        this.empAge = Integer.parseInt(map.get(list.get(i)));
        i=0;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpAge() {
        return empAge;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", empName='" + empName + '\'' +
                ", empAge=" + empAge +
                '}';
    }


}
