package com.aliakseila.springED.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "_profile")
@NamedEntityGraph(
        name = "profile-with-posts-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "user"),
                @NamedAttributeNode(value = "posts"),
                @NamedAttributeNode(value = "friends", subgraph = "friend-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "friend-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "owner"),
                                @NamedAttributeNode(value = "friend", subgraph = "friend-profile-subgraph")
                        }
                ),
                @NamedSubgraph(
                        name = "friend-profile-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "user"),
                                @NamedAttributeNode(value = "posts"),
                                @NamedAttributeNode(value = "friends")
                        }
                )
        }
)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "first_name")
    private String firstName;
    @JoinColumn(name = "last_name")
    private String lastName;
    private Integer age;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Friend> friends;
}
