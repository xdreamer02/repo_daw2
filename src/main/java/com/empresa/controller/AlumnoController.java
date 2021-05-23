package com.empresa.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Alumno> elimina(@PathVariable("id") int idAlumno){
		System.out.println(">>>Elmina");
		Optional<Alumno> optAlumno = service.obtienePorId(idAlumno);
		if(optAlumno .isPresent()) {
			service.eliminaAlumno(idAlumno);
			return ResponseEntity.ok(optAlumno.get());
		}else {
			System.out.println("elimina  no existe");
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/buscarPorDNI/{dni}")

	public ResponseEntity<List<Alumno>> buscar(@PathVariable("dni") String dni) {

		System.out.println("Dni: " + dni);

		List<Alumno> lstAlumno = service.listaPorDni(dni);

		if (!CollectionUtils.isEmpty(lstAlumno)) {
			return ResponseEntity.ok(lstAlumno);
		} else {
			System.out.println(">>>> buscar por dni - no existen alumnos con ese dni : " + dni);
			return ResponseEntity.badRequest().build();
		}

	}
	
}
