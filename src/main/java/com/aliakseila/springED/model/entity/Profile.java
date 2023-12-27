package com.aliakseila.springED.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "_profile")
public class Profile {

    @Id
    private Long id;
    private String name;
    private String surname;
    private Integer age;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Profile(User user){
        this.user = user;
        this.name = "";
        this.surname = "";
        this.age = 0;
    }

}
