package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository //데이터 저장
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();//save할 때 어디에 저장해야 되니까 만드는 맵
    private static long sequence = 0L; //0,1,2 키값을 생성해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버를 세이브할 때 시퀀스 값을 1씩 올려줌 //스토어에 넣기 전에 멤버의 아이디값을 세팅해줌
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //get(id) 했을 때 null이 나올 수 있으므로 Optional로 감싸기
    }


    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
/*

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        //findAny()는 하나라도 찾으면 반환하는 것
        //Stream에서 어떤 조건에 일치하는 요소 1개를 찾을 때, findAny()와 findFirst() API를 사용할 수 있다.
        //findAny()는 Stream에서 가장 먼저 탐색되는 요소를 리턴하고,
        //findFirst()는 조건에 일치하는 요소들 중에 Stream에서 순서가 가장 앞에 있는 요소를 리턴합니다.
        //둘 다 결과를 Optional로 반환함
    }
*/

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}