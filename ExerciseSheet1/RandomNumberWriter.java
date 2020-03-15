/** EXERCISE:
   In this exercise you will write a Java application that writes 10,000 random numbers via a byte-based and a char-based
   stream to a file.

   Write a class called RandomNumberWriter that implements RandomIO (package uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.io).
   There must be a constructor that takes one long as an argument. This long has to be used to seed the object's random
   number generator (java.util.Random).

   Implement the two methods writeRandomByte and writeRandomChars, which should write the 10,000 integers to the specified
   file using a byte-based and a char-based stream, respectively. Each of these integers should be between 0 and 100,000
   (including 0, but not 100,000).*/

import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.io.RandomIO;
import java.io.*;
import java.util.Random;

public class RandomNumberWriter implements RandomIO {

    Random randomNumbersGenerator;

    public RandomNumberWriter(long seed) {
        randomNumbersGenerator = new Random(seed);
    }

    @Override
    public void writeRandomByte(String fileName) throws IOException {

        File outputFile = new File(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(outputFile));

        for (int i=0; i<10000; i++) {
            dataOutputStream.writeInt(randomNumbersGenerator.nextInt(100000));
        }
        dataOutputStream.close();
    }

    @Override
    public void writeRandomChars(String fileName) throws IOException {

        File outputFile = new File(fileName);

        Writer outputStream = new FileWriter(outputFile);

        for (int i=0; i<10000; i++) {
            outputStream.write(randomNumbersGenerator.nextInt(100000) + "\r\n");
        }
        outputStream.close();
    }
}