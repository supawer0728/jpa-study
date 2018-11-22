package com.example.jpastudy.order.domain;

import com.example.jpastudy.member.domain.Member;
import java.util.Collections;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Table(name = "order_table")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER) // default
    @JoinColumn(name = "orderer_id", nullable = false, referencedColumnName = "member_id")
    private Orderer orderer;

    @ElementCollection(fetch = FetchType.LAZY) // default
    // referecedColumnName 생략될 수 있음
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"))
    private List<OrderLine> orderLines = Collections.emptyList();

    public static Order create(List<OrderLine> orderLines, Member member) {

        Orderer orderer = Orderer.fromMember(member);
        Order order = new Order();
        order.status = OrderStatus.ORDERED;
        order.setOrderLines(orderLines);
        order.setOrderer(orderer);
        return order;
    }

    private void setOrderer(Orderer orderer) {
        this.orderer = orderer;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        if (CollectionUtils.isEmpty(orderLines)) {
            throw new IllegalArgumentException("orderLine is required");
        }
        this.orderLines = orderLines;
    }
}
