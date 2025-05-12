package com.sample.post_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Data
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "text_content", nullable = false, length = 500)
    private String textContent;

    @Column(name = "media_ids")
    private String mediaIds;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructor
    public Post(UUID userId, String textContent, String mediaIds) {
        this.userId = userId;
        this.textContent = textContent;
        this.mediaIds = mediaIds;
        this.createdAt = LocalDateTime.now(); // Automatically set the creation time
    }

}