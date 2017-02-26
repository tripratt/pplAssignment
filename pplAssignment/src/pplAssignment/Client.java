package pplAssignment;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

	public static ArrayList<girl> girl_array = new ArrayList<>();
	public static ArrayList<boy> boy_array = new ArrayList<>();
	public static ArrayList<couple> couple_array = new ArrayList<>();

	public static ArrayList<gift> gift_array = new ArrayList<>();

	public static void input() throws IOException {

		String csvFile = "C:\\Users\\dell\\Desktop\\girl.csv";// csv for girl
		String line = "";
		String cvsSplitby = ",";

		int name, attractiveness, maintenanceCost, intelligenceLevel;
		String type;
		girl g;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				name = Integer.parseInt(line.split(",")[0]);
				attractiveness = Integer.parseInt(line.split(",")[1]);
				maintenanceCost = Integer.parseInt(line.split(",")[2]);
				intelligenceLevel = Integer.parseInt(line.split(",")[3]);
				type = (line.split(",")[4]);

				g = new girl(name, attractiveness, maintenanceCost, intelligenceLevel, type);
				girl_array.add(g);
			}
		} catch (IOException eg) {
			eg.printStackTrace();
		}

		csvFile = "C:\\Users\\dell\\Desktop\\boy.csv";// csv for boy
		line = "";
		cvsSplitby = ",";

		int budget, minAttractivenessReq;
		boy b;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				name = Integer.parseInt(line.split(",")[0]);
				attractiveness = Integer.parseInt(line.split(",")[1]);
				budget = Integer.parseInt(line.split(",")[2]);
				intelligenceLevel = Integer.parseInt(line.split(",")[3]);
				minAttractivenessReq = Integer.parseInt(line.split(",")[4]);
				type = (line.split(",")[5]);

				b = new boy(name, attractiveness, budget, intelligenceLevel, minAttractivenessReq, type);
				boy_array.add(b);
			}
		} catch (IOException eb) {
			eb.printStackTrace();
		}

		csvFile = "C:\\Users\\dell\\Desktop\\gift.csv";// csv for gift
		line = "";
		cvsSplitby = ",";

		int price, value, id;
		gift gif;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				price = Integer.parseInt(line.split(",")[0]);
				value = Integer.parseInt(line.split(",")[1]);
				id = Integer.parseInt(line.split(",")[2]);
				type = (line.split(",")[3]);

				gif = new gift(price, value, id, type);
				gift_array.add(gif);
			}
		} catch (IOException egif) {
			egif.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		int opt;
		while (true) {
			System.out.println(
					"PPL Assignment:\n1: Select \"1\" for Answer 1\n2: Select \"2\" for Answer 2\n3: Select \"3\" for Exit");
			opt = s.nextInt();
			switch (opt) {
			case 1: qn1_Client.main(args); break;
			case 2: qn2_Client.main(args); break;
			default: System.exit(0);
			}
		}
	}
}
