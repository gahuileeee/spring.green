package kr.co.ch07.repository.shop.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.ch07.entity.shop.Customer;
import kr.co.ch07.entity.shop.QCustomer;
import kr.co.ch07.repository.shop.custom.CustomerRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
/*
   - CustomerRepositoryCustom 구현 클래스
   - RepositoryCustom에서 접미사 Custom 대신 Impl 네이밍 규칙
   - 반드시 @Repository 선언해야 함
 */

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // Q도메인 클래스 선언 (QueryDSL이 사용하는 entity)
    QCustomer qCustomer = QCustomer.customer;

    @Override
    public List<Customer> selectCustomers() {
        List<Customer> customers= jpaQueryFactory.select(qCustomer).from(qCustomer).fetch();
        // =select * from customer

        return customers;
    }

    @Override
    public Customer selectCustomer(String custId) {
        return jpaQueryFactory
                .selectFrom(qCustomer)
                .where(qCustomer.custId.eq(custId))
                .fetchOne();
    }
}
