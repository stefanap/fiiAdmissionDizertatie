package com.elasticemail.app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileData {
	/**
	 * File content
	 */
    public byte[] content;
    
    /**
     * MIME content type, optional for uploads
     */
    public String contentType;

    /**
     * Name of the file this class contains
     */
    public String fileName;
    
    /**
     * Reads a file to this class instance
     * @param pathWithFileName Path string including file name
     */
    public void ReadFrom(String pathWithFileName) throws Exception
    {
    	Path path = Paths.get(pathWithFileName);
        content = Files.readAllBytes(path);
        fileName = path.getFileName().toString(); 
        contentType = null;
    }

    /**
     * Creates a new FileData instance from a file
     * @param pathWithFileName Path string including file name
     * @return
     */
    public static FileData CreateFromFile(String pathWithFileName) throws Exception
    {
        FileData fileData = new FileData();
        fileData.ReadFrom(pathWithFileName);
        return fileData;
    }
}