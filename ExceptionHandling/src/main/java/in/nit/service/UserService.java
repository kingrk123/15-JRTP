package in.nit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.dao.UserDao;
import in.nit.exception.NoDataFound;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public String getUserName(Integer userId) throws NoDataFound {
		return dao.findNameById(userId); 
	}
}
