package com.emp.empApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emp.empApp.models.EmpModel;

@Repository
public interface EmpRepo extends JpaRepository<EmpModel, Integer> {

	@Query(value = "select e from EmpModel e where e.empname like %?1%")
	List<EmpModel> findAllByEmpname(String empname);
}
