package com.sparta.spartamongodbfinalproject.logicalOperator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
@Data
@Accessors(chain = true)
public class Success {
    private String operation;
    private String entity;

    public Success(String operation, String entity) {
        this.operation = operation;
        this.entity = entity;
    }
}
