package com.example.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComebackDateRepository extends MongoRepository<ComebackDate, Long> {
    
    public ComebackDate findFirstByOrderByIdDesc();

}
