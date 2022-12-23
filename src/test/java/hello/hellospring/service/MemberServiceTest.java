package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); //test가 끝날 떄마다 한 번씩 레파지토리를 지움
    }

    @Test
    void 회원가입(){
        //given - 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        //when - 이 것을 실행했을 때
        Long saveId = memberService.join(member);

        //then - 결과가 이게 나와야 된다
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //예외가 발생할 때 두번째 로직을 실행한다
        //assertThrows(예외 타입, 실행할 꺼)
        //IllegalStateException : 메소드가 요구된 처리를 하기에 적합한 상태에 있지 않을때

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*try {
            memberService.join(member2);
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then
    }

    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }


}