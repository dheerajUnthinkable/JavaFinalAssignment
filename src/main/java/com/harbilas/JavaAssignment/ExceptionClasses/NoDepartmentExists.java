package com.harbilas.JavaAssignment.ExceptionClasses;

public class NoDepartmentExists extends RuntimeException{
    private String deptId;
    public NoDepartmentExists(String deptId){
        this.deptId = deptId;
    }
    public NoDepartmentExists(){

    }

    @Override
    public String getMessage() {
        return "There doesn't exists any Department with Dept Id: "+deptId;
    }
}
