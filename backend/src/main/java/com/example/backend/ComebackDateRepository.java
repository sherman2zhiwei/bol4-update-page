package com.example.backend;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComebackDateRepository extends MongoRepository<ComebackDate, Long> {
    
    public ComebackDate findFirstByOrderByIdDesc();
    
    public ComebackDate findFirstByDatetimeGreaterThanOrderByIdDesc(LocalDateTime datetime);

    public ComebackDate findFirstByDatetimeLessThanOrderByIdDesc(LocalDateTime datetime);

}
