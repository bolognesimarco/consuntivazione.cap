package consuntivazione.cap.service;

import java.util.List;

import consuntivazione.cap.vo.OrderVO;
import consuntivazione.cap.vo.ReportEntryVO;
import consuntivazione.cap.vo.TimeSheetVO;
import consuntivazione.cap.vo.WorkerVO;

public interface TimeSheetService {
	public void newWorker(WorkerVO wVO) throws Exception;
	public void newOrder(OrderVO oVO) throws Exception;
	public void newTimeSheet(TimeSheetVO vo) throws Exception;
	public List<ReportEntryVO> report(int workerId, int month, int year) throws Exception;
	public List<TimeSheetVO> suspended(int workerId, int month, int year) throws Exception;
	public List<OrderVO> leftOvers(int workerId, int month, int year) throws Exception;
	
	
	public void closeCtx() throws Exception;
}
