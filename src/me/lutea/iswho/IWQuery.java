package me.lutea.iswho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.lutea.iswho.intface.IQuery;
import me.lutea.iswho.intface.ILog.LEVEL;

public class IWQuery implements IQuery {
	private static final int	READ_TIMEOUT	= 15000;

	public IWQuery() {
	}

	@Override
	public List<String> query(String domain, WhoisServer server) {
		List<String> raw = queryWhois( domain, server );
		return raw;
	}

	protected List<String> queryWhois(String domain, WhoisServer server) {
		List<String> raw = new ArrayList<String>();
		Socket sock = null;

		Map<String, String> params = ISWHO.getData().getQueryData( server.getHostName() );

		try {
			InetAddress addr = InetAddress.getByName( server.getHostName() );
			InetSocketAddress sAddr = new InetSocketAddress( addr, 43 );
			sock = getConnectSocket( sAddr );
			if (null == sock)
				return raw;
			sock.setSoTimeout( READ_TIMEOUT );
			PrintWriter pw = new PrintWriter( sock.getOutputStream() );
			pw.print( server.getParams( domain ) + "\r\n" );
			pw.flush();

			BufferedReader br = new BufferedReader( new InputStreamReader( sock.getInputStream(), "UTF-8" ) );
			String line = null;

			if (null != params.get( "Start" )) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith( params.get( "Start" ) )) {
						raw.add( line );
						break;
					}
				}
			}

			if (null != params.get( "End" )) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith( params.get( "End" ) )) {
						break;
					}
					raw.add( line );
				}
			}
			else {
				while ((line = br.readLine()) != null) {
					raw.add( line );
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

		return raw;
	}

	protected Socket getConnectSocket(SocketAddress addr) {
		if (null == addr)
			return null;
		Socket sock = null;
		int retryTimes = 0;
		while (retryTimes < 6) {
			try {
				sock = new Socket();
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

}
