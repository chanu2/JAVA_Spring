package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity   //이제부터 jpa가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // DB에 값을 넣으면 DB가 알아서 ID를 자동생성 해주는 것을 아이덴티티 전략이라고 한다
    private Long id;
    private String name;

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
