package hsu.readme.service;

import hsu.readme.Repository.MemberRepository;
import hsu.readme.domain.Member;
import hsu.readme.domain.University;
import hsu.readme.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외_이메일() throws Exception{
        //given
        Member member1 = new Member();
        member1.setEmail("test@test.com");
        member1.setName("kim");

        Member member2 = new Member();
        member2.setEmail("test@test.com");
        member2.setName("cho");

        //when
        memberService.join(member1);
        assertThrows(ApiException.class, ()-> memberService.join(member2));

        //fail("예외가 발생해야 합니다.");
    }

    @Test
    public void 중복_회원_예외_이름() throws Exception{
        //given
        Member member1 = new Member();
        member1.setEmail("test@test.com");
        member1.setName("cho");

        Member member2 = new Member();
        member2.setEmail("test1@test.com");
        member2.setName("cho");

        //when
        memberService.join(member1);
        assertThrows(ApiException.class, ()-> memberService.join(member2));

        //fail("예외가 발생해야 합니다.");
    }

    @Test
    public void 로그인() throws Exception{
        //given
        Member member = new Member();
        member.setEmail("test@test.com");
        member.setPassword("1234");

        //when
        memberService.join(member);
        Long loginUserId = memberService.login(member.getEmail(), member.getPassword());
    }

    @Test
    public void 로그인_이메일틀림() throws Exception{
        //given
        Member member = new Member();
        member.setEmail("test@test.com");
        member.setPassword("1234");

        //when
        memberService.join(member);
        assertThrows(ApiException.class, ()->memberService.login(member.getEmail(), "123"));
    }

    @Test
    public void 로그인_비밀번호틀림() throws Exception{
        //given
        Member member = new Member();
        member.setEmail("test@test.com");
        member.setPassword("1234");

        //when
        memberService.join(member);
        assertThrows(ApiException.class, ()->memberService.login("test", "1234"));
    }
}