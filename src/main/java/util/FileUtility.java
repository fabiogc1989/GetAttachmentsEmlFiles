/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabio Coimbra
 */
public class FileUtility {
    
    private static void getFilesRecursive(File directory, List<File> files){
        File[] list = directory.listFiles();
        for(File item : list){
            if(item.isFile() == true){
                files.add(item);
            } else{
                getFilesRecursive(item.getAbsoluteFile(), files);
            }
        }
    }
    
    public static List<File> getFiles(String folderPath){
        File directory = new File(folderPath);
        List<File> list = new ArrayList<>();
        getFilesRecursive(directory, list);
        return list;
    }
    
    public static boolean hasFile(String filePath) {
    	File file = new File(filePath);
    	return file.exists();
    }
    
    public static boolean hasFile(File file) {
    	return FileUtility.hasFile(file.getAbsolutePath());
    }
}
