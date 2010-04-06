package me.lutea.iswho;


public class WhoisServer {
	public static final String	DEFAULT_PARAM	= "{domain}";

	private String				host;
	private String				params;

	public WhoisServer(String url) {
		if (null != url && !"".equals( url.trim() )) {
			String u = url.trim();
			int pos = u.indexOf( "?" );
			if (pos > 0) {
				this.host = u.substring( 0, pos ).toLowerCase();
				this.params = u.substring( pos + 1 );
			}
			else {
				this.host = u.toLowerCase();
				this.params = DEFAULT_PARAM;
			}
		}
	}

	public String getHostName() {
		return host;
	}

	public String getParams(String domain) {
		return params.replace( DEFAULT_PARAM, domain );
	}

	public WhoisServer setParams(String params) {
		this.params = params;
		return this;
	}

	@Override
	public String toString() {
		return host + "?" + params;
	}

}
