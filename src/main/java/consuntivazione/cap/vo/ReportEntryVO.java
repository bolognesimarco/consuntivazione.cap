package consuntivazione.cap.vo;


public class ReportEntryVO {
	
	private int id;
	
	private InvoiceVO invoice;
	
	private TimeSheetVO timeSheet;
	
	private OrderVO order;
	
	private double days;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InvoiceVO getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceVO invoice) {
		this.invoice = invoice;
	}

	public TimeSheetVO getTimeSheet() {
		return timeSheet;
	}

	public void setTimeSheet(TimeSheetVO timeSheet) {
		this.timeSheet = timeSheet;
	}

	public OrderVO getOrder() {
		return order;
	}

	public void setOrder(OrderVO order) {
		this.order = order;
	}

	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	
	
}
