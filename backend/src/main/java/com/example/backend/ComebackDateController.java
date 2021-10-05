package com.example.backend;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ComebackDateController {

    @Autowired
	private ComebackDateRepository repository;

    @GetMapping("/last-comeback-date")
    public ComebackDate lastComebackDate(){
        return repository.findFirstByOrderByIdDesc();
    }

    
}
