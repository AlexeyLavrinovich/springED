package com.aliakseila.springED.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_friend")
public class Friend {

    @EmbeddedId
    private EmbeddedFriendId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    private Profile owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("friendId")
    @JoinColumn(name = "friend_id")
    private Profile friend;

}
