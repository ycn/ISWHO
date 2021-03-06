package me.lutea.iswho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

import me.lutea.iswho.intface.IProxy;
import me.lutea.iswho.intface.IQuery;
import me.lutea.iswho.intface.ILog.LEVEL;

public class IWQuery implements IQuery {
	private static final int	READ_TIMEOUT	= 15000;

	private IProxy				proxyFactory;
	private String				curProxy;

	public IWQuery() {
	}

	@Override
	public String query(String domain, WhoisServer server) {
		String raw = queryWhois( domain, server );
		return raw;
	}

	protected String queryWhois(String domain, WhoisServer server) {
		StringBuilder raw = new StringBuilder();
		Socket sock = null;

		QueryData data = ISWHO.getData().getQueryData( server.getHostName() );

		try {
			InetAddress addr = InetAddress.getByName( server.getHostName() );
			InetSocketAddress sAddr = new InetSocketAddress( addr, 43 );
			sock = getConnectSocket( sAddr );
			if (null == sock)
				return raw.toString();
			sock.setSoTimeout( READ_TIMEOUT );
			PrintWriter pw = new PrintWriter( sock.getOutputStream() );
			pw.print( server.getParams( domain ) + "\r\n" );
			pw.flush();

			BufferedReader br = new BufferedReader( new InputStreamReader( sock.getInputStream(), "UTF-8" ) );
			String line = null;

			if (!"".equals( data.getStartLine() )) {
				while ((line = br.readLine()) != null) {
					if (line.trim().toLowerCase().startsWith( data.getStartLine() )) {
						raw.append( line + "\n" );
						break;
					}
				}
			}

			if (!"".equals( data.getEndLine() )) {
				while ((line = br.readLine()) != null) {
					if (line.trim().toLowerCase().startsWith( data.getEndLine() )) {
						break;
					}
					raw.append( line + "\n" );
				}
			}
			else {
				while ((line = br.readLine()) != null) {
					raw.append( line + "\n" );
				}
			}

			pw.close();
			br.close();
		}
		catch (Exception e) {
			ISWHO.getLog().log( LEVEL.ERROR, "[" + domain + "] Query Exception <" + server + ">", e );
		}
		finally {
			if (null != sock) {
				try {
					sock.close();
				}
				catch (IOException e) {
					// ignore
				}
			}
		}

		return raw.toString();
	}

	protected Socket getConnectSocket(SocketAddress addr) {
		if (null == addr)
			return null;
		Socket sock = null;
		int retryTimes = 0;
		while (retryTimes < 6) {
			try {
				curProxy = proxyFactory.getProxy();
				if (null != curProxy) {
					String[] ss = curProxy.split( ":" );
					if (ss.length > 1) {
						Proxy p = new Proxy( Proxy.Type.SOCKS, new InetSocketAddress( ss[0], Integer.parseInt( ss[1] ) ) );
						sock = new Socket( p );
						sock.setSoTimeout( READ_TIMEOUT );
					}
				}
				else {
					sock = new Socket();
				}
				sock.connect( addr, 1000 );
			}
			catch (Exception e) {
				// continue
			}
			finally {
				if (sock.isConnected())
					return sock;
				retryTimes++;
			}
		}
		return null;
	}

	@Override
	public void setProxyFactory(IProxy proxyFactory) {
		this.proxyFactory = proxyFactory;
	}

	@Override
	public boolean useProxy() {
		return (proxyFactory != null);
	}

	@Override
	public String curProxy() {
		return curProxy;
	}

}
