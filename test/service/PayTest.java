package service;

import edu.nju.tickets.dao.PayDao;
import edu.nju.tickets.model.PayAccount;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class PayTest {

    @Autowired
    private PayService payService;

    @Autowired
    private PayDao payDao;


    @Test
    public void payServiceTest1() {
        ResultMessage resultMessage = payService.pay("13000000000", "123456", 20);
        assertEquals(resultMessage, ResultMessage.NO_PAY_ACCOUNT);
    }

    @Test
    public void payServiceTest2() {
        ResultMessage resultMessage = payService.pay("13900000000", "888888", 20);
        assertEquals(resultMessage, ResultMessage.WRONG_PAY_PASSWORD);
    }

    @Test
    public void payServiceTest3() {
        ResultMessage resultMessage = payService.pay("13900000000", "123456", 20);
        assertEquals(resultMessage, ResultMessage.NO_ENOUGH_MONEY);
    }

    @Test
    public void payServiceTest4() {
        PayAccount payAccount = payDao.getByAccountName("13988888888");
        double oldMoney = payAccount.getMoney();
        ResultMessage resultMessage = payService.pay("13988888888", "123456", 2);
        double newMoney = payDao.getByAccountName("13988888888").getMoney();

        assertEquals(resultMessage, ResultMessage.SUCCESS);
        assertEquals(newMoney, oldMoney - 2, 0.00000001);

    }

}