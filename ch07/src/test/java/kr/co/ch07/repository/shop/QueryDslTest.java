package kr.co.ch07.repository.shop;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.JavaTemplates;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import kr.co.ch07.entity.shop.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QueryDslTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QCustomer qCustomer = QCustomer.customer;
    QProduct qProduct = QProduct.product;
    QOrder qOrder = QOrder.order;

    public void test01(){
       List<Product> products = jpaQueryFactory.select(qProduct).from(qProduct).fetch();
        // = select * from product
        // 사실 이런건 jpa findAll 로 조회하기
    }

    @Test
    public void test02(){
        //선택 조회
        List<Customer> customers =
                jpaQueryFactory.
                select(
                        Projections.fields(
                                Customer.class,
                                qCustomer.custId,
                                qCustomer.name,
                                qCustomer.age
                        )
                )
                .from(qCustomer).fetch();
        //= select custId, name, age from customer
        log.info(customers.toString());
    }

    @Test
    public void test03(){
        //조건 조회
        List<Customer> customers1 =jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.name.eq("김유신")).fetch();
        // = select * from customer where name = '김유신'
        List<Customer> customers2 =jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.name.ne("김유신")).fetch();
        // = select * from customer where name != '김유신'

        log.info(customers1.toString());
        log.info(customers2.toString());
    }

    @Test
    public void test04(){
        List<Customer> result1 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.gt("30")).fetch();
        List<Customer> result2 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.goe("30")).fetch();
        // where age >= 30
        List<Customer> result3 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.lt("30")).fetch();
        List<Customer> result4 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.loe("30")).fetch();
        //where age <= 30
         log.info("result1 :"+result1.toString());
    }

    @Test
    public void test05(){
        List<Customer> customers =jpaQueryFactory.selectFrom(qCustomer)
                .where(qCustomer.age.in("21","31","41"))
                .fetch();
        log.info("customers : "+customers);
    }

    @Test
    public void test06(){
        List<Customer> customers =  jpaQueryFactory.selectFrom(qCustomer)
                .where(qCustomer.name.like("%신"))
                .fetch();
        log.info("customers : "+customers);
    }


    public void test07(){
     jpaQueryFactory.selectFrom(qProduct)
             .where(qProduct.stock.gt(0))
             .orderBy(qProduct.price.desc()).fetch();
    }

    public void test08(){
     jpaQueryFactory.selectFrom(qProduct)
             .where(qProduct.stock.gt(0))
             .orderBy(qProduct.price.asc())
             .offset(0)
             .limit(3)
             .fetch();
     // select ~ where stock > 0 order by price asc limit (0, 3)
    }
    /*
    public void test09(){
        //ProductAggDTO 생성 (집계 class)
        //private int priceSum, double price Avg....int priceMin
        //이렇게 Avg만 double로 선언하기
        jpaQueryFactory
                .select(
                        Projections.fields(
                                ProductAggDTO.class,
                                qProduct.price.sum().as("priceSum"),
                                qProduct.price.avg().as("priceAvg"),
                                qProduct.price.max().as("priceMax"),
                                qProduct.price.min().as("priceMin")
                        )
                ).from(qProduct).fetchOne();
    }
*/
    public void test10(){
        //select ~ where orderStatus = 1 group by orderer having custId >=2;
        jpaQueryFactory.selectFrom(qOrder)
                .where(qOrder.orderStatus.eq(1))
                .groupBy(qOrder.customer.custId)
                .having(qOrder.customer.custId.count().goe(2))
                .fetch();
    }

    @Transactional
    public void test11(){
        //join
        jpaQueryFactory.select(qOrder, qCustomer)
                .from(qOrder)
                .join(qCustomer)
                .on(qOrder.customer.eq(qCustomer))
                .fetch();
    }

    @Test
    public void test12(){
        String name = "김유신";
        String age = "21";

        //동적 쿼리 생성 builder
        BooleanBuilder builder = new BooleanBuilder();

        if(name != null){
            builder.and(qCustomer.name.eq(name));
        }

        if(age != null){
            builder.and(qCustomer.age.eq(age));
        }

        List<Customer> customers = jpaQueryFactory.
                selectFrom(qCustomer)
                .where(builder)
                .fetch();

        log.info("test12 : "+customers.toString());
    }

    @Test
    public  void test13(){
        String keyword = "유신";
        //동적쿼리 생성을 위한 booleanExpression
        BooleanExpression expression = qCustomer
                .name.containsIgnoreCase(keyword)
                .or(qCustomer.addr.containsIgnoreCase(keyword));
        //name contain(keyword) or add contain(keyword)

        List<Customer> customers =jpaQueryFactory.selectFrom(qCustomer)
                .where(expression)
                .fetch();
        log.info("test12 : "+customers.toString());
    }
}
