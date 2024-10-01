package org.jcd2052.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.entities.enums.PostType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post implements IEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_user_id", nullable = false)
    private User postUser;
    @NotNull
    @Column(name = "post_type", nullable = false, length = Integer.MAX_VALUE)
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @NotNull
    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;
    @NotNull
    @Column(name = "post_content", nullable = false, length = Integer.MAX_VALUE)
    private String content;
    @Column(name = "time_created", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = ApiConstants.APPLICATION_DATE_FORMAT)
    private LocalDateTime timeCreated;
    @Column(name = "time_updated", nullable = false)
    @UpdateTimestamp
    @DateTimeFormat(pattern = ApiConstants.APPLICATION_DATE_FORMAT)
    private LocalDateTime timeUpdated;
    @OneToMany(mappedBy = "post")
    private Set<PostComment> postComments = new LinkedHashSet<>();
}