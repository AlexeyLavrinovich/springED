package com.aliakseila.springED.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "_post")
@AssociationOverrides({
                @AssociationOverride(
                        name = "createdBy",
                        joinColumns = @JoinColumn(name = "profile_id")
                ),
                @AssociationOverride(
                        name = "createdAt",
                        joinColumns = @JoinColumn(name = "created_at")
                ),
                @AssociationOverride(
                        name = "modifiedAt",
                        joinColumns = @JoinColumn(name = "modified_at")
                ),
})
@NamedEntityGraph(
        name = "post-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "createdBy", subgraph = "profile-subgraph")
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
public class Post extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;


}
