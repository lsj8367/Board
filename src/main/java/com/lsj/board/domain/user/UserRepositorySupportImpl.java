package com.lsj.board.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.lsj.board.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport{

    private final JPAQueryFactory jpaQueryFactory;

    public User findByName(String name) {

        return jpaQueryFactory.selectFrom(user)
                .where(user.name.eq(name))
                .fetchOne();
    }
}
