package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence =0L;

    /* static 으로 생성되었기때문에 MemberRepository 가 new 로 많아도 얘는 딱 하나만 생성된다 */

    private static final MemberRepository instance = new MemberRepository(); /*싱글톤으로 만들때는 private 으로 생성자 막아야함*/

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository() {
    }

    public static Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public static Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
