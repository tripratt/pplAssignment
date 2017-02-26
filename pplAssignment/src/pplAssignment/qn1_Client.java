package pplAssignment;

import java.io.*;
import java.sql.Timestamp;
import com.csvreader.CsvWriter;

public class qn1_Client {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		
		Client.input();
        coupleCreator cC = new coupleCreator();
        cC.create();
        
         Timestamp ts = new Timestamp(System.currentTimeMillis());
         
         String outputFile = "C:\\Users\\dell\\Desktop\\qn1_TimeStamp.csv";
 		
 		boolean alreadyExists = new File(outputFile).exists();
 			
 		try {
 	
 			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
 			
 			
 			if (!alreadyExists)
 			{
 				csvOutput.write("TimeStamp");
 				csvOutput.write("C_id");
 				csvOutput.endRecord();
 			}
 			
 			
 			
 			
 			
 			for (int it = 0; it < Client.couple_array.size(); it++) {
 	           csvOutput.write("Time :" + ts.toString());
 	           csvOutput.write(Integer.toString(Client.couple_array.get(it).cid));
 	           csvOutput.endRecord();
 	        }
 			
 			csvOutput.close();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}		

	}

}
