package consuntivazione.cap.test;

import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import consuntivazione.cap.service.TimeSheetService;
import consuntivazione.cap.vo.OrderVO;
import consuntivazione.cap.vo.TimeSheetVO;
import consuntivazione.cap.vo.WorkerVO;

public class Tester {

	public static void main(String[] args) throws Exception{
		Tester t = new Tester();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		t.tss = ctx.getBean(TimeSheetService.class);
		
		
		//t.newOrder();
		//t.newWorker();
		//t.newTimeSheet();
		t.init();
		
		System.exit(0);
	}
	
	TimeSheetService tss = null;
	
	
	
	public void init() throws Exception{
		
		
		
		
		/*
		newOrder(BARIGELLI, 5.33, "13/14", 310, Calendar.JULY, 1);
		
		newOrder(BRUNI, 5.21, "15/14", 245, Calendar.JULY, 1);
		newOrder(GNIGNERA, 2.38, "16/14", 245, Calendar.JULY, 1);
		newOrder(BRUNI, 40, "45/14", 245, Calendar.JULY, 1);
		newOrder(GNIGNERA, 40, "46/14", 245, Calendar.JULY, 1);
		
		newTimeSheet(BRUNI, 22.5, Calendar.JULY, 31);
		newTimeSheet(BOLOGNESI, 12.5, Calendar.JULY, 31);
		newTimeSheet(BARIGELLI, 19.63, Calendar.JULY, 31);
		newTimeSheet(GNIGNERA, 20.5, Calendar.JULY, 31);
		newTimeSheet(BOLOGNESI, 11, Calendar.AUGUST, 31);
		newTimeSheet(BRUNI, 17.63, Calendar.AUGUST, 31);
		newTimeSheet(BARIGELLI, 6, Calendar.AUGUST, 31);
		newTimeSheet(GNIGNERA, 1, Calendar.AUGUST, 31);
		
		newOrder(BRUNI, 50, "60/14", 245, Calendar.SEPTEMBER, 1);
		newOrder(GNIGNERA, 50, "61/14", 245, Calendar.SEPTEMBER, 1);
		newOrder(BARIGELLI, 90, "62/14", 310, Calendar.SEPTEMBER, 1);
		newOrder(BOLOGNESI, 90, "63/14", 310, Calendar.SEPTEMBER, 1);
		
		newTimeSheet(BOLOGNESI, 22, Calendar.SEPTEMBER, 30);
		newTimeSheet(BRUNI, 12, Calendar.SEPTEMBER, 30);
		newTimeSheet(BARIGELLI, 20, Calendar.SEPTEMBER, 30);
		newTimeSheet(GNIGNERA, 21, Calendar.SEPTEMBER, 30);
		*/
		
		newOrder(BOLOGNESI, 7, "14/14", 310, Calendar.JULY, 1);
	}
	
	
	public void newOrder(int workerid, double days, String protocol, double rate, int month, int day) throws Exception{
		OrderVO vo = new OrderVO();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.DAY_OF_MONTH, day);	
			cal.set(Calendar.YEAR, 2014);
		WorkerVO wVO = new WorkerVO();
		
		
		wVO.setId(workerid);
		vo.setLeftDays(days);
		vo.setPlacerProtocol(protocol);
		vo.setRate(rate);
		cal.set(Calendar.MONTH, month);
		
		
		vo.setReceiverProtocol(vo.getPlacerProtocol());
		vo.setStartDate(cal.getTime());
		vo.setTotalDays(vo.getLeftDays());
		vo.setWorker(wVO);
		tss.newOrder(vo);
		
	}
	
	public void newTimeSheet(int workerid, double days, int month, int day) throws Exception{
		TimeSheetVO vo = new TimeSheetVO();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.YEAR, 2014);
		WorkerVO wVO = new WorkerVO();
		
		
		
		vo.setDays(days);
		wVO.setId(workerid);
		cal.set(Calendar.MONTH, month);
		
		
		vo.setWorker(wVO);
		vo.setEndDate(cal.getTime());
		vo.setSuspendedDays(vo.getDays());
		tss.newTimeSheet(vo);
	}
	
	private final int BOLOGNESI = 1;
	private final int BARIGELLI = 2;
	private final int BRUNI = 3;
	private final int GNIGNERA = 4;
	
	
	public void newWorker() throws Exception{
		WorkerVO vo = new WorkerVO();
		vo.setName("Gnignera Luca");
		tss.newWorker(vo);
		
	}

}
