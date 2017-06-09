package study.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.domain.Sample;
import study.domain.SampleRepository;

@Controller
public class IndexController {

    @Autowired
    private SampleRepository sampleRepository;
    
    private EntityManagerFactory emf;
    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@RequestMapping("/")
    @ResponseBody
    String home() {
    	//根据规则自定义查询
    	Sample sampleTemp = sampleRepository.findBysCode("a3");
    	if(sampleTemp == null){
    		//通用保存
    		Sample sa = new Sample("a3","a3sess");
        	sampleRepository.save(sa);
    	}
    	
    	//通用方法
    	java.util.List<Sample> results = sampleRepository.findAll();
    	System.out.println("========="+results.get(0).getsCode());
    	
    	//hql模糊查询
    	List<Sample> hqlSamples = sampleRepository.findBysDescHql("a");
        System.out.println("hql模糊查询size"+hqlSamples.size());
        
        //sql模糊查询
        List<Sample> sqlSamples = sampleRepository.findBySDescSql("a");
        System.out.println("sql模糊查询size"+sqlSamples.size());
        
        //Top和Sort
        sampleRepository.findTop2BySCode("a1", new Sort("sCode","sDesc"));
        sampleRepository.findTop2BySCode("a1", new Sort(Direction.ASC,"sCode","sDesc"));
        
        //分页查询
        Pageable pageable = new PageRequest(0, 2);//支持排序
        org.springframework.data.domain.Page<Sample> result = sampleRepository.findBySCode("a1", pageable);
        System.out.println("分页查询"+result.getContent().size());
        Sample pa = new Sample();
        pa.setsCode("a1");
//        sampleRepository.findBySample(pa, pageable);
//        sampleRepository.findBySDesc("a", pageable);
        
        //使用Example以及ExampleMatcher来进行模糊查询
        Sample sa = new Sample();
        sa.setsCode("a1");
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("sCode", GenericPropertyMatchers.contains());
        Example<Sample> ex = Example.of(sa, matcher);
        sampleRepository.findAll(ex);
        
        //针对复制的sql查询,@query使用原生sql都无法满足的情况下，可以通过代码拼接sql获取hql
        EntityManager em = this.emf.createEntityManager();
        try {  
            Query query = em.createQuery("from Sample as s where s.sCode = ?1");  
            query.setParameter(1, "a1");  
            query.getResultList();  
        }finally {  
            if (em != null) {  
               em.close();  
            }  
         }
        return "Hello World! ++";
    }

    @RequestMapping("/home2")
    @ResponseBody
    String hom2e() {

        return "Hello World! ";
    }
}
