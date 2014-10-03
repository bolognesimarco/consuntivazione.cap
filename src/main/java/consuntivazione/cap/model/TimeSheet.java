package consuntivazione.cap.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TimeSheet {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private Date endDate;
	
	@ManyToOne
	private Worker worker;
	
	@Column
	private double days;
	
	@Column
	private double suspendedDays;
	
	@OneToMany(mappedBy="timeSheet")
	private List<ReportEntry> reportEntries = new ArrayList<ReportEntry>();

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

	public double getSuspendedDays() {
		return suspendedDays;
	}

	public void setSuspendedDays(double suspendedDays) {
		this.suspendedDays = suspendedDays;
	}

	public List<ReportEntry> getReportEntries() {
		return reportEntries;
	}

	public void setReportEntries(List<ReportEntry> reportEntries) {
		this.reportEntries = reportEntries;
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


	
	
}
