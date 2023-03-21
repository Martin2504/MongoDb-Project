package com.sparta.spartamongodbfinalproject.model.entities;

import com.sparta.spartamongodbfinalproject.model.entities.theatres.Geo;
import com.sparta.spartamongodbfinalproject.model.entities.theatres.Location;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("theaters")
@Data
@Accessors(chain = true)
public class Theatre {
    //id 59a47286cfa9a3a73e51e73a
//    theaterId 1019
//    location Object
//    address Object
//    geo Object

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field("theaterId")
    private Integer theatreId;

    private Location location;
}
