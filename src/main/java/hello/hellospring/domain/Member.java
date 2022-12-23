package hello.hellospring.domain;

public class Member {

    private Long id; //시스템이 정해줌
    private String name; //고객이 가입할 때 정함

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
