package com.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Post {
    String userId;
    String title;
    String body;
}
