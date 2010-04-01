package me.lutea.iswho.intface;

import me.lutea.iswho.WhoisServer;

public interface IData {

	WhoisServer getServer(String domain);

	WhoisServer getDirectServer(String tld);
	
	
}
