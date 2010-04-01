package me.lutea.iswho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.lutea.iswho.util.JSONEncode;

public class WhoisMap {

	private Map<String, Object>			map;
	private Map<String, WhoisContact>	contacts;

	public WhoisMap(String domain) {
		map = new LinkedHashMap<String, Object>();
		map.put( "Domain", domain );
		map.put( "Status", new ArrayList<String>() );
		map.put( "NameServers", new ArrayList<String>() );
		map.put( "WhoisServers", new ArrayList<String>() );
		map.put( "Registrar", "" );
		map.put( "ReferralUrl", "" );
		map.put( "CreatedDate", "" );
		map.put( "UpdatedDate", "" );
		map.put( "ExpirationDate", "" );
		map.put( "RawData", new ArrayList<String>() );
		contacts = new HashMap<String, WhoisContact>();
	}

	@Override
	public String toString() {
		return toJSONString();
	}

	public String toJSONString() {
		Map<String, Map<String, String>> conts = new HashMap<String, Map<String, String>>();
		if (contacts.containsKey( "reg" ))
			conts.put( "reg", contacts.get( "reg" ).getMap() );
		if (contacts.containsKey( "admin" ))
			conts.put( "admin", contacts.get( "admin" ).getMap() );
		if (contacts.containsKey( "tech" ))
			conts.put( "tech", contacts.get( "tech" ).getMap() );
		if (contacts.containsKey( "bill" ))
			conts.put( "bill", contacts.get( "bill" ).getMap() );
		map.put( "Contacts", conts );
		return JSONEncode.encode( map );
	}

	public String getDomain() {
		return ( String ) map.get( "Domain" );
	}

	@SuppressWarnings("unchecked")
	public List<String> getStatus() {
		return ( List<String> ) map.get( "Status" );
	}

	@SuppressWarnings("unchecked")
	public List<String> getNameServers() {
		return ( List<String> ) map.get( "NameServers" );
	}

	@SuppressWarnings("unchecked")
	public List<String> getWhoisServers() {
		return ( List<String> ) map.get( "WhoisServers" );
	}

	public String getRegistrar() {
		return ( String ) map.get( "Registrar" );
	}

	public String getReferralUrl() {
		return ( String ) map.get( "ReferralUrl" );
	}

	public String getCreatedDate() {
		return ( String ) map.get( "CreatedDate" );
	}

	public String getUpdatedDate() {
		return ( String ) map.get( "UpdatedDate" );
	}

	public String getExpirationDate() {
		return ( String ) map.get( "ExpirationDate" );
	}

	public WhoisContact getContactRegistrant() {
		return contacts.get( "reg" );
	}

	public WhoisContact getContactAdmin() {
		return contacts.get( "admin" );
	}

	public WhoisContact getContactTech() {
		return contacts.get( "tech" );
	}

	public WhoisContact getContactBilling() {
		return contacts.get( "bill" );
	}

	@SuppressWarnings("unchecked")
	public List<String> getRawData() {
		return ( List<String> ) map.get( "RawData" );
	}

	@SuppressWarnings("unchecked")
	public WhoisMap addStatus(String stat) {
		if (stat != null && !"".equals( stat.trim() )) {
			String s = stat.trim().toLowerCase();
			List<String> list = ( List<String> ) map.get( "Status" );
			if (list != null && !list.contains( s ))
				list.add( s );
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public WhoisMap addNameServer(String server) {
		if (server != null && !"".equals( server.trim() )) {
			String s = server.trim().toLowerCase();
			List<String> list = ( List<String> ) map.get( "NameServers" );
			if (list != null && !list.contains( s ))
				list.add( s );
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public WhoisMap addWhoisServers(String server) {
		if (server != null && !"".equals( server.trim() )) {
			String s = server.trim().toLowerCase();
			List<String> list = ( List<String> ) map.get( "WhoisServers" );
			if (list != null && !list.contains( s ))
				list.add( s );
		}
		return this;
	}

	public WhoisMap setRegistrar(String registrar) {
		if (registrar != null && !"".equals( registrar.trim() )) {
			map.put( "Registrar", registrar );
		}
		return this;
	}

	public WhoisMap setReferralUrl(String referralUrl) {
		if (referralUrl != null && !"".equals( referralUrl.trim() )) {
			map.put( "ReferralUrl", referralUrl );
		}
		return this;
	}

	public WhoisMap setCreatedDate(String createdDate) {
		if (createdDate != null && !"".equals( createdDate.trim() )) {
			map.put( "CreatedDate", createdDate );
		}
		return this;
	}

	public WhoisMap setUpdatedDate(String updatedDate) {
		if (updatedDate != null && !"".equals( updatedDate.trim() )) {
			map.put( "UpdatedDate", updatedDate );
		}
		return this;
	}

	public WhoisMap setExpirationDate(String expirationDate) {
		if (expirationDate != null && !"".equals( expirationDate.trim() )) {
			map.put( "ExpirationDate", expirationDate );
		}
		return this;
	}

	public WhoisMap setContactRegistrant(WhoisContact c) {
		if (null != c && !c.getMap().isEmpty()) {
			contacts.put( "reg", c );
		}
		return this;
	}

	public WhoisMap setContactAdmin(WhoisContact c) {
		if (null != c && !c.getMap().isEmpty()) {
			contacts.put( "admin", c );
		}
		return this;
	}

	public WhoisMap setContactTech(WhoisContact c) {
		if (null != c && !c.getMap().isEmpty()) {
			contacts.put( "tech", c );
		}
		return this;
	}

	public WhoisMap setContactBilling(WhoisContact c) {
		if (null != c && !c.getMap().isEmpty()) {
			contacts.put( "bill", c );
		}
		return this;
	}

	public WhoisMap setRawData(List<String> raw) {
		if (null != raw && !raw.isEmpty()) {
			map.put( "RawData", raw );
		}
		return this;
	}
}
