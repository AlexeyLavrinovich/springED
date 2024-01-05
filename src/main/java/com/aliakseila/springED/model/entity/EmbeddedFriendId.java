package com.aliakseila.springED.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmbeddedFriendId {

    @JoinColumn(name = "owner_id")
    private Long ownerId;

    @JoinColumn(name = "friend_id")
    private Long friendId;

}
