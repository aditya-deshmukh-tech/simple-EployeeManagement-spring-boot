package com.emp.empApp.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emp.empApp.models.EmpModel;
import com.emp.empApp.service.EmpService;
import com.emp.empApp.service.ImgService;


@RestController
public class EmpController {

	@Autowired
	private EmpService empservice;
	
	@Autowired
	private ImgService img;

	@GetMapping("/allemp")
	public List<EmpModel> getAllBooks() {
		return empservice.getAll();
	}
	
	@GetMapping("/single/{empid}")
	public Optional<EmpModel> getSingleEmployee(@PathVariable int empid) {
		return empservice.getOneById(empid);
	}
	
	@GetMapping("/singlebyname/{empname}")
	public List<EmpModel> getSingleByName(@PathVariable String empname){
		return empservice.getByName(empname);
	}
	
	@PostMapping("/createemp")
	public EmpModel createEmployee(@RequestBody EmpModel emp) {
		return empservice.createEmp(emp);
	}
	
	@PutMapping("/updateemp")
	public EmpModel updateEmployee(@RequestBody EmpModel emp) {
		return empservice.updateEmp(emp);
	}
	
	@DeleteMapping("/deleteemp/{empid}")
	public ResponseEntity deleteEmployee(@PathVariable int empid) {
		if(empservice.deleteEmp(empid)) {
			return ResponseEntity.ok("employee deleted..");
		}else {
			return (ResponseEntity) ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@PostMapping("/addImg/{empid}")
	public ResponseEntity addEmpImage(@RequestParam("img") MultipartFile file, @PathVariable int empid) {
		img.addImage(file, empid);
		return ResponseEntity.ok("image uploaded..");
	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> profilefileDownload(@PathVariable String filename, HttpServletRequest req) {
		Resource r = img.downloadProfileImg(filename);
		String mimeType;
		try {
			mimeType = req.getServletContext().getMimeType(r.getFile().getAbsolutePath());
		}catch(Exception e) {
			mimeType =MediaType.APPLICATION_OCTET_STREAM_VALUE;
		}
		
		mimeType = mimeType == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mimeType;
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+r.getFilename())
				.body(r);
	}
}
