package com.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Comment {
    String postId;
    String name;
    String email;
    String body;
}