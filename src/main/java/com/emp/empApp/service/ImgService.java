package com.emp.empApp.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.empApp.models.EmpModel;
import com.emp.empApp.service.EmpService;

@Service
public class ImgService {
	
	@Autowired
	private EmpService emp;
	
	public String getFileName(String fname) {
		String filename = fname.substring(0, fname.indexOf(".")).replaceAll("[^a-zA-Z0-9]", "");
		return filename;
	}
	
	public String getExtension(String fname) {
		return fname.substring(fname.indexOf("."), fname.length());
	}
	
	public void addImage(MultipartFile file, int empid) {
		String names = file.getOriginalFilename();
		String filep = this.getFileName(names)+"_"+System.currentTimeMillis()+this.getExtension(names);
		File nFile = new File("C:/Users/admin/Desktop/employeemanagement angular/emp profiles/"+filep);
		try {
			FileOutputStream os = new FileOutputStream(nFile);
			byte[] fbyte = file.getBytes();
			os.write(fbyte);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 EmpModel e = emp.getOneById(empid).get();
		 e.setEmpimg("http://localhost:8089/download/"+filep);
		 
		 emp.updateEmp(e);
	
	}
	
	public Resource downloadProfileImg(String filename) {
		Resource resourse = null;
		try {
			resourse = new FileSystemResource("C:/Users/admin/Desktop/employeemanagement angular/emp profiles/"+filename);
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
		

		return resourse;
	}

}

