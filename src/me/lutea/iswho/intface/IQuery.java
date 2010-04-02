package me.lutea.iswho.intface;

import java.util.List;

import me.lutea.iswho.WhoisServer;

public interface IQuery {
	List<String> query(String domain, WhoisServer server);
}
