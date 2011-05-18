package uk.ac.shef.semweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UrlFileReader {

    public static final String INPUT_PATH = "input/internalLinks.txt";

    public List<String> readFile(String inputPath) throws FileNotFoundException {
	File inputFile = new File(inputPath);
	Scanner scanner = new Scanner(inputFile);
	
	List<String> urls = new ArrayList<String>();
	while (scanner.hasNext()){
	    urls.add(scanner.nextLine());
	}
	return urls;
    }
}
