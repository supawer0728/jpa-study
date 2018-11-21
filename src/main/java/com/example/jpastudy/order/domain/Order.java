package com.example.jpastudy.order.domain;

import com.example.jpastudy.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Table(name = "order_table")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER) // default
    @JoinColumn(name = "orderer_id", nullable = false, referencedColumnName = "member_id")
    private Orderer orderer;

    public static Order create(Member member) {
        Orderer orderer = Orderer.fromMember(member);

        Order order = new Order();
        order.setOrderer(orderer);
        return order;
    }

    private void setOrderer(Orderer orderer) {
        this.orderer = orderer;
    }
}
