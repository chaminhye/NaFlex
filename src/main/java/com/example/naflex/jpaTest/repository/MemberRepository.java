package com.example.naflex.jpaTest.repository;

import com.example.naflex.jpaTest.vo.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberVO, Long> {
    // 비어있어도 잘 작동함
    // long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함
    /**
     * - JPA 처리를 담당하는 Repository는 기본적으로 4가지가 있다. (T : Entity의 타입클래스, ID : P.K 값의 Type)
     * 1) Repository<T, ID>
     * 2) CrudRepository<T, ID>
     * 3) PagingAndSortingRepository<T, ID>
     * 4) JpaRepository<T, ID>
     * */
    
    public MemberVO save(MemberVO memberVO);
    
    public List<MemberVO> findAll();

    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    public List<MemberVO> findById(String id);

    public List<MemberVO> findByName(String name);
    
    public void deleteById(Long mbrNo);

    //like검색도 가능
    public List<MemberVO> findByNameLike(String keyword);
}
