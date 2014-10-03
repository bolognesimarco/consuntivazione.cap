package consuntivazione.cap.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import consuntivazione.cap.model.Order;
import consuntivazione.cap.model.TimeSheet;
import consuntivazione.cap.model.Worker;
import consuntivazione.cap.vo.OrderVO;
import consuntivazione.cap.vo.TimeSheetVO;
import consuntivazione.cap.vo.WorkerVO;

@Service
@Transactional
public class TimeSheetServiceImpl implements TimeSheetService{
	
	@PersistenceContext
	private EntityManager em;
	
	public void newWorker(WorkerVO wVO) throws Exception{
		Worker wE = new Worker();
		wE.setName(wVO.getName());
		
		em.persist(wE);
	}
	
	public void newOrder(OrderVO oVO) throws Exception{
		Order oE = new Order();
		
	
		/*
		 * se ci sono order piu recenti sullo stesso worker e con leftdays<totaldays, si prendono 
		 * tutte le reportentry di quell ordine non ancora invoiced e si cancellano ripristinando i
		 * leftdays dei relativi order e timesheet. 
		 * 
		 * 
		 * Poi IN OGNI CASO si ricalcola tutto a partire dai timesheet
		 * con leftdays>0 del worker in ordine di data. per ciascuno si prendono gli order validi
		 * per data e con leftdays>0 e si comincia a creare le nuove reportentry scalando i giorni
		 * dall order e dal timesheet.
		 */
		
		
		oE.setLeftDays(oVO.getLeftDays());
		oE.setPlacerProtocol(oVO.getPlacerProtocol());
		oE.setRate(oVO.getRate());
		oE.setReceiverProtocol(oVO.getReceiverProtocol());
		oE.setStartDate(oVO.getStartDate());
		oE.setTotalDays(oVO.getTotalDays());
		
		Worker w = new Worker();
		w.setId(oVO.getWorker().getId());
		
		oE.setWorker(w);
		
		em.persist(oE);
	}
	
	public void newTimeSheet(TimeSheetVO vo) throws Exception{
		/*
		 * se ci sono timesheet piu recenti sullo stesso worker e con leftdays<totaldays, si prendono 
		 * tutte le reportentry di quell timesheet non ancora invoiced e si cancellano ripristinando i
		 * leftdays dei relativi order e timesheet. 
		 * 
		 * 
		 * Poi IN OGNI CASO si ricalcola tutto a partire dai timesheet
		 * con leftdays>0 del worker in ordine di data. per ciascuno si prendono gli order validi
		 * per data e con leftdays>0 e si comincia a creare le nuove reportentry scalando i giorni
		 * dall order e dal timesheet.
		 */
		
		TimeSheet ts = new TimeSheet();
		ts.setDays(vo.getDays());
		ts.setEndDate(vo.getEndDate());
		ts.setSuspendedDays(vo.getSuspendedDays());

		Worker w = new Worker();
		w.setId(vo.getWorker().getId());
		
		ts.setWorker(w);
		
		
		em.persist(ts);
		
		
	}
}
