package com.dystopia.postservice.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "hashtags")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NaturalId
    @Size(min = 5, max = 30)
    @Column(name = "hashtag_name", nullable = false, length = 30, unique = true)
    private String hashtagName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "hashtags")
    @JsonIgnore
    @ToString.Exclude
    private List<Post> posts;

}
