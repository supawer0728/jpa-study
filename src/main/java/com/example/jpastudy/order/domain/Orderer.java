package com.example.jpastudy.order.domain;

import com.example.jpastudy.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Table(name = "member")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Orderer {

    @Id
    @Column(name = "member_id")
    private String id;
    private String name;

    protected static Orderer fromMember(@NonNull Member member) {
        return new Orderer(member.getId(), member.getName());
    }
}
