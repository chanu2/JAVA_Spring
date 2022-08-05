package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence는 0,1,2 키값을 생성해준다

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store.get(id)가 null일 수도 있기 때문에 Optional.ofNullable을 통해서 감싸준다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // values 값을 하나 씩 모두 반환한다
                .filter(member -> member.getName().equals(name))
                .findAny();  // 발견한 순서의 첫번째 것을 반환한다.

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();  // 다 비워준다

    }
}
