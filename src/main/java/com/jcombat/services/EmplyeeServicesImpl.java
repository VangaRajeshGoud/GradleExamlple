package com.jcombat.services;

import com.jcombat.entity.EmployeeDetails;
import com.jcombat.repositry.EmployeeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmplyeeServicesImpl {

    @Autowired
    EmployeeRepositry employeeRepositry;

    public EmployeeDetails save(EmployeeDetails employeeDetails){
        return employeeRepositry.save(employeeDetails);
    }


    public void SaveAllEmployees(List<EmployeeDetails> emplyeeServices){
        for (EmployeeDetails empObj: emplyeeServices ) {
            employeeRepositry.save(empObj);

        }

    }

    public EmployeeDetails getEmployeeByID(String eid) {
        return  employeeRepositry.findOne(eid);
    }

    public void DeleteEmployeeByID(String eid) {
          employeeRepositry.delete(eid);
    }
}
