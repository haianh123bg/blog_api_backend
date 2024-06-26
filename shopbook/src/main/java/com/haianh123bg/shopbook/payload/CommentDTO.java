package com.haianh123bg.shopbook.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
    private long id;

    // name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty!")
    private String name;

    // email should not be null or empty
    // email field validation
    @Email
    @NotEmpty(message = "Email should not be null or empty!")
    private String email;

    // comment body should not be null or empty
    // comment body must be minimum 10 characters
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}













