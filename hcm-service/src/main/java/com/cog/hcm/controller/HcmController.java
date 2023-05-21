package com.cog.hcm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.hcm.entity.Hcm;
import com.cog.hcm.service.HcmService;

@RestController
@RequestMapping("/hcm")
public class HcmController {

	@Autowired
	private HcmService hcmService;

	@PostMapping
	public ResponseEntity<Integer> createHcm(@RequestBody @Valid Hcm hcm) {
		return new ResponseEntity<>(hcmService.createHcm(hcm), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Integer> updateHcm(@RequestBody @Valid Hcm hcm) {
		return new ResponseEntity<>(hcmService.updateHcm(hcm), HttpStatus.OK);
	}

	@GetMapping("{employeeId}")
	public ResponseEntity<Hcm> readHcmById(@PathVariable Integer employeeId) {
		return new ResponseEntity<>(hcmService.readHcmById(employeeId), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Hcm>> readAllHcm() {
		return new ResponseEntity<>(hcmService.readAllHcm(), HttpStatus.OK);
	}
}
