package com.fiiadmission.service;

import com.fiiadmission.domain.UploadedDocument;

public interface UploadedDocumentService {

    UploadedDocument create(UploadedDocument uploadedDocument);

    UploadedDocument findById(Long id);
}
