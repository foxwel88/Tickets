package service;

import edu.nju.tickets.dao.PlaceDao;
import edu.nju.tickets.dao.ShowDao;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.SeatState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@ContextConfiguration("/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class PlaceTest {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private ShowDao showDao;

    @Test
    public void placeDaoTest1() {

        Show show = new Show("有趣的show", "很有趣很有趣", 1, new Date(), new SeatState(20));
        showDao.add(show);
    }

    @Test
    public void placeDaoTest2() {

        Show show = showDao.getById(5);
        show.getSeatState();
    }

}