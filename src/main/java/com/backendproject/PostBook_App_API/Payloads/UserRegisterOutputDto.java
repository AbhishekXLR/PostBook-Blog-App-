package com.backendproject.PostBook_App_API.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterOutputDto extends UserRegisterInputDto {

    private int id;


}
