package service;

import edu.nju.tickets.dao.UserDao;
import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    public void a_signUpTest1() {
        ResultMessage resultMessage = userService.signUp("testUser", "cttony1997@126.com",
                "338624", "13233333333", "888888");
        assertEquals(resultMessage, ResultMessage.SUCCESS);
    }

    @Test
    public void b_signUpTest2() {
        ResultMessage resultMessage = userService.signUp("testUser", "cttony1997@126.com",
                "000000", "13233333333", "888888");
        assertEquals(resultMessage, ResultMessage.WRONG_EMAIL_CHECK_NUMBER);
    }

    @Test
    public void c_signUpTest3() {
        ResultMessage resultMessage = userService.signUp("testUser", "cttony1997@126.com",
                "338624", "13233333333", "888888");
        assertEquals(resultMessage, ResultMessage.USERNAME_EXIST);
    }

    @Test
    public void d_signUpTest4() {
        User user = userService.getUser("testUser");
        assertEquals("cttony1997@126.com", user.getEmailAddress());
        assertEquals("13233333333", user.getPhone());
        assertEquals("888888", user.getPassWord());
        assertEquals("true", user.getState());
        assertEquals(0, user.getMoney(), 0.000001);
        assertEquals(0, user.getIntegral());
        assertEquals(1, user.getLevel());
    }

    @Test
    public void e_loginTest1() {
        ResultMessage resultMessage = userService.login("testUser", "888888");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void f_loginTest2() {
        ResultMessage resultMessage = userService.login("jijijijijijiji", "888888");
        assertEquals(ResultMessage.NO_USER, resultMessage);
    }

    @Test
    public void g_loginTest3() {
        ResultMessage resultMessage = userService.login("", "888888");
        assertEquals(ResultMessage.NO_USER, resultMessage);
    }

    @Test
    public void h_loginTest4() {
        ResultMessage resultMessage = userService.login(null, "888888");
        assertEquals(ResultMessage.NO_USER, resultMessage);
    }

    @Test
    public void i_loginTest5() {
        ResultMessage resultMessage = userService.login("testUser", "123456");
        assertEquals(ResultMessage.WRONG_PASSWORD, resultMessage);
    }

    @Test
    public void j_modifyEmailTest1() {
        ResultMessage resultMessage = userService.modifyEmail("testUser", "888888",
                "cttony@126.com", "484992");
        assertEquals(resultMessage, ResultMessage.SUCCESS);
        User user = userService.getUser("testUser");
        assertEquals("cttony@126.com", user.getEmailAddress());
    }

    @Test
    public void k_modifyEmailTest2() {
        ResultMessage resultMessage = userService.modifyEmail("testUser", "888888",
                "cttony1997@126.com", "111111");
        assertEquals(resultMessage, ResultMessage.WRONG_EMAIL_CHECK_NUMBER);
        User user = userService.getUser("testUser");
        assertEquals("cttony@126.com", user.getEmailAddress());
    }

    @Test
    public void l_modifyEmailTest3() {
        ResultMessage resultMessage = userService.modifyEmail("testUser", "9898989",
                "cttony1997@126.com", "484992");
        assertEquals(resultMessage, ResultMessage.WRONG_PASSWORD);
        User user = userService.getUser("testUser");
        assertEquals("cttony@126.com", user.getEmailAddress());
    }

    @Test
    public void m_modifyPassWordTest1() {
        ResultMessage resultMessage = userService.modifyPassWord("testUser", "888888",
                "12345678");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        assertEquals(ResultMessage.SUCCESS, userService.login("testUser", "12345678"));
    }

    @Test
    public void n_modifyPassWordTest2() {
        ResultMessage resultMessage = userService.modifyPassWord("testUser", "9999999",
                "222222");
        assertEquals(ResultMessage.WRONG_PASSWORD, resultMessage);
        assertEquals(ResultMessage.SUCCESS, userService.login("testUser", "12345678"));
    }

    @Test
    public void o_modifyPassWordTest3() {
        ResultMessage resultMessage = userService.modifyPassWord("sssss", "888888",
                "222222");
        assertEquals(ResultMessage.NO_USER, resultMessage);
        assertEquals(ResultMessage.SUCCESS, userService.login("testUser", "12345678"));
    }

    @Test
    public void p_deleteTest1() {
        ResultMessage resultMessage = userService.delete("testUser", "12345678");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        assertEquals(ResultMessage.USER_DELETED, userService.login("testUser", "888888"));
        assertEquals("false", userService.getUser("testUser").getState());
    }

    @Test
    public void q_addMoneyTest1() {
        double oldMoney = userService.getUser("testUser").getMoney();
        int oldIntegral = userService.getUser("testUser").getIntegral();
        ResultMessage resultMessage = userService.addMoney("testUser", 98.2);

        assertEquals(ResultMessage.SUCCESS, resultMessage);
        assertEquals(oldMoney + 98.2, userService.getUser("testUser").getMoney(), 0.0000001);
        assertEquals(oldIntegral + 98, userService.getUser("testUser").getIntegral());
    }

    @Test
    public void r_addIntegralTest1() {
        int oldIntegral = userService.getUser("testUser").getIntegral();
        ResultMessage resultMessage = userService.addIntegral("testUser", 20);

        assertEquals(ResultMessage.SUCCESS, resultMessage);
        assertEquals(oldIntegral + 20, userService.getUser("testUser").getIntegral());
    }

    @Test
    public void s_useIntegralTest1() {
        int oldIntegral = userService.getUser("testUser").getIntegral();
        ResultMessage resultMessage = userService.useIntegral("testUser", 10);

        assertEquals(ResultMessage.SUCCESS, resultMessage);
        assertEquals(oldIntegral - 10, userService.getUser("testUser").getIntegral());
    }

    @Test
    public void z_deleteUser() {
        userDao.delete(userDao.getByUserName("testUser"));
    }
}