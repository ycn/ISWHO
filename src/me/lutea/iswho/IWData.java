package me.lutea.iswho;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import me.lutea.iswho.intface.IData;
import me.lutea.iswho.intface.ILog.LEVEL;

public class IWData implements IData {
	protected static Properties	Settings;
	protected static Properties	Servers;
	protected static Properties	Datas;
	protected static Properties	Filters;

	static {
		String pFile = "";
		try {
			pFile = "settings.properties";
			Settings = new Properties();
			Settings.load( IWData.class.getResourceAsStream( pFile ) );

			pFile = "servers.properties";
			Servers = new Properties();
			Servers.load( IWData.class.getResourceAsStream( pFile ) );

			pFile = "datas.properties";
			Datas = new Properties();
			Datas.load( IWData.class.getResourceAsStream( pFile ) );

			pFile = "filters.properties";
			Filters = new Properties();
			Filters.load( IWData.class.getResourceAsStream( pFile ) );
		}
		catch (Exception e) {
			ISWHO.getLog().log( LEVEL.ERROR, "can not load <" + pFile + ">" );
		}
	}

	public IWData() {
	}

	@Override
	public WhoisServer getServer(String domain) {
		WhoisServer server = null;

		List<String> tlds = buildTLDs( domain );
		if (tlds.isEmpty())
			return server;

		for (String tld : tlds) {
			server = getTLDServer( tld );
			if (null != server)
				break;
		}

		if (null == server)
			for (String tld : tlds) {
				String host = joinDefaultServer( tld );
				if (null == getAddressbyName( host )) {
					ISWHO.getLog().log( LEVEL.WARN,
							"[" + domain + "] can not access to server <" + host + "> try next one..." );
					continue;
				}
				server = new WhoisServer( host );
				break;
			}

		return server;
	}

	private List<String> buildTLDs(String domain) {
		List<String> tlds = new ArrayList<String>();
		String tld = domain;
		int pos = -1;
		while ((pos = tld.indexOf( '.' )) > -1) {
			tld = tld.substring( pos + 1 );
			tlds.add( tld );
		}
		return tlds;
	}

	private String getAddressbyName(String domain) {
		String host = null;
		try {
			InetAddress addr = InetAddress.getByName( domain );
			host = addr.getHostAddress();
		}
		catch (UnknownHostException e) {
			// do nothing
		}
		return host;
	}

	private String joinDefaultServer(String tld) {
		return tld + WhoisServer.DEFAULT_SERVER;
	}

	@Override
	public WhoisServer getTLDServer(String tld) {
		WhoisServer server = null;
		String s = Servers.getProperty( tld, "" );
		if (s.equals( "" )) {
			return server;
		}
		String[] sp = s.split( "," );
		if (sp.length == 1) {
			server = new WhoisServer( sp[0] );
		}
		else {
			if (sp[0].trim().equals( "" )) {
				sp[0] = joinDefaultServer( tld );
			}
			server = new WhoisServer( sp[0] );
			String p = Settings.getProperty( sp[1].trim(), "" );
			if (!p.equals( "" )) {
				server.setParams( p );
			}
		}
		return server;
	}

	@Override
	public QueryData getQueryData(String host) {
		QueryData data = new QueryData();
		if (host.endsWith( WhoisServer.DEFAULT_SERVER ))
			host = host.substring( 0, host.indexOf( WhoisServer.DEFAULT_SERVER ) );
		String[] params = Filters.getProperty( host, "" ).split( "\\|{3}" );
		data.setStartLine( params[0] );
		if (params.length > 1) {
			data.setEndLine( params[1] );
		}
		return data;
	}

	@Override
	public ParseData getParseData(String host) {
		ParseData data = new ParseData();
		if (host.endsWith( WhoisServer.DEFAULT_SERVER ))
			host = host.substring( 0, host.indexOf( WhoisServer.DEFAULT_SERVER ) );
		String[] params = Datas.getProperty( host, "" ).split( "\\|{3}" );

		return data;
	}
}
