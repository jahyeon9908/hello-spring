package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //비즈니스 로직을 만든다
public class MemberService {

    private final MemberRepository memberRepository;

    //맨 위 @Service를 보고 MemberService를 컨테이너에 등록할 때 MemberService가 생성되는데
    //그 때 생성자에 붙은 @Autowired로 인해 memberRepository가 MemberService 안으로 들어감
    //엄밀하게 말하자면 인터페이스인 MemberRepository의 구현체인 MemoryMemberRepository가 들어가는 거지
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {


        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //result에 값이 있다면 이 로직을 실행시켜줌
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        //옵셔널을 바로 반환하는 게 별로 좋지 않기 때문에 밑처럼 쓰는 것을 권장
        //memberRepository.findByName(member.getName())은 옵셔널을 리턴하니까 바로 ifPresent 쓸 수 있음

        validateDuplicationMember(member);
        //같은 이름이 있는 중복 회원X
        //member의 이름을 아직 save하기 전인데 findByName()으로 이름을 찾았다? 중복인거지
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member){
        memberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
