package dao;

import edu.nju.tickets.dao.UserDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class UserDaoTest {


    @Autowired
    private UserDao userDao;


}