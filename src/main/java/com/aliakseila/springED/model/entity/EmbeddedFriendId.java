package com.aliakseila.springED.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmbeddedFriendId {

    @JoinColumn(name = "profile_id")
    private Long profileId;

    @JoinColumn(name = "friend_id")
    private Long friendId;

}
