package edu.nju.tickets.service.impl;

import edu.nju.tickets.dao.UserDao;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.User;
import edu.nju.tickets.service.MailService;
import edu.nju.tickets.service.UserService;
import edu.nju.tickets.util.EmailUtil;
import edu.nju.tickets.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MailService mailService;

    @Override
    public ResultMessage signUp(String userName, String emailAddress, String emailCheckNumber, String phoneNumber, String passWord) {
        if (!EmailUtil.getEmailCheckNumber(emailAddress).equals(emailCheckNumber)) {
            return ResultMessage.WRONG_EMAIL_CHECK_NUMBER;
        }

        User user = userDao.getByUserName(userName);
        if (user != null) {
            return ResultMessage.USERNAME_EXIST;
        }

        user = new User(userName, passWord, emailAddress, phoneNumber);
        userDao.add(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage login(String userNameOrEmail, String passWord) {

        if ((userNameOrEmail == null) || (passWord == null)) {
            return ResultMessage.NO_USER;
        }

        User user = userDao.getByUserName(userNameOrEmail);

        if (user == null) {
            return ResultMessage.NO_USER;
        }

        if (user.getState().equals("false")) {
            return ResultMessage.USER_DELETED;
        }

        if (!user.getPassWord().equals(passWord)) {
            return ResultMessage.WRONG_PASSWORD;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage sendEmail(String emailAddress) {
        String emailCheckNumber = EmailUtil.getEmailCheckNumber(emailAddress);
        try {
            mailService.sendEmail(emailAddress, "Tickets系统注册验证邮件", "您的 Tickets 账户验证码为：" + emailCheckNumber);
        } catch (Exception e) {
            return ResultMessage.FAIL_SENDING_EMAIL;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyEmail(String userName, String passWord, String emailAddress, String emailCheckNumber) {
        ResultMessage resultMessage = login(userName, passWord);
        if (resultMessage != ResultMessage.SUCCESS) {
            return resultMessage;
        }

        if (!EmailUtil.getEmailCheckNumber(emailAddress).equals(emailCheckNumber)) {
            return ResultMessage.WRONG_EMAIL_CHECK_NUMBER;
        }

        User user = userDao.getByUserName(userName);
        user.setEmailAddress(emailAddress);
        userDao.update(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyPassWord(String userName, String oldPassWord, String newPassWord) {
        ResultMessage resultMessage = login(userName, oldPassWord);
        if (resultMessage != ResultMessage.SUCCESS) {
            return resultMessage;
        }

        User user = userDao.getByUserName(userName);
        user.setPassWord(newPassWord);
        userDao.update(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modify(User user) {
        return userDao.update(user);
    }

    @Override
    public User getUser(String userName) {
        return userDao.getByUserName(userName);
    }

    @Override
    public ResultMessage delete(String userName, String passWord) {
        ResultMessage resultMessage = login(userName, passWord);
        if (resultMessage != ResultMessage.SUCCESS) {
            return resultMessage;
        }

        User user = getUser(userName);
        user.setState("false");
        return userDao.update(user);
    }

    @Override
    public ResultMessage addMoney(String userName, double money) {
        User user = getUser(userName);
        user.setMoney(user.getMoney() + money);
        userDao.update(user);
        addIntegral(userName, ((int)money));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage addIntegral(String userName, int integral) {
        User user = getUser(userName);
        user.setIntegral(user.getIntegral() + integral);
        user.setLevel(LevelUtil.getLevel(user.getIntegral()));
        userDao.update(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage useIntegral(String userName, int integral) {
        User user = getUser(userName);
        user.setIntegral(user.getIntegral() - integral);
        userDao.update(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage cancel(String userName, String passWord) {
        ResultMessage resultMessage = login(userName, passWord);
        if (resultMessage != ResultMessage.SUCCESS) {
            return resultMessage;
        }
        User user = getUser(userName);
        user.setState("false");
        return modify(user);
    }


}
