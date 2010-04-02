package me.lutea.iswho.intface;

import java.util.Map;

import me.lutea.iswho.WhoisServer;

public interface IData {
	WhoisServer getServer(String domain);

	WhoisServer getTLDServer(String tld);

	Map<String, String> getQueryData(String host);

	Map<String, String> getParseData(String host);
}
