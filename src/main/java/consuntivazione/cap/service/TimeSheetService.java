package consuntivazione.cap.service;

import consuntivazione.cap.vo.OrderVO;
import consuntivazione.cap.vo.TimeSheetVO;
import consuntivazione.cap.vo.WorkerVO;

public interface TimeSheetService {
	public void newWorker(WorkerVO wVO) throws Exception;
	public void newOrder(OrderVO oVO) throws Exception;
	public void newTimeSheet(TimeSheetVO vo) throws Exception;
}
