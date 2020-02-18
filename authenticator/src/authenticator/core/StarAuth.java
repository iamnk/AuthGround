package authenticator.core;

public interface StarAuth extends Auth
{
	public void signUp(UserAuthData userAuthData) throws Exception;

	public void resetPassword(UserAuthData userAuthData) throws Exception;

}
