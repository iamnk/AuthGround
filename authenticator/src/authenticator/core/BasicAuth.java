package authenticator.core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BasicAuth implements StarAuth
{
	public static final int ID = 0;

	@Override public int getId()
	{
		return ID;
	}

	@Override public boolean isAuthenticated()
	{
		return false;
	}

	@Override public boolean getCredentials()
	{
		return false;
	}

	/**
	 * Mandatory UserAUthData fields: email, password
	 * @param userAuthData
	 * @return
	 * @throws Exception
	 */
	@Override public  void authenticate(UserAuthData userAuthData) throws Exception
	{
		String passWordFromDB = "password";
		byte[] passwordFromRequest = hashPassword(userAuthData);
		boolean isEqual = MessageDigest.isEqual(passWordFromDB.getBytes(StandardCharsets.UTF_8), BCrypt.hashpw(passwordFromRequest, passWordFromDB).getBytes(StandardCharsets.UTF_8));

		if(!isEqual)
		{
			throw new Exception("password not matched");
		}

	}

	/**
	 * UserAuthData mandatory Fields: password, email, appSalt
	 *
	 * @param userAuthData
	 * @return
	 * @throws Exception
	 */
	@Override
	public void signUp(UserAuthData userAuthData) throws Exception
	{

		byte[] password = hashPassword(userAuthData);

	}

	/**
	 *  UserAuthData mandatory Fields: password, email, appSalt, userID
	 * @param userAuthData
	 * @throws Exception
	 */
	@Override public void resetPassword(UserAuthData userAuthData) throws Exception
	{
		byte[] newPassword = hashPassword(userAuthData);

		//Reset the password in db
	}

	/**
	 *  This method returns the hashed password by BCrypt implementation of Spring security.
	 *  Salt logic: email+ appsalt + unique salt generated for each user using Bcrypt.
	 *
	 * @param userAuthData
	 *
	 * @return
	 * @throws Exception
	 */

	private static byte[] hashPassword(UserAuthData userAuthData) throws Exception
	{
		if(userAuthData.appSalt == null || userAuthData.appSalt.isEmpty())
		{
			throw new Exception("salt is mandatory");
		}
		String baseSalt = BCrypt.gensalt(10, new SecureRandom());

		return BCrypt.hashpw(userAuthData.passWord.getPassword(), baseSalt+userAuthData.email+ userAuthData.appSalt).getBytes(StandardCharsets.UTF_8);
	}
}
