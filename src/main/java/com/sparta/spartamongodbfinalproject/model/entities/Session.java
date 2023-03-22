

package com.sparta.spartamongodbfinalproject.model.entities;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("sessions")
@Data
public class Session {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String user_id;


    private String jwt;
}

