/** EXERCISE:
   In this exercise you will write a Java application that concatenates several Java files into one file.

   Create a class called JavaFileUtil that implements ConcatenateJavaFiles (package uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.io).
   Implement the concatenateJavaFiles(String dirName, String fileName) method in the following way. Find all non-directory
   files contained in directory dirName whose name ends in ".java" and concatenate them in to a single file called fileName
   stored in dirName. Do not delete any files and please backup any of your own files before testing your solution.

   If the first given argument is not a directory, throw an IllegalArgumentException with a suitable error message.*/

import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.io.ConcatenateJavaFiles;
import java.io.*;

public class JavaFileUtil implements ConcatenateJavaFiles {

    @Override
    public void concatenateJavaFiles(String dirName, String fileName) throws IOException {

        File[] javaFiles = finder(dirName);

        File outputFile = new File(dirName + File.separator + fileName);

        Writer writer = new FileWriter(outputFile);

        Reader reader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();

        for (File javaFile : javaFiles) {
            reader = new FileReader(javaFile);
            bufferedReader = new BufferedReader(reader);

            String line;
            while((line = bufferedReader.readLine())!=null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            bufferedReader.close();
            reader.close();
        }
        writer.write(stringBuilder.toString());
        writer.close();
    }

    public static File[] finder(String dirName) throws IllegalArgumentException {

        File directory = new File(dirName);

        if (directory.isDirectory()) {
            return directory.listFiles((dir, filename) -> filename.endsWith(".java"));
        } else {
            throw new IllegalArgumentException("The path specified does not lead to a directory.");
        }
    }
}
