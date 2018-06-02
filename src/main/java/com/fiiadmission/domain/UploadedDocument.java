package com.fiiadmission.domain;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "uploaded_document")
public class UploadedDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "mime_type")
	private String mimeType;
	
	@Column(name = "file_name")
	private String filename;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "upload_date")
	private Timestamp uploadDate;
		
	@Lob
	@Column(name = "content")
	private byte[] content;

	@Column(name = "document_type")
	private String documentType;

	@ManyToOne
	@JoinColumn(name = "admission_data")
	private AdmissionData admissionData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public AdmissionData getAdmissionData() {
		return admissionData;
	}

	public void setAdmissionData(AdmissionData admissionData) {
		this.admissionData = admissionData;
	}
}