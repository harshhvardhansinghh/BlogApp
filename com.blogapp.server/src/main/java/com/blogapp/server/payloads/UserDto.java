package com.blogapp.server.payloads;

import com.blogapp.server.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min=4 , message="username must be minimum of 4 characters!")
    private String name;

    @Email(message = "email is not valid")
    private String email;

    @NotEmpty
    @Size(min=3, max=10, message= "Password must be minimum of 3 characters and maximum of 10 characters")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles=new HashSet<>();
}
