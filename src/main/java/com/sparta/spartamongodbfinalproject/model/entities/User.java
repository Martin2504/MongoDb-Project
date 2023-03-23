package com.sparta.spartamongodbfinalproject.model.entities;


import com.sparta.spartamongodbfinalproject.model.entities.roles.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

@Document("users")
@Data
@Accessors(chain = true)
public class User {
//    _id : 59b99db4cfa9a34dcd7885b6
//    name : "Ned Stark"
//    email : "sean_bean@gameofthron.es"
//    password : "$2b$12$UREFwsRUoyF0CRqGNK0LzO0HM/jLhgUCNNIJ9RJAqMUQ74crlJ1Vu"

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    private String email;

    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();


}
