package pplAssignment;

import java.io.*;
import java.util.ArrayList;

public class coupleCreator {

	public void create() {

		ArrayList<girl> girl_array = Client.girl_array;
		ArrayList<boy> boy_array = Client.boy_array;
		ArrayList<couple> couple_array = Client.couple_array;

		for (int a = 0; a < girl_array.size(); a++) {
			for (int b = 0; b < boy_array.size(); b++) {
				if (boy_array.get(b).minAttractivenessReq <= girl_array.get(a).attractiveness
						&& boy_array.get(b).budget > girl_array.get(a).maintenanceCost
						&& boy_array.get(b).isCommitted == 0) {
					boy_array.get(b).isCommitted = 1;
					girl_array.get(a).isCommitted = 1;
					couple c = new couple(boy_array.get(b), girl_array.get(a));
					couple_array.add(c);
					break;
				}
			}
		}
	}

}
