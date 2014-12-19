/*
 * copyright belongs to chalse.park
 * hyponus@gmail.com, aorura21@gmail.com
 * aorura@korea.com, anderson.park2080@gmail.com, anybody
 */
package com.aorura.tosrt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class ToSrt {
	public static void main(String args[]) {
		final String EMPTY_LINE = "";
		final String COLON  = ":";
		final String COMMA = ",";
		final String ARROW = " --> ";
		final String DIR = "convertedSrt";
		//final String DOT = ".";
		
		File dir = new File(DIR);
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		for (String arg : args) {
			try {
				File subtitle = new File(arg);
				FileReader fileReader = new FileReader(subtitle);
				BufferedReader reader = new BufferedReader(fileReader);
				
				StringBuilder outputFile = new StringBuilder();
				outputFile.append(dir.getPath());
				outputFile.append(dir.separator);
				outputFile.append(subtitle.getName());
				
				File output = new File(outputFile.toString());
				PrintWriter fileWriter = new PrintWriter(output);
				
				String line = null;
				int i = 1;
				//System.out.print(DOT);
				fileWriter.println(i++);
				while ((line = reader.readLine()) != null) {
					if (line.equals(EMPTY_LINE)) {
						fileWriter.println(line);
						fileWriter.println(i++);
						//System.out.print(DOT);
					} else if (line.indexOf(COLON) > 0 && line.indexOf(COMMA)  > 0) {
						String[] times = line.split(COMMA);
						StringBuilder timeLine = new StringBuilder();
						timeLine.append(times[0]);
						timeLine.append(ARROW);
						timeLine.append(times[1]);
						fileWriter.println(timeLine.toString());
						//System.out.print(DOT);
					} else {
						fileWriter.println(line);
						//System.out.print(DOT);
					}
				}
				reader.close();
				fileWriter.close();
				reader = null;
				fileWriter = null;
			}  catch (Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
			System.out.println(arg + " : DONE");
		}
		System.out.println("EXIT");
	}
}
