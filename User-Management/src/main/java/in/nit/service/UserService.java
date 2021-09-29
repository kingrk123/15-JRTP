package in.nit.service;

import java.util.Map;

import in.nit.bindings.LoginForm;
import in.nit.bindings.UnlockAccForm;
import in.nit.bindings.UserRegForm;
import in.nit.exception.UserAppException;


public interface UserService {

	public String loginCheck(LoginForm loginForm)throws UserAppException;

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCities(Integer stateId);

	public String emailCheck (String emailId);

	public boolean saveUser(UserRegForm userForm) throws UserAppException;

	public boolean unlockAccount(UnlockAccForm unlockAccForm)throws UserAppException;

	public boolean forgotPwd(String emailId) throws UserAppException;
}
