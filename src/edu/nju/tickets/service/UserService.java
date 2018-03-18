package edu.nju.tickets.service;


import com.sun.org.apache.regexp.internal.RE;
import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;

public interface UserService {

	ResultMessage signUp(String userName, String emailAddress, String emailCheckString, String phoneNumber, String passWord);


	ResultMessage login(String userNameOrEmail, String passWord);

	/**
	 *
	 * @param emailAddress user's email.
	 * @return String: emailCheckString. null: fail to send email
	 */
	ResultMessage sendEmail(String emailAddress);

	/**
	 *
	 * @param emailAddress user's new email.
	 * @param emailCheckNumber emailCheckString.
	 * @return
	 */
	ResultMessage modifyEmail(String userName, String passWord, String emailAddress, String emailCheckNumber);

	ResultMessage modifyPassWord(String userName, String oldPassWord, String newPassWord);

	ResultMessage modify(User user);

	User getUser(String userName);

	ResultMessage delete(String userName, String passWord);

	ResultMessage addMoney(String userName, double money);

	ResultMessage addIntegral(String userName, int Integral);

	ResultMessage useIntegral(String userName, int Integral);

	ResultMessage cancel(String userName, String passWord);
}
