package com.fiiadmission.service.impl;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.UploadedDocument;
import com.fiiadmission.repository.UploadedDocumentRepository;
import com.fiiadmission.service.UploadedDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadedDocumentServiceImpl implements UploadedDocumentService {

    @Autowired
    UploadedDocumentRepository uploadedDocumentRepository;

    @Override
    public UploadedDocument create(UploadedDocument uploadedDocument) {
        return uploadedDocumentRepository.save(uploadedDocument);
    }

    @Override
    public UploadedDocument findById(Long id) {
        return uploadedDocumentRepository.findOne(id);
    }

    @Override
    public List<UploadedDocument> findByAdmissionData(AdmissionData admissionData) {
        return uploadedDocumentRepository.findByAdmissionData(admissionData);
    }
}
