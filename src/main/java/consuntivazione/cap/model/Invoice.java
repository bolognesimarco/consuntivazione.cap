package consuntivazione.cap.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Invoice {
	
	//su develop
	//su feature1
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private Date date;
	
	@OneToMany(mappedBy="invoice")
	private List<ReportEntry> entries = new ArrayList<ReportEntry>();

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

	public List<ReportEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<ReportEntry> entries) {
		this.entries = entries;
	}


	
}
