package in.nit.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.nit.bindings.LoginForm;
import in.nit.bindings.UnlockAccForm;
import in.nit.bindings.UserRegForm;
import in.nit.constants.AppConstants;
import in.nit.exception.UserAppException;
import in.nit.model.City;
import in.nit.model.CountryMaster;
import in.nit.model.State;
import in.nit.model.User;
import in.nit.props.AppProperties;
import in.nit.repository.CityRepo;
import in.nit.repository.CountryRepo;
import in.nit.repository.StateRepo;
import in.nit.repository.UserRepo;
import in.nit.service.UserService;
import in.nit.util.EmailUtils;
import in.nit.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepo countryrepo;

	@Autowired
	private StateRepo staterepo;

	@Autowired
	private CityRepo cityrepo;

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private AppProperties appProps;

	@Autowired
	private EmailUtils email;

	private static Logger logger = LoggerFactory.getLogger(PwdUtils.class);

	@Override
	public String loginCheck(LoginForm loginForm)throws UserAppException {
		String msg;
		String encryptedPwd = null;
		try {
			encryptedPwd = PwdUtils.encrypt(loginForm.getPwd());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		Map<String, String> msgs = appProps.getMessages();
		User user =userrepo.findByEmailAndPassword(loginForm.getEmail(), encryptedPwd);
		if (user !=null) {
			String accStatus = user.getAccStatus();
			if(AppConstants.LOCKED.equals(accStatus)) {
				msg =msgs.get(AppConstants.ACC_LOCKED);
			}else {
				msg =AppConstants.SUCCESS_MSG;
			}
		}else {
			msg =msgs.get(AppConstants.INVALID_CREDENTIALS);
		}
		return msg;
	}


	@Override
	public Map<Integer, String> getCountries() {
		List<CountryMaster> countries = countryrepo.findAll();
		Map<Integer, String> countryMap= new HashMap<>();
		countries.forEach(country ->
			countryMap.put(country.getCountryId(), country.getCountryName()));
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<State> states =staterepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		states.forEach(state->
			stateMap.put(state.getStateId(), state.getStateName()));
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<City> cities =cityrepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cities.forEach(city->
			cityMap.put(city.getCityId(), city.getCityName()));
		return cityMap;
	}

	@Override
	public String emailCheck(String emailId) {
		Optional<User> findOne = getUserByEmail(emailId);
		if (findOne.isPresent()) {
			return AppConstants.DUPICATE;
		}else {
			return AppConstants.UNIQUE;
		}
	}

	@Override
	public boolean saveUser(UserRegForm userForm) throws UserAppException {
		User entity = new User();
		BeanUtils.copyProperties(userForm, entity);
		entity.setAccStatus(AppConstants.LOCKED);
		String randomPwd = generateRandomChars(6);
		String encryptedPwd;
		try {
			encryptedPwd = PwdUtils.encrypt(randomPwd);
			entity.setPassword(encryptedPwd);
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		entity=userrepo.save(entity);
		String emailBody = readUnlockAccEmailBody(entity);
		String sub = appProps.getMessages().get(AppConstants.UNLOCK_ACC_EMAIL_SUB);
		try {
			email.sendEmail(userForm.getEmail(), sub, emailBody);
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		return entity.getUserId() !=null ? true : false; 
	}

	@Override
	public boolean unlockAccount(UnlockAccForm unlockAccForm)throws UserAppException {
		String myEmail=unlockAccForm.getEmail();
		String tempPwd = unlockAccForm.getTempPzzwd();
		String encryptedPwd =null;
		try {
			encryptedPwd = PwdUtils.encrypt(tempPwd);
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		User user = userrepo.findByEmailAndPassword(myEmail, encryptedPwd);
		if (user!=null) {
			String newPwd = unlockAccForm.getNewPzzwd();
			String encryptedNewPwd =null ;
			try {
				encryptedNewPwd = PwdUtils.encrypt(newPwd);
			} catch (Exception e) {
				logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
			}
			user.setPassword(encryptedNewPwd);
			user.setAccStatus(AppConstants.UNLOCKED);
			userrepo.save(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean forgotPwd(String emailId)throws UserAppException {
		User entity =new User();
		entity.setEmail(emailId);
		Example<User> example = Example.of(entity);
		Optional<User> findOne = userrepo.findOne(example);
		if (findOne.isPresent()) {
			User usr = findOne.get();
			String emailBody = readForgotPwdEmailBody(usr);
			String sub = appProps.getMessages().get(AppConstants.RECOVER_PZZWD_EMAIL_SUB);
			try {
				email.sendEmail(usr.getEmail(), sub, emailBody);
			} catch (Exception e) {
				logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
			}
			return true;
		}else {
			return false;
		}
	}

	private String readForgotPwdEmailBody(User entity) throws UserAppException {
		StringBuilder sb = new StringBuilder(AppConstants.EMPTY_STAR);
		String mailBody = AppConstants.EMPTY_STAR;
		String fileName = appProps.getMessages().get(AppConstants.RECOVER_PZZWD_EMAIL_BODY_FILE);
		try (FileReader fr= new FileReader(fileName) ){
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			String decrpted=PwdUtils.decrypt(entity.getPassword());
			mailBody = sb.toString();
			mailBody = mailBody.replace(AppConstants.FNAME, entity.getFname());
			mailBody = mailBody.replace(AppConstants.LNAME, entity.getLname());
			mailBody = mailBody.replace(AppConstants.PZZWD, decrpted);
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		return mailBody;
	}


	private Optional<User> getUserByEmail(String emailId) {
		User entity =new User();
		entity.setEmail(emailId.trim());
		Example<User> example=Example.of(entity);
		return userrepo.findOne(example);
	}

	private static String generateRandomChars(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(AppConstants.CANDIDATECHARS.charAt(random.nextInt(AppConstants.CANDIDATECHARS
					.length())));
		}
		return sb.toString();
	}

	private String readUnlockAccEmailBody(User entity) throws UserAppException {
		StringBuilder sb = new StringBuilder(AppConstants.EMPTY_STAR);
		String mailBody = AppConstants.EMPTY_STAR;
		String fileName = appProps.getMessages().get(AppConstants.UNLOCK_ACC_EMAIL_BODY_FILE);
		try (FileReader fr= new FileReader(fileName);) {
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			String decrpted=PwdUtils.decrypt(entity.getPassword());
			mailBody = sb.toString();
			mailBody = mailBody.replace(AppConstants.FNAME, entity.getFname());
			mailBody = mailBody.replace(AppConstants.LNAME, entity.getLname());
			mailBody = mailBody.replace(AppConstants.TEMP_PZZWD, decrpted);
			mailBody = mailBody.replace(AppConstants.EMAIL, entity.getEmail());
			mailBody = mailBody.replace(AppConstants.URL, ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());

		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		return mailBody;
	}
}
