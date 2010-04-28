package me.lutea.iswho;

import java.util.HashMap;
import java.util.Map;

public class ParseData {

	private String				pf;
	private String				datef;
	private String				repp;
	private Map<String, String>	map;
	private Map<String, String>	contacts;

	public ParseData() {
		pf = "";
		datef = "";
		repp = "";
		map = new HashMap<String, String>();
		contacts = new HashMap<String, String>();
	}

}
