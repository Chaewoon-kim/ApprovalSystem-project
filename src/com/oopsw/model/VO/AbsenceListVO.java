package com.oopsw.model.VO;

import java.sql.Date;

public class AbsenceListVO {
	private int absenceDateNo;
    private String absenteeId;
    private String proxyId;
    private Date absenceStartDate;
    private Date absenceEndDate;
    private String absenceReason;
    private String absenceUsage;
    
    private String proxyName;
    private String proxyRank;
    private String absenteeName;
    private String absenteeRank;
    
    public AbsenceListVO(){};
    
    
    
	public AbsenceListVO(int absenceDateNo, String absenteeId, String proxyId, Date absenceStartDate,
			Date absenceEndDate, String absenceReason, String absenceUsage,
			String proxyName, String proxyRank, String absenteeName, String absenteeRank) {
		this.absenceDateNo = absenceDateNo;
		this.absenteeId = absenteeId;
		this.proxyId = proxyId;
		this.absenceStartDate = absenceStartDate;
		this.absenceEndDate = absenceEndDate;
		this.absenceReason = absenceReason;
		this.absenceUsage = absenceUsage;
		this.proxyName = proxyName;
		this.proxyRank = proxyRank;
		this.absenteeName = absenteeName;
		this.absenteeRank = absenteeRank;
	}



	public int getAbsenceDateNo() {
		return absenceDateNo;
	}
	public void setAbsenceDateNo(int absenceDateNo) {
		this.absenceDateNo = absenceDateNo;
	}
	public String getAbsenteeId() {
		return absenteeId;
	}
	public void setAbsenteeId(String absenteeId) {
		this.absenteeId = absenteeId;
	}
	public String getProxyId() {
		return proxyId;
	}
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	public Date getAbsenceStartDate() {
		return absenceStartDate;
	}
	public void setAbsenceStartDate(Date absenceStartDate) {
		this.absenceStartDate = absenceStartDate;
	}
	public Date getAbsenceEndDate() {
		return absenceEndDate;
	}
	public void setAbsenceEndDate(Date absenceEndDate) {
		this.absenceEndDate = absenceEndDate;
	}
	public String getAbsenceReason() {
		return absenceReason;
	}
	public void setAbsenceReason(String absenceReason) {
		this.absenceReason = absenceReason;
	}
	public String getAbsenceUsage() {
		return absenceUsage;
	}
	public void setAbsenceUsage(String absenceUsage) {
		this.absenceUsage = absenceUsage;
	}

	public String getProxyName() {
		return proxyName;
	}
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}
	public String getProxyRank() {
		return proxyRank;
	}
	public void setProxyRank(String proxyRank) {
		this.proxyRank = proxyRank;
	}
	public String getAbsenteeName() {
		return absenteeName;
	}
	public void setAbsenteeName(String absenteeName) {
		this.absenteeName = absenteeName;
	}
	public String getAbsenteeRank() {
		return absenteeRank;
	}
	public void setAbsenteeRank(String absenteeRank) {
		this.absenteeRank = absenteeRank;
	}



	@Override
	public String toString() {
		return "AbsenceListVO [absenceDateNo=" + absenceDateNo + ", absenteeId=" + absenteeId + ", proxyId=" + proxyId
				+ ", absenceStartDate=" + absenceStartDate + ", absenceEndDate=" + absenceEndDate + ", absenceReason="
				+ absenceReason + ", absenceUsage=" + absenceUsage + ", proxyName=" + proxyName + ", proxyRank="
				+ proxyRank + ", absenteeName=" + absenteeName + ", absenteeRank=" + absenteeRank + "]\n";
	}
    
    
    
    
    
    

}
