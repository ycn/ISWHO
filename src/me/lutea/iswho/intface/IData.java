package me.lutea.iswho.intface;

import me.lutea.iswho.ParseData;
import me.lutea.iswho.QueryData;
import me.lutea.iswho.WhoisServer;

public interface IData {
	WhoisServer getServer(String domain);

	WhoisServer getTLDServer(String tld);

	QueryData getQueryData(String host);

	ParseData getParseData(String host);
}
