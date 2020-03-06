package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 주문 2개
 * userA
 * JPA1 BOOK
 * JPA2 BOOK
 * userB
 * SPRING1 BOOK
 * SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInitOne();
        initService.dbInitTwo();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInitOne() {

            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book bookOne = createBook("JPA1 Book", 10000, 100);
            em.persist(bookOne);

            Book bookTwo = createBook("JPA2 Book", 20000, 100);
            em.persist(bookTwo);

            OrderItem orderItemOne = OrderItem.createOrderItem(bookOne, 10000, 1);
            OrderItem orderItemTwo = OrderItem.createOrderItem(bookTwo, 20000, 2);

            Order order = createDelivery(member, orderItemOne, orderItemTwo);
            em.persist(order);
        }

        private Order createDelivery(Member member, OrderItem orderItemOne, OrderItem orderItemTwo) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return Order.createOrder(member, delivery, orderItemOne, orderItemTwo);
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book bookOne = new Book();
            bookOne.setName(name);
            bookOne.setPrice(price);
            bookOne.setStockQuantity(stockQuantity);
            return bookOne;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInitTwo() {

            Member member = createMember("userB", "부산", "2", "2222");
            em.persist(member);

            Book bookOne = createBook("Spring1 Book", 20000, 200);
            em.persist(bookOne);

            Book bookTwo = createBook("Spring2 Book", 40000, 300);
            em.persist(bookTwo);

            OrderItem orderItemOne = OrderItem.createOrderItem(bookOne, 20000, 3);
            OrderItem orderItemTwo = OrderItem.createOrderItem(bookTwo, 40000, 4);

            Order order = createDelivery(member, orderItemOne, orderItemTwo);
            em.persist(order);
        }
    }


}

