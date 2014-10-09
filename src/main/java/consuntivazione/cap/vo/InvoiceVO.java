package consuntivazione.cap.vo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InvoiceVO {

	private int id;

	private Date date;

	private List<ReportEntryVO> entries = new ArrayList<ReportEntryVO>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ReportEntryVO> getEntries() {
		return entries;
	}

	public void setEntries(List<ReportEntryVO> entries) {
		this.entries = entries;
	}

}
