package study.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * SampleRepository继承 {@link JpaRepository}，有许多默认的方法
 * 如：findAll查询，排序查询，分页查询等等。
 * jpa的官方文档可以查看{@link http://docs.spring.io/spring-data/jpa/docs/1.11.3.RELEASE/reference/html/}
 * @author daihongchang
 *
 */
public interface SampleRepository extends JpaRepository<Sample, Long> {
	
	/**
	 * JPA中可以根据 findByXX(XX)来自动匹配查询
	 * find…By, read…By, query…By, count…By, and get…By
	 * findBy...OrderBy...Asc  findBy...OrderBy...Desc    findBy...And...AllIgnoreCase  findDistinct...By...Or...
	 * findBy...EndsWith
	 * @param sCode
	 * @return
	 */
    Sample findBysCode(String sCode);
    
    /**
     * JPA可以根据注解中的hql查询数据
     * @return
     */
    @Query("select sa from Sample sa where sa.sDesc like %?1%")
    List<Sample> findBysDescHql(String sDesc);
    
    /**
     * JPA中可以根据注释的sql查询数据
     * @param sDesc
     * @return
     */
    @Query(value = "SELECT * FROM Sample WHERE s_desc like %?1%", nativeQuery = true)
    List<Sample> findBySDescSql(String sDesc);
    
    /**
     * 查询前两条
     * @param sCode
     * @param sort
     * @return
     */
    List<Sample> findTop2BySCode(String sCode,Sort sort);
    
    /**
     * 分页查询
     * @param lastname
     * @param pageable
     * @return
     */
    Page<Sample> findBySCode(String sCode, Pageable pageable);
    
//    Page<Sample> findBySample(Sample sample, Pageable pageable);
//    @Query(value = "SELECT * FROM Sample WHERE S_CODE = %?1%",
//    	    countQuery = "SELECT count(*) FROM Sample WHERE S_CODE = %?1%",
//    	    nativeQuery = true)
//    Page<Sample> findBySDesc(String sDesc, Pageable pageable);
}
