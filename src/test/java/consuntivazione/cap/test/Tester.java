package consuntivazione.cap.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import consuntivazione.cap.service.TimeSheetService;
import consuntivazione.cap.vo.WorkerVO;

public class Tester {

	public static void main(String[] args) throws Exception{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		TimeSheetService tss = ctx.getBean(TimeSheetService.class);
		WorkerVO vo = new WorkerVO();
		vo.setName("Bolognesi Marco");
		tss.newWorker(vo);
		System.exit(0);
	}

}
