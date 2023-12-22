package com.Mani.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.Mani.Exception.ResourceNotFound;
import com.Mani.Model.Empolyee;
import com.Mani.Repository.EmpolyeeRepository;

@RestController
@RequestMapping("api/v1/empolyee")
public class EmpolyeeController {
@Autowired
private EmpolyeeRepository emprepo;
@GetMapping
public List<Empolyee> getall(){
	return emprepo.findAll();
}
@PostMapping("/add")
public Empolyee addnew(@RequestBody Empolyee Emp) {
	return emprepo.save(Emp);
}
@GetMapping("{id}")
public ResponseEntity<Empolyee> getid(@PathVariable Long id){
	Empolyee emp=emprepo.findById(id).orElseThrow(()-> new ResourceNotFound("id is not found"+id));
	return ResponseEntity.ok(emp); 
	
}
@PutMapping("{id}")
public Empolyee update(@PathVariable Long id,@RequestBody Empolyee empde){
	Empolyee empup=emprepo.findById(id).orElseThrow(()-> new ResourceNotFound("id not found"+id));
	empup.setName(empde.getName());
	empup.setEmail(empde.getEmail());
	empup.setMobile(empde.getMobile());
	return emprepo.save(empup);
	
}
@DeleteMapping("{id}")
public ResponseEntity<HttpStatus> deleteid(@PathVariable Long id) {

	Empolyee empdel=emprepo.findById(id).orElseThrow(()-> new ResourceNotFound("id not found"+id));
	emprepo.deleteById(id);
	
	return new ResponseEntity(HttpStatus.ACCEPTED);
	
}
}
