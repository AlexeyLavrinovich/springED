package com.aliakseila.springED.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_friend")
@NamedEntityGraph(
        name = "friend-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "owner", subgraph = "profile-friend-subgraph"),
                @NamedAttributeNode(value = "friend", subgraph = "profile-friend-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "profile-friend-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "user"),
                                @NamedAttributeNode(value = "posts"),
                                @NamedAttributeNode(value = "friends")
                        }
                )
        }
)
public class Friend {

    @EmbeddedId
    private EmbeddedFriendId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ownerId")
    @ToString.Exclude
    private Profile owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("friendId")
    @ToString.Exclude
    private Profile friend;

}
