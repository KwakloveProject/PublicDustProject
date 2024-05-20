package publicDustProfect;

import java.sql.Date;

public class DustInfo 
{
   private int clearVal;
   private int sn;
   private String districtName;
   private Date dataDate;
   private int issueVal;
   private String moveName;
   private String issueGbn;
   private String itemCode;

   
   public DustInfo() 
   {
	super();
   }

public DustInfo(int clearVal, int sn, String districtName, Date dataDate, int issueVal, String moveName,
		String issueGbn, String itemCode) {
	super();
	this.clearVal = clearVal;
	this.sn = sn;
	this.districtName = districtName;
	this.dataDate = dataDate;
	this.issueVal = issueVal;
	this.moveName = moveName;
	this.issueGbn = issueGbn;
	this.itemCode = itemCode;
}

public int getClearVal() {
	return clearVal;
}

public void setClearVal(int clearVal) {
	this.clearVal = clearVal;
}

public int getSn() {
	return sn;
}

public void setSn(int sn) {
	this.sn = sn;
}

public String getDistrictName() {
	return districtName;
}

public void setDistrictName(String districtName) {
	this.districtName = districtName;
}

public Date getDataDate() {
	return dataDate;
}

public void setDataDate(Date dataDate) {
	this.dataDate = dataDate;
}

public int getIssueVal() {
	return issueVal;
}

public void setIssueVal(int issueVal) {
	this.issueVal = issueVal;
}

public String getMoveName() {
	return moveName;
}

public void setMoveName(String moveName) {
	this.moveName = moveName;
}

public String getIssueGbn() {
	return issueGbn;
}

public void setIssueGbn(String issueGbn) {
	this.issueGbn = issueGbn;
}

public String getItemCode() {
	return itemCode;
}

public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}

@Override
public String toString() {
	return "clearVal=" + clearVal + ", sn=" + sn + ", districtName=" + districtName + ", dataDate=" + dataDate
			+ ", issueVal=" + issueVal + ", moveName=" + moveName + ", issueGbn=" + issueGbn + ", itemCode=" + itemCode
			;
}
   
   


   

}
