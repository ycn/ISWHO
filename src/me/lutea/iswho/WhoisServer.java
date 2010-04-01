package me.lutea.iswho;

import java.net.MalformedURLException;
import java.net.URL;

public class WhoisServer {
	public static final String	DEFAULT_PARAM	= "{domain}";

	public enum Protocol {
		WHOIS, HTTP, HTTPS
	}

	private String		url;
	private String		host;
	private String		params;
	private Protocol	ptl;

	public WhoisServer(String url) {
		if (null != url && !"".equals( url.trim() )) {
			String u = url.trim();
			if (u.startsWith( "https://" )) {
				this.ptl = Protocol.HTTPS;
				this.url = u;
				this.host = getHostName( u ).toLowerCase();
				this.params = "";
			}
			else if (u.startsWith( "http://" )) {
				this.ptl = Protocol.HTTP;
				this.url = u;
				this.host = getHostName( u ).toLowerCase();
				this.params = "";
			}
			else {
				this.ptl = Protocol.WHOIS;
				this.url = u;
				this.host = u.toLowerCase();
				this.params = DEFAULT_PARAM;
			}
		}
	}

	public String getURL(String domain) {
		return url.replace( DEFAULT_PARAM, domain );
	}
	
	public String getHostName() {
		return host;
	}

	public String getParams(String domain) {
		return params.replace( DEFAULT_PARAM, domain );
	}

	public Protocol getProtocol() {
		return ptl;
	}

	public WhoisServer setParams(String params) {
		this.params = params;
		return this;
	}

	@Override
	public String toString() {
		return url + (ptl.equals( Protocol.WHOIS ) ? "?" + params : "");
	}

	public static String getHostName(String url) {
		String host = "";
		if (url.indexOf( "://" ) == -1)
			return url;
		URL u;
		try {
			u = new URL( url );
			host = u.getHost();
		}
		catch (MalformedURLException e) {
			// do nothing
		}
		return host;
	}
}
