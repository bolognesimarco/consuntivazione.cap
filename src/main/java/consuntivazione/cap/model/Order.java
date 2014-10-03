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

@Entity(name="CustomerOrder")
public class Order {

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String placerProtocol;
	
	@Column
	private String receiverProtocol;
	
	@Column
	private double totalDays;
	
	@Column
	private double leftDays;
	
	@Column(nullable=false)
	private Date startDate;
	
	@Column
	private double rate;
	
	@ManyToOne
	private Worker worker;
	
	@OneToMany(mappedBy="order")
	private List<ReportEntry> reportEntries = new ArrayList<ReportEntry>();

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

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public List<ReportEntry> getReportEntries() {
		return reportEntries;
	}

	public void setReportEntries(List<ReportEntry> reportEntries) {
		this.reportEntries = reportEntries;
	}

	
}
