package com.oopsw.model.VO;

import java.sql.Date;

public class AbsenceVO {
	private int absenceDateNo;
    private String absenteeId;
    private String proxyId;
    private Date absenceStartDate;
    private Date absenceEndDate;
    private String absenceReason;
    private String absenceUsage;
    private Date notiInDate;
    private Date absenceReadStatus;
    
    public AbsenceVO(){};
    
    
	public AbsenceVO(int absenceDateNo, String absenteeId, String proxyId, Date absenceStartDate, Date absenceEndDate,
			String absenceReason, String absenceUsage, Date notiInDate, Date absenceReadStatus) {
		this.absenceDateNo = absenceDateNo;
		this.absenteeId = absenteeId;
		this.proxyId = proxyId;
		this.absenceStartDate = absenceStartDate;
		this.absenceEndDate = absenceEndDate;
		this.absenceReason = absenceReason;
		this.absenceUsage = absenceUsage;
		this.notiInDate = notiInDate;
		this.absenceReadStatus = absenceReadStatus;
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
	public Date getNotiInDate() {
		return notiInDate;
	}
	public void setNotiInDate(Date notiInDate) {
		this.notiInDate = notiInDate;
	}
	public Date getAbsenceReadStatus() {
		return absenceReadStatus;
	}
	public void setAbsenceReadStatus(Date absenceReadStatus) {
		this.absenceReadStatus = absenceReadStatus;
	}


	@Override
	public String toString() {
		return "AbsenceVO [absenceDateNo=" + absenceDateNo + ", absenteeId=" + absenteeId + ", proxyId=" + proxyId
				+ ", absenceStartDate=" + absenceStartDate + ", absenceEndDate=" + absenceEndDate + ", absenceReason="
				+ absenceReason + ", absenceUsage=" + absenceUsage + ", notiInDate=" + notiInDate
				+ ", absenceReadStatus=" + absenceReadStatus + "]\n";
	}
    
    

}
