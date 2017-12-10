package com.syska.network.pojos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.syska.network.util.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldMatch(first = "password", second = "passwordConfirmation", message = "The password fields must match")
public class UserRegistration {

    @NotBlank
    @Size(min=4, max=255, message = "Username size must be between 4 and 255")
    private String username;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min=8, max=25, message = "password size must be between 8 and 25")
    private String password;

    @NotBlank
    @Size(min=8, max=25, message = "Password Confirmation size must be between 8 and 25")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String passwordConfirmation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z", timezone="GMT")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date dateCreate;

    @NotBlank
    @Email
    private String email;

}
