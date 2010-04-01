package me.lutea.iswho.intface;

import java.util.List;

import me.lutea.iswho.WhoisMap;
import me.lutea.iswho.WhoisServer;

public interface IParse {

	WhoisServer parse(WhoisMap whoisMap, List<String> rawData);

}
