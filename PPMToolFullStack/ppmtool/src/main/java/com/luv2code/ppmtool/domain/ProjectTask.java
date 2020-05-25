package com.luv2code.ppmtool.domain;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProjectTask {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long  id;
	
	@Column(updatable =false,unique=true)
	private String projectSequence;
	@NotBlank(message="Please include a project summary")
	private String summary;
	
	@NotBlank(message="Please include the Acceptance Criteria")
	private String acceptanceCriteria;
	private String status;
	private Integer priority;
	
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date dueDate;
	
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date create_At;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updated_At;
	
	@Column(updatable =false)
	private String projectIdentifier;
	
	/*ManyToOne Relation with  the BackLog and the cascade that I 
	am using in this conxtext is that, whenever i delete a task from
	the backlog, and I refresh the backloog, that Same task ShouldAccept not Longer exist*/
	@ManyToOne(fetch=FetchType.EAGER) //REMOVE THE REFRESH HERE, IT WILL BE TAKEN CARE OF BY THE  OWNING SIDE(BACKLOG)
    @JoinColumn(name="backlog_id", nullable=false, updatable=false)
	@JsonIgnore
    private Backlog backlog;
	
	public ProjectTask() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.create_At = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectSequence() {
		return projectSequence;
	}

	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}

	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreate_At() {
		return create_At;
	}

	public void setCreate_At(Date create_At) {
		this.create_At = create_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	
	
	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	@Override
	public String toString() {
		return "ProjectTask [id=" + id + ", projectSequence=" + projectSequence + ", summary=" + summary
				+ ", acceptanceCriteria=" + acceptanceCriteria + ", status=" + status + ", priority=" + priority
				+ ", dueDate=" + dueDate + ", create_At=" + create_At + ", updated_At=" + updated_At
				+ ", projectIdentifier=" + projectIdentifier + "]";
	}
	
	
	
	
}
