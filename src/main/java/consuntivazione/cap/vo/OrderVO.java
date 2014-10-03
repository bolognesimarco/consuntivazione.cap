package consuntivazione.cap.vo;

import java.util.Date;


public class OrderVO {

	private int id;
	private String placerProtocol;
	private String receiverProtocol;
	private double totalDays;
	private double leftDays;
	private Date startDate;
	private double rate;
	private WorkerVO worker;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlacerProtocol() {
		return placerProtocol;
	}

	public void setPlacerProtocol(String placerProtocol) {
		this.placerProtocol = placerProtocol;
	}

	public String getReceiverProtocol() {
		return receiverProtocol;
	}

	public void setReceiverProtocol(String receiverProtocol) {
		this.receiverProtocol = receiverProtocol;
	}

	public double getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(double totalDays) {
		this.totalDays = totalDays;
	}

	public double getLeftDays() {
		return leftDays;
	}

	public void setLeftDays(double leftDays) {
		this.leftDays = leftDays;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public WorkerVO getWorker() {
		return worker;
	}

	public void setWorker(WorkerVO worker) {
		this.worker = worker;
	}
	
	
}
