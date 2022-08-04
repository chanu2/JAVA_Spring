package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // test메서드가 끝날 때 마다 동작을 하는것
    public void afterEach(){
        repository.clearStore();
    }

    @Test   // 순서와 상관없이 실행됨 그래서 clear해줄 필요가 있다 하나의 태스트가 끝날때마다 깔끔하게 지워야함
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member,result);
        assertThat(member).isEqualTo(result);  // 요즘 많이 사용하는 코드   //import static org.assertj.core.api.Assertions.*; 하고 바로 assertthat 가능
    }

    @Test
    public void findByName(){
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("spring2");
        repository.save(member2);

        repository.findByName("spring1");

        Member result =repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        repository.findAll();
        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2  );



    }
}
