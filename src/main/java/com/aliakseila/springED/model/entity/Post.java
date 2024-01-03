package com.aliakseila.springED.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "_post")
@NamedEntityGraph(
        name = "post-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "author", subgraph = "profile-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "profile-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "user")
                        }
                )
        }
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_info_id")
    private DateInfo dateInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    @ToString.Exclude
    private Profile author;


}
