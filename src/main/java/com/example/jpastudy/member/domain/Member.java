package com.example.jpastudy.member.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Table(name = "member")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Member {

    @Id
    private String id;
    private String name;

    @Embedded
    private Address address;

    public void changeAddress(@NonNull Address newAddress) {
        this.address = newAddress;
    }
}
