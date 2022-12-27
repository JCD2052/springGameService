package org.jcd2052.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "user_info")
public class UserInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_password")
    private String password;
//    @ManyToMany(mappedBy = "users")
//    private Set<GameInfo> games;
}
