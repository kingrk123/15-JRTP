package in.nit.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nit.constants.AppConstants;
import in.nit.exception.UserAppException;

public class PwdUtils {
	

	private static Logger logger = LoggerFactory.getLogger(PwdUtils.class);
	
	private static final String SECRET_KEY = "abc123xyz123abcd";
	private static final String INIT_VECTOR = "abc123xyz123abcd";
	
	private PwdUtils() {
	}
	
	public static String encrypt(String plainText) throws UserAppException
	{
		String encodeToString = null;
		try {
			IvParameterSpec ivParamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

			SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"),"AES");

			Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5PADDING");

			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParamSpec);
			byte[] encryptedByte = cipher.doFinal(plainText.getBytes());
			encodeToString=Base64.getEncoder().encodeToString(encryptedByte);
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		return encodeToString;
	}

	public static String decrypt(String encryptedText) throws UserAppException
	{
		String decryptedMsg = null;
		try {
			IvParameterSpec ivParamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

			SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"),"AES");

			Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5PADDING");

			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,ivParamSpec);
			
			byte[] decodeMsg =Base64.getDecoder().decode(encryptedText);
			byte[] decryptedByte = cipher.doFinal(decodeMsg);
			decryptedMsg =new String(decryptedByte);
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
		return decryptedMsg;
}
}