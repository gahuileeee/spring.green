package kr.co.ch07.repository.shop;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public  void selectCustomers(){
        log.info(customerRepository.selectCustomers().toString());
    }

    @Test
    public  void selectCustomer(){
        log.info(customerRepository.selectCustomer("a1234").toString());
    }

}