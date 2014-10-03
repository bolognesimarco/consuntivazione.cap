package consuntivazione.cap.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import consuntivazione.cap.model.Worker;
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
}
