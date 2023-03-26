package org.example;


import org.testng.annotations.Test;


import java.io.*;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Test
public class FilesSystem {
    FilesData fData = new FilesData();

    @Test
    public void createFileBorder() throws Exception {
        // Creating files for testing file`s size only in empty folder
        if (isDirEmpty(Paths.get(fData.getPathFileForCreation())) == true) {
            RandomAccessFile fZero = new RandomAccessFile(fData.getPathFileForCreation() + "Zero_" + fData.getFileName(), "rw");
            RandomAccessFile fOne = new RandomAccessFile(fData.getPathFileForCreation() + "One_" + fData.getFileName(), "rw");
            RandomAccessFile fOnePointNine = new RandomAccessFile(fData.getPathFileForCreation() + "OnePointNine_" + fData.getFileName(), "rw");
            RandomAccessFile fTwo = new RandomAccessFile(fData.getPathFileForCreation() + "Two_" + fData.getFileName(), "rw");
            fZero.setLength(fData.getZeroByte());
            fOne.setLength(fData.getOneByte());
            fOnePointNine.setLength(fData.getOnePointNineGB());
            fTwo.setLength(fData.getTwoGB() * 1024 * 1024 * 1024);
        }
        else {
            System.out.println("Directory is already filled by some files");
            return;
        }
    }
    public List<String> listOfFiles(String pathFiles) {
        // List of Files inside directory
        return Stream.of(new File(pathFiles).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private static boolean isDirEmpty(final Path directory) throws IOException {
        // Is directory is empty
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }
}
