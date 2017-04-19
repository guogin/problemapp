package com.myapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.models.Report;
import com.myapp.repositories.ReportRepository;

@RestController
@RequestMapping(path = "/")
public class ReportController {

	private final ReportRepository repository;
	
	@Autowired
	public ReportController(ReportRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public Iterable<Report> index() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Report show(@PathVariable("id") Long id) {
		return repository.findOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Report create(@RequestBody @Valid Report entity) {
		return repository.save(entity);
	}
}
