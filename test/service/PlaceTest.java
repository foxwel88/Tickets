package service;

import edu.nju.tickets.dao.PlaceDao;
import edu.nju.tickets.dao.ShowDao;
import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.SeatInfo;
import edu.nju.tickets.model.util.SeatState;
import edu.nju.tickets.service.PlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class PlaceTest {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ShowDao showDao;

    @Test
    public void test() {
        placeService.checkPlaceModifyRequestByPrePlaceId(1);
    }

    @Test
    public void placeDaoTest1() {
        List<String> nameList = new ArrayList<>();
        nameList.add("北一区");
        nameList.add("北二区");
        nameList.add("南一区");
        nameList.add("南二区");
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(10);
        list1.add(12);
        list1.add(12);
        list1.add(14);
        list1.add(14); //72
        List<Integer> list2 = new ArrayList<>();
        list2.add(10);
        list2.add(10);
        list2.add(10);
        list2.add(10);
        list2.add(10);
        list2.add(12); // 62
        List<Integer> list3 = new ArrayList<>();
        list3.add(5);
        list3.add(6);
        list3.add(7);
        list3.add(8);
        list3.add(9);
        list3.add(10); //45
        List<Integer> list4 = new ArrayList<>();
        list4.add(10);
        list4.add(10);
        list4.add(12);
        list4.add(12);
        list4.add(14);
        list4.add(14); //72

        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        SeatInfo seatInfo = new SeatInfo(nameList, list);

        Place place = new Place("123456", "奥林匹克体育场", "南京市建邺区奥体中心","南京市建邺区奥体中心的奥林匹克体育场", seatInfo, "true");

        int placeId = placeDao.add(place);

        List<Double> priceList = new ArrayList<>();
        priceList.add(80.0);
        priceList.add(100.0);
        priceList.add(120.0);
        priceList.add(200.0);
        Show show = new Show("有趣的演唱会", "南京市建邺区奥体中心很有趣很有趣南京市建邺区奥体中心的奥林匹克体育场", placeId, new Date(), new SeatState(place.getSeatInfo().getTotalSeatNum(), priceList));
        showDao.add(show);


    }

    @Test
    public void placeDaoTest2() {

        Place place = placeDao.getById(3);
        System.out.println(place.getSeatInfo().getSeatName(71));
    }

}