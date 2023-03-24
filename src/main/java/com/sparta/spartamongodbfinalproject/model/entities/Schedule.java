package com.sparta.spartamongodbfinalproject.model.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Showings;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("schedule")
@Data
@Accessors(chain = true)
public class Schedule {

    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String id;


    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime day;
    private ArrayList<Showings> showings;



}
