package pplAssignment;

import java.io.*;
import java.util.ArrayList;

public class couple {

	boy b;
	girl g;
	int cid;
	gift gif;
	double happiness;
	double compatibility;
	ArrayList<gift> gift_basket = new ArrayList<>();

	public couple(boy b, girl g) {
		super();
		this.b = b;
		this.g = g;
		this.cid = b.name * 10000 + g.name;
		this.happiness = 0;
		this.compatibility = 0;
	}

}
