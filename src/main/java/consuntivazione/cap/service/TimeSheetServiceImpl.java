package consuntivazione.cap.service;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import consuntivazione.cap.model.Order;
import consuntivazione.cap.model.ReportEntry;
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
		
		/*
		 * se ci sono order piu recenti sullo stesso worker e con leftdays<totaldays, si prendono 
		 * tutte le reportentry di quell ordine non ancora invoiced e si cancellano ripristinando i
		 * leftdays dei relativi order e timesheet. 
		 * 
		 * 
		 * Poi IN OGNI CASO si ricalcola tutto a partire dai timesheet
		 * con suspendedDays>0 del worker in ordine di data. per ciascuno si prendono gli order validi
		 * per data e con leftdays>0 e si comincia a creare le nuove reportentry scalando i giorni
		 * dall order e dal timesheet.
		 */
		
		String orders = "from CustomerOrder o where o.worker.id=:wid and o.leftDays<o.totalDays and o.startDate>:sdate";
		TypedQuery qO = em.createQuery(orders, Order.class);
		qO.setParameter("wid", w.getId());
		qO.setParameter("sdate", oE.getStartDate());
		List<Order> tsO = qO.getResultList();
		for (Order order : tsO) {
			List<ReportEntry> res = order.getReportEntries();
			//Iterator<ReportEntry> resI = res.iterator();
			//ReportEntry reportEntry = null;
			//while(resI.hasNext()){
				//reportEntry = resI.next();
			for (ReportEntry reportEntry : res) {
				if(reportEntry.getInvoice()==null){
					TimeSheet ts = reportEntry.getTimeSheet();
					order.setLeftDays(order.getLeftDays()+reportEntry.getDays());
					ts.setSuspendedDays(ts.getSuspendedDays()+reportEntry.getDays());
					em.remove(reportEntry);
				}
			}
		}
		
		updateWorkerReport(w.getId());
	}
	
	private void updateWorkerReport(int workerId) throws Exception{
		String toBeReported = "from TimeSheet ts where ts.worker.id=:wid and ts.suspendedDays>0 order by ts.endDate asc";
		TypedQuery q = em.createQuery(toBeReported, TimeSheet.class);
		q.setParameter("wid", workerId);
		List<TimeSheet> ts = q.getResultList();
		
		String orders = "from CustomerOrder o where o.worker.id=:wid and o.leftDays>0 order by o.startDate asc";
		TypedQuery qO = em.createQuery(orders, Order.class);
		qO.setParameter("wid", workerId);
		List<Order> tsO = qO.getResultList();
		
		for (TimeSheet timeSheet : ts) {
			for (Order order : tsO) {
				if(order.getLeftDays()>0){
					//if(order.getStartDate().before(timeSheet.getEndDate())){
						double min = order.getLeftDays()>timeSheet.getSuspendedDays()?timeSheet.getSuspendedDays():order.getLeftDays();
						order.setLeftDays(order.getLeftDays()-min);
						timeSheet.setSuspendedDays(timeSheet.getSuspendedDays()-min);
						
						ReportEntry re = new ReportEntry();
						re.setDays(min);
						re.setOrder(order);
						re.setTimeSheet(timeSheet);
						order.getReportEntries().add(re);
						timeSheet.getReportEntries().add(re);
						
						em.persist(re);
						
						if(timeSheet.getSuspendedDays()==0){
							break;
						}
					//}
				}
			}
		}
	}
	
	public void newTimeSheet(TimeSheetVO vo) throws Exception{
		
		
		TimeSheet ts = new TimeSheet();
		ts.setDays(vo.getDays());
		ts.setEndDate(vo.getEndDate());
		ts.setSuspendedDays(vo.getSuspendedDays());

		Worker w = new Worker();
		w.setId(vo.getWorker().getId());
		
		ts.setWorker(w);
		
		em.persist(ts);
		
		/*
		 * se ci sono timesheet piu recenti sullo stesso worker e con suspendeddays<totaldays, si prendono 
		 * tutte le reportentry di quel timesheet non ancora invoiced e si cancellano ripristinando i
		 * leftdays dei relativi order e timesheet. 
		 * 
		 * 
		 * Poi IN OGNI CASO si ricalcola tutto a partire dai timesheet
		 * con suspendedDays>0 del worker in ordine di data. per ciascuno si prendono gli order validi
		 * per data e con leftdays>0 e si comincia a creare le nuove reportentry scalando i giorni
		 * dall order e dal timesheet.
		 */
		
		String timeSheets = "from TimeSheet ts where ts.worker.id=:wid and ts.suspendedDays<ts.days and ts.endDate>:edate";
		TypedQuery qO = em.createQuery(timeSheets, TimeSheet.class);
		qO.setParameter("wid", w.getId());
		qO.setParameter("edate", ts.getEndDate());
		List<TimeSheet> tsO = qO.getResultList();
		for (TimeSheet timeSheet : tsO) {
			List<ReportEntry> res = timeSheet.getReportEntries();
			for (ReportEntry reportEntry : res) {
				if(reportEntry.getInvoice()==null){
					Order o = reportEntry.getOrder();
					timeSheet.getReportEntries().remove(reportEntry);
					timeSheet.setSuspendedDays(timeSheet.getSuspendedDays()+reportEntry.getDays());
					o.getReportEntries().remove(reportEntry);
					o.setLeftDays(o.getLeftDays()+reportEntry.getDays());
					em.remove(reportEntry);
				}
			}
		}
		
		updateWorkerReport(w.getId());
	}
	
	
	public void report(int workerId, int month, int year) throws Exception{
		String reportEntries = "from ReportEntry re where re.timeSheet.worker.id=:wid and re.timeSheet.endDate between :s and :e";
		TypedQuery tq = em.createQuery(reportEntries, ReportEntry.class);
		
		Calendar s = Calendar.getInstance();
		s.set(Calendar.MILLISECOND, 0);
		s.set(Calendar.SECOND, 0);
		s.set(Calendar.MINUTE, 0);
		s.set(Calendar.HOUR_OF_DAY, 0);
		s.set(Calendar.DAY_OF_MONTH, 1);
		s.set(Calendar.MONTH, month);
		s.set(Calendar.YEAR, year);
		
		Calendar e = Calendar.getInstance();
		e.set(Calendar.MILLISECOND, 0);
		e.set(Calendar.SECOND, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.DAY_OF_MONTH, 1);
		e.set(Calendar.MONTH, month);
		e.set(Calendar.YEAR, year);
		e.add(Calendar.MONTH, 1);
		e.add(Calendar.DAY_OF_YEAR, -1);
		
		tq.setParameter("wid", workerId);
		tq.setParameter("s", s.getTime());
		tq.setParameter("e", e.getTime());
		
		List<ReportEntry> entries = tq.getResultList();
		
		for (ReportEntry reportEntry : entries) {
			System.out.println(
					"ORDER: "+reportEntry.getOrder().getPlacerProtocol()+
					" DAYS: "+reportEntry.getDays());
		}
		
	}
	
	
}