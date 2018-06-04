package com.fiiadmission.service;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.UploadedDocument;

import java.util.List;

public interface UploadedDocumentService {

    UploadedDocument create(UploadedDocument uploadedDocument);

    UploadedDocument findById(Long id);

    List<UploadedDocument> findByAdmissionData(AdmissionData admissionData);
}
