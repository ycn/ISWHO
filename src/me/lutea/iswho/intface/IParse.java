package me.lutea.iswho.intface;

import me.lutea.iswho.WhoisMap;
import me.lutea.iswho.WhoisServer;

public interface IParse {
	WhoisServer parse(WhoisMap whoisMap, String rawData);
}
