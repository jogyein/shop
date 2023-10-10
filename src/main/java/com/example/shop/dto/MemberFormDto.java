package com.example.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 값입니다.")
    private  String name;

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "형식 좀 지켜라")
    private String email;

    @NotBlank(message = "비번은 필수 값입니다.")
    @Length(min=8, max=16, message = "비번은 8자이상, 16자 이하로 작성해라")
    private String password;

    @NotBlank(message = "주소는 필수 값입니다.")
    private String address;
}
