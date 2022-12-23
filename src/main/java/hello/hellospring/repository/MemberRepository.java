package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); //요즘은 id에 널이 들어왔을 때 그냥 null을 반환하는게 아니라 Optional에 싸서 반환하는 걸 선호
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
