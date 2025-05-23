package com.sample.post_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "media")
public class Media implements Serializable {

    @Id
    private UUID id;
    private UUID userId;
    private byte[] blobContent;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    public Media(UUID userId, byte[] blobContent) {
        this.id = UUID.randomUUID(); // Automatically generate a UUID for the ID
        this.userId = userId;
        this.blobContent = blobContent;
        this.createdAt = LocalDateTime.now(); // Automatically set the creation time
    }
}