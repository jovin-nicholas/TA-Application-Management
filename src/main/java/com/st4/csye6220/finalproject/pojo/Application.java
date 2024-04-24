package com.st4.csye6220.finalproject.pojo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicationId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	private String professorEmail;
	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;
	private String status;
//	private MultipartFile applicantResume;
	private Date submissionDate;
	private Date reviewDate;

	public Application() {

	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getProfessorEmail() {
		return professorEmail;
	}

	public void setProfessorEmail(String professorEmail) {
		this.professorEmail = professorEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
//	public MultipartFile getApplicantResume() {
//		return applicantResume;
//	}
//
//	public void setApplicantResume(MultipartFile applicantResume) {
//		this.applicantResume = applicantResume;
//	}

}
