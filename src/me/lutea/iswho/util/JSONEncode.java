package me.lutea.iswho.util;

import java.util.List;
import java.util.Map;

public class JSONEncode {

	@SuppressWarnings("unchecked")
	public static String encode(Object obj) {
		if (obj == null) {
			return "null";
		}
		else if (obj instanceof Character) {
			return "\"" + toUnicode( escape( obj.toString() ) ) + "\"";
		}
		else if (obj instanceof char[]) {
			return "\"" + toUnicode( escape( new String( ( char[] ) obj ) ) ) + "\"";
		}
		else if (obj instanceof String) {
			return "\"" + toUnicode( escape( ( String ) obj ) ) + "\"";
		}

		StringBuilder sb = new StringBuilder();
		if (obj instanceof Object[]) {
			sb.append( "[" );
			Object[] list = ( Object[] ) obj;
			boolean f = true;
			for (Object v : list) {
				if (f) {
					f = false;
					sb.append( encode( v ) );
				}
				else {
					sb.append( "," + encode( v ) );
				}
			}
			sb.append( "]" );
		}
		else if (obj instanceof List) {
			return encode( (( List ) obj).toArray( new Object[0] ) );
		}
		else if (obj instanceof Map) {
			sb.append( "{" );
			Map map = ( Map ) obj;
			boolean f = true;
			for (Object k : map.keySet()) {
				if (k == null)
					continue;

				String key = k.toString();
				if (f) {
					f = false;
					sb.append( encode( key, map.get( k ) ) );
				}
				else {
					sb.append( "," + encode( key, map.get( k ) ) );
				}
			}
			sb.append( "}" );
		}
		else {
			sb.append( obj.toString() );
		}
		return sb.toString();
	}

	public static String encode(String key, Object value) {
		StringBuilder sb = new StringBuilder();
		sb.append( "\"" );
		sb.append( escape( key ) );
		sb.append( "\":" );
		sb.append( encode( value ) );
		return sb.toString();
	}

	public static String escape(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt( i );
			switch (ch) {
			case '"':
				sb.append( "\\\"" );
				break;
			case '\\':
				sb.append( "\\\\" );
				break;
			case '\b':
				sb.append( "\\b" );
				break;
			case '\f':
				sb.append( "\\f" );
				break;
			case '\n':
				sb.append( "\\n" );
				break;
			case '\r':
				sb.append( "\\r" );
				break;
			case '\t':
				sb.append( "\\t" );
				break;
			case '/':
				sb.append( "\\/" );
				break;
			case '\u0085': // Next Line
				sb.append( "\\u0085" );
				break;
			case '\u2028': // Line Separator
				sb.append( "\\u2028" );
				break;
			case '\u2029': // Paragraph Separator
				sb.append( "\\u2029" );
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString( ch );
					sb.append( "\\u" );
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append( '0' );
					}
					sb.append( ss.toUpperCase() );
				}
				else {
					sb.append( ch );
				}
			}
		}//for
		return sb.toString();
	}

	public static String toUnicode(String s) {
		StringBuilder sb = new StringBuilder();
		if (null != s && !"".equals( s )) {
			char[] c = s.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] > '\u0370') {
					String hex = Integer.toHexString( ( int ) c[i] );
					hex = "\\u" + (hex.length() < 4 ? "0" : "") + hex;
					sb.append( hex );
				}
				else {
					sb.append( c[i] );
				}
			}
		}
		return sb.toString();
	}
}
