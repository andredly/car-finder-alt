package com.syska.network.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

//import javax.persistence.CascadeType;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name = "users")
@Document(collection = "users")
public class User implements UserDetails {

//    @GeneratedValue
    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @JsonIgnore
    @NonNull
    private String password;

//    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    private List<Role> authorities;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private String phone;

    @NonNull
    private String email;

//    @CreatedDate
    private Date dateCreate;
}
