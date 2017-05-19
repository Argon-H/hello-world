package mongoDBTest.mongoDao;

import mongoDBTest.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by THINK on 2017/4/14.
 */
public interface UserMongDao extends MongoRepository {
	MongoUser find(String name);
}
