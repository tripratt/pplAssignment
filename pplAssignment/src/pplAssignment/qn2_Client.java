package pplAssignment;

import static java.lang.Math.*;
import java.sql.*;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static pplAssignment.Client.gift_array;
import java.io.*;
import java.util.*;

import com.csvreader.CsvWriter;

public class qn2_Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		coupleCreator cC = new coupleCreator();
		cC.create();
		Client.input();
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		Collections.sort(Client.gift_array, (gift a, gift b) -> {
			if(a.value<b.value){return 1;}else if(a.value>b.value){return -1;}else if(a.price<b.price){return -1;}else if(a.price>b.price){return 1;} else {return 0;}
		});

		String outputFile = "C:\\Users\\dell\\Desktop\\qn2_TimeStamp.csv";
		boolean alreadyExists = new File(outputFile).exists();
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			csvOutput.write("Couples Data:");
			csvOutput.endRecord();

			if (!alreadyExists) {
				csvOutput.write("TimeStamp");
				csvOutput.write("C_id");
				csvOutput.endRecord();
			}

			for (int it = 0; it < Client.couple_array.size(); it++) {
				csvOutput.write("Time :" + ts.toString());
				csvOutput.write(Integer.toString(Client.couple_array.get(it).cid));
				csvOutput.endRecord();
			}

			csvOutput.write("Exchanged Gifts Data:");
			csvOutput.endRecord();

			for (int it1 = 0; it1 < Client.couple_array.size(); it1++) {
				if (Client.couple_array.get(it1).b.type.equals("Miser")) {
					int mtc = Client.couple_array.get(it1).g.maintenanceCost;
					int it2 = 0, it3 = 0;
					while (it2 < mtc) {
						it2 += Client.gift_array.get(it3).price;
						Client.couple_array.get(it1).gift_basket.add(gift_array.get(it3));
						csvOutput.write("Time :" + ts.toString());
						csvOutput.write("Couples: " + Integer.toString(Client.couple_array.get(it1).cid));
						csvOutput.write("Exchanged Gift :" + Integer.toString(gift_array.get(it3).id));
						csvOutput.endRecord();
						it3++;
					}
				} else if (Client.couple_array.get(it1).b.type.equals("Generous")) {
					int mtc = Client.couple_array.get(it1).b.budget;
					int it2 = 0, it3 = 0;
					while (it2 < mtc) {
						it2 += Client.gift_array.get(it3).price;
						Client.couple_array.get(it1).gift_basket.add(gift_array.get(it3));
						csvOutput.write("Time :" + ts.toString());
						csvOutput.write("Couples: " + Integer.toString(Client.couple_array.get(it1).cid));
						csvOutput.write("Exchanged Gift :" + Integer.toString(gift_array.get(it3).id));
						csvOutput.endRecord();
						it3++;
					}
				} else {
					int mtc = Client.couple_array.get(it1).b.budget;
					int it2 = 0, it3 = 0;
					while (it2 < mtc) {
						it2 += Client.gift_array.get(it3).price;
						Client.couple_array.get(it1).gift_basket.add(gift_array.get(it3));
						csvOutput.write("Time :" + ts.toString());
						csvOutput.write("Couples: " + Integer.toString(Client.couple_array.get(it1).cid));
						csvOutput.write("Exchanged Gift :" + Integer.toString(gift_array.get(it3).id));
						csvOutput.endRecord();
						it3++;
					}
					int it4 = Client.couple_array.get(it1).b.budget - it2;
					for (it2 = 0; it2 < gift_array.size(); it2++) {
						if (Client.gift_array.get(it2).type.equals("Luxury") && it4 >= gift_array.get(it2).price) {
							Client.couple_array.get(it1).gift_basket.add(gift_array.get(it3));
							csvOutput.write("Time :" + ts.toString());
							csvOutput.write("Couples: " + Integer.toString(Client.couple_array.get(it1).cid));
							csvOutput.write("Exchanged Gift :" + Integer.toString(gift_array.get(it3).id));
							csvOutput.endRecord();
						}
					}
				}
			}

			for (int it1 = 0; it1 < Client.couple_array.size(); it1++) {
				double h_g = 0, h_b = 0;
				int it3 = 0, it4 = 0;
				for (int it2 = 0; it2 < Client.couple_array.get(it1).gift_basket.size(); it2++) {
					it3 += Client.couple_array.get(it1).gift_basket.get(it2).price;
					it4 += Client.couple_array.get(it1).gift_basket.get(it2).value;
				}
				int mtc = Client.couple_array.get(it1).g.maintenanceCost;
				if (Client.couple_array.get(it1).g.type.equals("Choosy")) {
					h_g += Math.abs(Math.log10(it3 - mtc + (it4 * 2)));
				} else if (Client.couple_array.get(it1).g.type.equals("Desperate")) {
					int val = it3 - mtc ;
					while (Math.exp(val) == Infinity) {
						val -= 500;
					}
					h_g += Math.abs(Math.exp((val)));
				} else {
					h_g += Math.abs(it3 - mtc + it4);
				}
				if (Client.couple_array.get(it1).b.type.equals("Miser")) {
					h_b += Math.abs(Client.couple_array.get(it1).b.budget - it3);
				} else if (Client.couple_array.get(it1).b.type.equals("Generous")) {
					h_b = h_g;
				} else {
					h_b = Client.couple_array.get(it1).g.intelligenceLevel;
				}

				Client.couple_array.get(it1).b.happiness = h_b;
				Client.couple_array.get(it1).g.happiness = h_g;
				Client.couple_array.get(it1).happiness = h_b + h_g;
				Client.couple_array.get(it1).compatibility = Client.couple_array.get(it1).b.budget - mtc
						+ Math.abs(Client.couple_array.get(it1).g.intelligenceLevel
								- Client.couple_array.get(it1).b.intelligenceLevel)
						+ Math.abs(Client.couple_array.get(it1).g.attractiveness
								- Client.couple_array.get(it1).b.attractiveness);
			}

			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String outputFile1 = "C:\\Users\\dell\\Desktop\\qn2_log.csv";
		boolean alreadyExists1 = new File(outputFile1).exists();

		Collections.sort(Client.couple_array, (couple a, couple b) -> {
			if(a.happiness<b.happiness){return 1;}else if(a.happiness>b.happiness){return -1;}else{return 0;}
		});

		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile1, true), ',');
			if (!alreadyExists1) {
				csvOutput.write("Exchanged Gifts Data:");
				csvOutput.endRecord();
				for (int it1 = 0; it1 < Client.couple_array.size(); it1++) {
					int it3 = 0, it4 = 0;
					for (int it2 = 0; it2 < Client.couple_array.get(it1).gift_basket.size(); it2++) {
						it3 += Client.couple_array.get(it1).gift_basket.get(it2).price;
						it4 += Client.couple_array.get(it1).gift_basket.get(it2).value;
					}
					csvOutput.write("Couples :" + Integer.toString(Client.couple_array.get(it1).cid));
					csvOutput.write(
							"Exchanged  Gifts:" + Integer.toString(Client.couple_array.get(it1).gift_basket.size()));
					csvOutput.write("Price :" + Integer.toString(it3));
					csvOutput.write("Value :" + Integer.toString(it4));
					csvOutput.endRecord();
				}

				csvOutput.write(Integer.toString(Client.couple_array.size()) + " are the most happiest couples.");
				csvOutput.endRecord();

				csvOutput.write("TimeStamp");
				csvOutput.write("C_id");
				csvOutput.write("Happiness");
				csvOutput.endRecord();

				for (int it1 = 0; it1 < Client.couple_array.size(); it1++) {
					csvOutput.write("Time :" + ts.toString());
					csvOutput.write(Integer.toString(Client.couple_array.get(it1).cid));
					csvOutput.write("Happiness : " + Double.toString(Client.couple_array.get(it1).happiness));
					csvOutput.endRecord();
				}

				Collections.sort(Client.couple_array, (couple a, couple b) -> {
					if(a.compatibility<b.compatibility){return 1;}else if(a.compatibility>b.compatibility){return -1;}else{return 0;}
				});

				csvOutput.write(Integer.toString(Client.couple_array.size()) + " are the most compatible couples.");
				csvOutput.endRecord();

				csvOutput.write("TimeStamp");
				csvOutput.write("C_id");
				csvOutput.write("Compatibility");
				csvOutput.endRecord();

				for (int it1 = 0; it1 < Client.couple_array.size(); it1++) {
					csvOutput.write("Time :" + ts.toString());
					csvOutput.write(Integer.toString(Client.couple_array.get(it1).cid));
					csvOutput.write("Compatibility :" + Double.toString(Client.couple_array.get(it1).compatibility));
					csvOutput.endRecord();
				}
				csvOutput.close();

			}

			csvOutput.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
