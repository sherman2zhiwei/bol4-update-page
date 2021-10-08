package com.example.backend;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RestController
public class ComebackDateController {

    @Autowired
	private ComebackDateRepository repository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/last-comeback-date")
    public ComebackDate lastComebackDate(@RequestParam(name = "datetime") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime datetime){
        return repository.findFirstByDatetimeLessThanOrderByIdDesc(datetime);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/next-comeback-date")
    public ComebackDate nextComebackDate(@RequestParam(name = "datetime") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime datetime){
        ComebackDate nextComebackDate = repository.findFirstByDatetimeGreaterThanOrderByIdDesc(datetime);

        if (nextComebackDate == null){
            return new ComebackDate();
        }

        return nextComebackDate;
    }    
}
