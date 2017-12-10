package com.syska.network.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "roles")
@Document(collection = "roles")
public class Role implements GrantedAuthority {


//    @GeneratedValue
    @Id
    private String id;

    @NonNull
    private String authority;

}
