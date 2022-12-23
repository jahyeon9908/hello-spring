package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //외부 요청 받음
public class MemberController {
    //스프링 처음에 뜰 때 스프링 컨테이너라는 스프링 통이 생기는 데
    // 거기에 @Controller가 있으면 MemberController라는 객체가 생성돼서 스프링에 넣어두고 스프링이 관리함
    //== 스프링 빈이 관리된다

    private  final MemberService memberService;
    //멤버서비스를 가져다 써야 하는데 이전에 하던 것처럼 new로 쓸 수도 있는데
    //스프링이 관리를 하게 되면 다 스프링 컨테이너에 등록을 하고 컨테이너에서 받아다 써야한다
    //왜냐하면 new로 하면 멤버컨트롤러 말고 다른 여러 컨트롤러들이 멤버서비스를 가져다 쓸 수 있게 되고 여러개의 멤버서비스 객체들이 만들어짐
    //하나만 생성해서 같이 공용으로 쓰면 됨

    //스프링 컨테이너에 등록을 하고 쓰면 됨 딱 하나만 등록되거든(싱글톤)

    //스프링 컨테이너가 뜰 때 MemberController 라는 객체가 생성되면 생성자가 호출된다
    //그런데 생성자에 @Autowired가 있으면 memberService를 스프링이 스프링 컨테이너에 있는 MemberService와 연결시켜 준다
    @Autowired
    public MemberController(MemberService memberService) {
        //생성자에 Autowired를 쓰면 MmeberController가 생성이 될 때 스프링에서 그 안에 스프링 빈에 등록되어 있는 memberService를 넣어줌
        //-> Dependency Injection(의존성 주입)

        this.memberService = memberService;
    }
}
