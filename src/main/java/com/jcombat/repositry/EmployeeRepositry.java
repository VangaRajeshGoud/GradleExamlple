package com.jcombat.repositry;

import com.jcombat.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositry extends JpaRepository<EmployeeDetails,String> {
}
