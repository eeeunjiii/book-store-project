package com.spring.project.repository.item;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.project.entity.Item;
import com.spring.project.entity.QItem;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemCustomRepository {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory=new JPAQueryFactory(em);
    }

    @Override
    public Page<Item> search(String category, String keyword, Pageable pageable) {
        QItem item=QItem.item;

        BooleanBuilder builder=new BooleanBuilder();

        if(category.equals("title")) {
            builder.and(item.title.contains(keyword));
        } else if(category.equals("author")) {
            builder.and(item.author.contains(keyword));
        } else if(category.equals("publisher")) {
            builder.and(item.publisher.contains(keyword));
        }

        List<Item> content=queryFactory
                .selectFrom(item)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count=queryFactory
                .select(item.count())
                .from(item)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }
}
