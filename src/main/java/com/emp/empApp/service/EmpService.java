package com.emp.empApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.empApp.models.EmpModel;
import com.emp.empApp.repo.EmpRepo;



@Service
public class EmpService {
	
	@Autowired
	private EmpRepo emprepo;
	
	public List<EmpModel> getAll(){
		return emprepo.findAll();
	}
	
	public Optional<EmpModel> getOneById(int empid) {
		return emprepo.findById(empid);
	}
	
	public List<EmpModel> getByName(String empname){
		return emprepo.findAllByEmpname(empname);
	}
	
	public EmpModel createEmp(EmpModel emp) {
		return emprepo.save(emp);
	}
	
	public EmpModel updateEmp(EmpModel emp) {
		return emprepo.save(emp);
	}
	
	public boolean deleteEmp(int empid) {
		emprepo.deleteById(empid);
		if(!emprepo.existsById(empid)) {
			return true;
		}else {
			return false;
		}
	}

}

