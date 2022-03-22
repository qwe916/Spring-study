package hello.hellospring.controller;

public class MemberForm {
    private String name;//html name 속성과 일치해야함 html에서 가져온 데이터

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
