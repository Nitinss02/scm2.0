package com.scm.scm20.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class userform {
    @NotBlank(message = "UserName is Requried")
    @Size(min = 3, message = "Minimum 3 Charater requried")
    private String name;
    @Email(message = "Invalid Email Address")
    private String email;
    @NotBlank(message = "Password Must be Requried")
    @Size(min = 6, message = "password Minimum 6 charater")
    private String password;
    @Size(min = 8, max = 12, message = "Invalid Number")
    private String MobileNumber;
    @NotBlank(message = "About is requried")
    private String about;
}
