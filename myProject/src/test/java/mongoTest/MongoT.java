package mongoTest;

import mongoDBTest.model.MongoUser;
import mongoDBTest.mongoDao.UserMongDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by THINK on 2017/4/14.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration
@SpringBootApplication
public class MongoT {

	@Autowired
	private UserMongDao userMongDao;
	@Test
	public void mango() throws Exception {
		userMongDao.save(new MongoUser(1L,"尼玛","为U而后认为好人"));

		System.out.println(userMongDao.findAll());
	}
}
