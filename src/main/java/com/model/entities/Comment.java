package com.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
//    @JsonProperty(defaultValue = "laudantium enim quasi est quidem magnam voluptate ipsam eosntempora quo necessitatibus ndolor quam autem quasinreiciendis et nam sapiente accusantium")
    String body;
}