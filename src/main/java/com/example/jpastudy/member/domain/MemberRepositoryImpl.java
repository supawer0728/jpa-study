package com.example.jpastudy.member.domain;

import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    private static final QMember member = QMember.member;

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> findAll() {
        return from(member).fetch();
    }
}
