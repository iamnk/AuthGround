package authenticator.core;

public interface Auth
{
	int getId();

	boolean isAuthenticated();

	boolean getCredentials();

	/**
	 	Description: This method the heart of authentication process. This will try to authenticate the user
	 and throws error, if credentials don't match.
	 */
	void authenticate(UserAuthData userAuthData) throws Exception;

	/**
	 *  This method will be called after the successfull auth include authenticate, signUp, resetPassword.
	 *  This will delete the sensitive information in userAuthData and set the authSource
	 * @param userAuthData
	 */
	default void afterAuth(UserAuthData userAuthData)
	{
		userAuthData.userID = this.getId();
		userAuthData.flushSensitiveData();
	}
}
