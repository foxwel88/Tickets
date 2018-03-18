package dao;

import edu.nju.tickets.dao.ManagerDao;
import edu.nju.tickets.dao.PayDao;
import edu.nju.tickets.model.util.ResultMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class PlaceAccountDaoTest {

    @Autowired
    private ManagerDao managerDao;

    @Test
    public void test() {
        managerDao.getPlaceCalc();
        managerDao.getUserCalc();
        managerDao.getTicketsCalc();
    }

}