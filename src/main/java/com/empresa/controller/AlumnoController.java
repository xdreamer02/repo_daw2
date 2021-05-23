package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

import lombok.extern.apachecommons.CommonsLog;


@CommonsLog
@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<List<Alumno>> lista(){
		System.out.println(">>>> lista ");
		List<Alumno> lstAlumno = service.listaAlumno();
		return ResponseEntity.ok(lstAlumno);
		
	}
	
	@PostMapping
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj){
		System.out.println(">>>> Crear ");
		Alumno objSalida = service.insertaActualizaAlumno(obj);
		
		if(objSalida !=null) {
			return ResponseEntity.ok(objSalida);
		}else {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj){
		System.out.println(">>>> Crear ");
		Optional<Alumno> optAlumno = service.obtienePorId(obj.getIdAlumno());
		if (optAlumno.isPresent()) {
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			if (objSalida != null) {

				return ResponseEntity.ok(objSalida);
			}else {
				return ResponseEntity.badRequest().build();
			}	
		}else {
			System.out.println(">>>> Crear ");
			return ResponseEntity.badRequest().build();

		}
		
	}
	
}
