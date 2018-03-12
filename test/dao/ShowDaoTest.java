package dao;

import edu.nju.tickets.dao.ShowDao;
import edu.nju.tickets.model.util.SeatState;
import edu.nju.tickets.model.Show;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class ShowDaoTest {

    @Autowired
    private ShowDao showDao;

    @Test
    public void showDaoTest1() {

        Show show = new Show("有趣的show", "很有趣很有趣", 1, new Date(), new SeatState(10));
        showDao.add(show);
    }

    @Test
    public void showDaoTest2() {

        Show show = showDao.getById(5);
    }

}