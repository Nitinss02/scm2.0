package com.scm.scm20.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Contactform {

    @NotBlank(message = "Name is requried")
    private String name;

    @Email
    @NotBlank(message = "Email is requried")
    private String email;

    @NotBlank(message = "PhoneNumber is Requried")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Address is requried")
    private String address;
    private String Description;
    private boolean Favourite;
    private String websitelink;
    private String Linkedinlink;
    private MultipartFile contactImage;
    private String picture;
}
