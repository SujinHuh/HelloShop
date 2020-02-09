package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * @Transactional 롤백을 기본적으로 한다. 벙보를 보고싶다면,
 *  @Rollback(false)
 */
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;


    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Huh");

        //When
        Long saveId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예약() throws Exception {
        //given

        Member memberOne = new Member();
        memberOne.setName("Huh");

        Member memberTwo = new Member();
        memberTwo.setName("Huh");

        //When
        memberService.join(memberOne);
        memberService.join(memberTwo);// 예외가 발생한다.!!

        //Then
       fail(" 예외가 발생해야 한다.");
    }
}