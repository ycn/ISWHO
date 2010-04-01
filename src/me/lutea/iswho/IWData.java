package me.lutea.iswho;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import me.lutea.iswho.intface.IData;
import me.lutea.iswho.intface.ILog.LEVEL;

public class IWData implements IData {
	public static final String		DEFAULT_SERVER	= ".whois-servers.net";
	public static final String		COMSIGN			= "domain ={domain}";
	public static final String		DESIGN			= "-T dn,ace {domain}";
	protected static List<String>	ComSignList;
	protected static List<String>	DeSignList;

	static {
		ComSignList = new ArrayList<String>();
		ComSignList.add( "com" );
		ComSignList.add( "net" );
		ComSignList.add( "jobs" );
		ComSignList.add( "cc" );

		DeSignList = new ArrayList<String>();
		DeSignList.add( "de" );
	}

	public IWData() {
	}

	@Override
	public final WhoisServer getServer(String domain) {
		WhoisServer server = null;

		List<String> tlds = buildTLDs( domain );
		if (tlds.isEmpty())
			return server;

		for (String tld : tlds) {
			server = getDirectServer( tld );
			if (null != server)
				break;
		}

		if (null == server)
			for (String tld : tlds) {
				String host = tld + DEFAULT_SERVER;
				if (null == getAddressbyName( host )) {
					ISWHO.getLog().log( LEVEL.WARN,
							"[" + domain + "] can not access to server <" + host + "> try next one..." );
					continue;
				}

				server = new WhoisServer( host );

				// Check if has special parameters
				if (ComSignList.contains( tld ))
					server.setParams( COMSIGN );

				if (DeSignList.contains( tld ))
					server.setParams( DESIGN );

				break;
			}

		return server;
	}

	public static List<String> buildTLDs(String domain) {
		List<String> tlds = new ArrayList<String>();
		String tld = domain;
		int pos = -1;
		while ((pos = tld.indexOf( '.' )) > -1) {
			tld = tld.substring( pos + 1 );
			tlds.add( tld );
		}
		return tlds;
	}

	public static String getAddressbyName(String domain) {
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

	@Override
	public WhoisServer getDirectServer(String tld) {
		return null;
	}
}
