package me.lutea.iswho.intface;

import me.lutea.iswho.WhoisServer;

public interface IQuery {
	String query(String domain, WhoisServer server);

	boolean useProxy();

	void setProxyFactory(IProxy proxyFactory);
	
	String curProxy();
}
