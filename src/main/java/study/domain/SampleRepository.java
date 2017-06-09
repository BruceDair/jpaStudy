package study.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * SampleRepository�̳� {@link JpaRepository}�������Ĭ�ϵķ���
 * �磺findAll��ѯ�������ѯ����ҳ��ѯ�ȵȡ�
 * jpa�Ĺٷ��ĵ����Բ鿴{@link http://docs.spring.io/spring-data/jpa/docs/1.11.3.RELEASE/reference/html/}
 * @author daihongchang
 *
 */
public interface SampleRepository extends JpaRepository<Sample, Long> {
	
	/**
	 * JPA�п��Ը��� findByXX(XX)���Զ�ƥ���ѯ
	 * find��By, read��By, query��By, count��By, and get��By
	 * findBy...OrderBy...Asc  findBy...OrderBy...Desc    findBy...And...AllIgnoreCase  findDistinct...By...Or...
	 * findBy...EndsWith
	 * @param sCode
	 * @return
	 */
    Sample findBysCode(String sCode);
    
    /**
     * JPA���Ը���ע���е�hql��ѯ����
     * @return
     */
    @Query("select sa from Sample sa where sa.sDesc like %?1%")
    List<Sample> findBysDescHql(String sDesc);
    
    /**
     * JPA�п��Ը���ע�͵�sql��ѯ����
     * @param sDesc
     * @return
     */
    @Query(value = "SELECT * FROM Sample WHERE s_desc like %?1%", nativeQuery = true)
    List<Sample> findBySDescSql(String sDesc);
    
    /**
     * ��ѯǰ����
     * @param sCode
     * @param sort
     * @return
     */
    List<Sample> findTop2BySCode(String sCode,Sort sort);
    
    /**
     * ��ҳ��ѯ
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
