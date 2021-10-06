package com.example.backend;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.bson.types.Binary;

public class ComebackDate {

    @Id
    public String id;
    
    public LocalDateTime datetime;
    public String name;
    public Binary image;


    public ComebackDate() {}

    public ComebackDate(LocalDateTime datetime, String name, Binary image){
        this.datetime = datetime;
        this.name = name;
        this.image = image;
    }

    public String getId(){
        return id;
    }

    public LocalDateTime getDate(){
        return datetime;
    }

    @Override
    public String toString() {
        return String.format("Comeback Date [id=%s, date='%s', name='%s']", 
        id, datetime, name);
    }

}