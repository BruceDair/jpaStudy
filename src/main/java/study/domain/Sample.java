package study.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sample implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String sCode;

    @Column
    private String sDesc;

    public Sample(){}
    
    public Sample(String sCode,String sDesc){
    	this.sCode = sCode;
    	this.sDesc = sDesc;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }

}
