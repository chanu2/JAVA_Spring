package hello.hellospring.controller;

public class MemberForm {  // Spring이 자동으로 createMemberForm에서 받은 name을 setName을 통해서 넣어준다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }






}
