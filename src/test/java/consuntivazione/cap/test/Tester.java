package consuntivazione.cap.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import consuntivazione.cap.service.TimeSheetServiceImpl;

public class Tester {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ctx.getBean(TimeSheetServiceImpl.class);
	}

}
