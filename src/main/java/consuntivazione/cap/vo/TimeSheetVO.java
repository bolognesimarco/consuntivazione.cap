package consuntivazione.cap.vo;

import java.util.Date;

import consuntivazione.cap.model.Worker;

public class TimeSheetVO {
	private int id;
	private Date endDate;
	private Worker worker;
	private double days;
	private double suspendedDays;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public double getDays() {
		return days;
	}
	public void setDays(double days) {
		this.days = days;
	}
	public double getSuspendedDays() {
		return suspendedDays;
	}
	public void setSuspendedDays(double suspendedDays) {
		this.suspendedDays = suspendedDays;
	}
	
	
}
