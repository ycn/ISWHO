package me.lutea.iswho;

import java.util.LinkedHashMap;
import java.util.Map;

import me.lutea.iswho.util.JSONEncode;

public class WhoisContact {

	private Map<String, String>	map;

	public WhoisContact() {
		map = new LinkedHashMap<String, String>();
		map.put( "name", "" );
		map.put( "org", "" );
		map.put( "country", "" );
		map.put( "state", "" );
		map.put( "city", "" );
		map.put( "pcode", "" );
		map.put( "addr", "" );
		map.put( "phone", "" );
		map.put( "fax", "" );
		map.put( "email", "" );
	}

	@Override
	public String toString() {
		return toJSONString();
	}

	public String toJSONString() {
		return JSONEncode.encode( map );
	}

	Map<String, String> getMap() {
		return map;
	}

	public String getName() {
		return map.get( "name" );
	}

	public String getOrganization() {
		return map.get( "org" );
	}

	public String getCountry() {
		return map.get( "country" );
	}

	public String getState() {
		return map.get( "state" );
	}

	public String getCity() {
		return map.get( "city" );
	}

	public String getPostcode() {
		return map.get( "pcode" );
	}

	public String getAddress() {
		return map.get( "addr" );
	}

	public String getPhone() {
		return map.get( "phone" );
	}

	public String getFax() {
		return map.get( "fax" );
	}

	public String getEmail() {
		return map.get( "email" );
	}

	public WhoisContact setName(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "name" );
			if (!ols.equals( s ))
				map.put( "name", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setOrganization(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "org" );
			if (!ols.equals( s ))
				map.put( "org", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setCountry(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "country" );
			if (!ols.equals( s ))
				map.put( "country", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setState(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "state" );
			if (!ols.equals( s ))
				map.put( "state", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setCity(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "city" );
			if (!ols.equals( s ))
				map.put( "city", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setPostcode(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "pcode" );
			if (!ols.equals( s ))
				map.put( "pcode", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setAddress(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "addr" );
			if (!ols.equals( s ))
				map.put( "addr", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setPhone(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "phone" );
			if (!ols.equals( s ))
				map.put( "phone", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setFax(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "fax" );
			if (!ols.equals( s ))
				map.put( "fax", ols + " " + s );
		}
		return this;
	}

	public WhoisContact setEmail(String str) {
		if (null != str && !"".equals( str.trim() )) {
			String s = str.trim().toLowerCase();
			String ols = map.get( "email" );
			if (!ols.equals( s ))
				map.put( "email", ols + " " + s );
		}
		return this;
	}
}
