package org.jcd2052.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.jcd2052.api.utils.converters.UserRoleConvertor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "username", nullable = false, length = Integer.MAX_VALUE)
    private String username;
    @Convert(converter = UserRoleConvertor.class)
    private UserRole userRole;
    @Column(name = "time_created")
    @Generated
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime timeCreated;
    @NotNull
    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;
    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;
    @OneToMany(mappedBy = "reviewerUser")
    private Set<GameReview> gameReviews;

    public static User createUser(Integer userId, String userName, String email, String userRole) {
        User user = new User();
        user.setId(userId);
        user.setUsername(userName);
        user.setEmail(email);
        Optional.ofNullable(userRole).ifPresent(role -> user.setUserRole(UserRole.valueOf(UserRole.class, role)));
        return user;
    }
}