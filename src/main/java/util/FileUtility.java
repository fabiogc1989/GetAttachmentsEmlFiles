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
    
    private static List<File> getFilesRecursive(File directory, List<File> files){
        File[] list = directory.listFiles();
        for(File item : list){
            if(item.isFile() == true){
                files.add(item);
            } else{
                getFilesRecursive(item.getAbsoluteFile(), files);
            }
        }
        return files;
    }
    
    public static List<File> getFiles(String folderPath){
        File directory = new File(folderPath);
        List<File> list = new ArrayList<>();
        return getFilesRecursive(directory, list);
    }
}
