package in.nit.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import in.nit.exception.NoDataFound;

@Repository
public class UserDao {

	private Map<Integer, String> userData = new HashMap<>();
	
	public UserDao() {
		userData.put(101, "K");
		userData.put(102, "v");

	}
	
	public String findNameById(Integer userId) throws NoDataFound {
		if (userData.containsKey(userId)) {
			String name = userData.get(userId);
			return name;
		}
		throw new NoDataFound("Invalid user ID");
	}
	
}
