package consuntivazione.cap.test;

import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import consuntivazione.cap.service.TimeSheetService;
import consuntivazione.cap.vo.OrderVO;
import consuntivazione.cap.vo.WorkerVO;

public class Tester {

	public static void main(String[] args) throws Exception{
		Tester t = new Tester();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		t.tss = ctx.getBean(TimeSheetService.class);
		
		
		t.newOrder();
		
		
		System.exit(0);
	}
	
	TimeSheetService tss = null;
	
	public void newOrder() throws Exception{
		OrderVO vo = new OrderVO();
		vo.setLeftDays(0);
		vo.setPlacerProtocol("13/14");
		vo.setRate(310);
		vo.setReceiverProtocol("13/14");
		
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.MONTH, Calendar.JULY);
			cal.set(Calendar.YEAR, 2014);
		
		vo.setStartDate(cal.getTime());
		vo.setTotalDays(5.33);
		
		WorkerVO wVO = new WorkerVO();
		wVO.setId(2);
		
		vo.setWorker(wVO);
		
		tss.newOrder(vo);
		
	}
	
	public void newWorker() throws Exception{
		WorkerVO vo = new WorkerVO();
		vo.setName("Barigelli Massimiliano");
		tss.newWorker(vo);
		
	}

}
