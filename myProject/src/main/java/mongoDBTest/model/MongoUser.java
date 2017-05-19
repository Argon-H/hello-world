/*******************************************************************************
 * Project Key : CPPII
 * Create on 2017年4月13日 下午2:52:07
 * Copyright (c) 2008 - 2011.深圳市齐采科技有限公司版权所有. 粤ICP备16034195号
 * 注意：本内容仅限于深圳市齐采科技服务有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package mongoDBTest.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 李俊华  2017年4月13日 下午2:52:07
 */
@Setter
@Getter
public class MongoUser {

	@Id
    private Long id;
    
    private String name;
    
    private String remark;

    public MongoUser() {
    }

    public MongoUser(Long id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;

    }
}

