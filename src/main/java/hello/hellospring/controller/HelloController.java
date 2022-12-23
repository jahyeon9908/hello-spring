package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //웹 애플리케이션에서 첫번째 진입점인 컨트롤러
public class HelloController {

    @GetMapping("hello") //웹 애플리케이션에서 localhost:8080/hello 라고 들어오면 이 메소드를 호출해줌
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); //스프링에서 파라미터를 준 경우
        return "hello2"; //누구랑 맞춰야 하는거지? -> resources 폴더 밑 templates 폴더의 html 중 같은 이름을 찾아서 띄움
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){//웹에서 파라미터 받기@RequestParam
        model.addAttribute("name2", name);//첫번째 네임은 키 두번째 네임은 띄울 값 //model은 넘어온 값을 받는 곳
        return "hello-template";
    }//웹에서 파라미터 받기 -name에다 값을 주려면 주소창에 http://localhost:8080/hello-mvc?name=spring!라고 쳐야됨(?는 get방식)
    //그러면 name=spring!에서 얻은 값 spring!이 name으로 들어감
    //model의 키와 밸류(name2, spring!)을 viewResolver가 잡아서 hello-template으로 넘김

    @GetMapping("hello-string")
    @ResponseBody //http는 헤더부와 바디부로 나뉘는데 그 중 바디부에 직접 리턴값을 넣겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ //json 방식(키와 밸류로 이루어짐)

        Hello hello = new Hello();
        hello.setName(name); //hello란 객체에 주소창에서 받아온 name 값을 넣어주자
        return hello; //객체를 넘겨보자 //객체를 받은 스프링은 디폴트로 제이슨 방식(키와 밸류로 이루어짐)으로 데이터를 만들어서 http응답에 반환한다
    }

    static class Hello{ //static 클래스로 만들면 클래스 안의 클래스 가능
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
