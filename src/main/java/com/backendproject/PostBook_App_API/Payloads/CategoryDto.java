package com.backendproject.PostBook_App_API.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty(message = "Category Title can't be empty")
    @Size(min=4, message="CategoryTitle must have minimum of 4 letters")
    private String categoryTitle;

    @NotEmpty(message = "Category Description can't be empty")
    @Size(min=10, message="CategoryTitle must have minimum of 10 letters")
    private String categoryDescription;

}
