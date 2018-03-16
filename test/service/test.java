package service;

import edu.nju.tickets.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class test
{
    @Autowired
    private UserService userService;

    @Test
    public void selectUserTest() {
        userService.login("root", "123456");
    }


}
