package consuntivazione.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ReportEntry {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Invoice invoice;
	
	@ManyToOne
	private TimeSheet timeSheet;
	
	@ManyToOne
	private Order order;
	
	@Column
	private double days;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public TimeSheet getTimeSheet() {
		return timeSheet;
	}

	public void setTimeSheet(TimeSheet timeSheet) {
		this.timeSheet = timeSheet;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}
	
	
}
