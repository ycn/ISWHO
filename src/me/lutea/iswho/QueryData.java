package me.lutea.iswho;

public class QueryData {

	private String	startLine;
	private String	endLine;

	public QueryData() {
		startLine = "";
		endLine = "";
	}

	public QueryData setStartLine(String str) {
		startLine = str.trim().toLowerCase();
		return this;
	}

	public String getStartLine() {
		return startLine;
	}

	public QueryData setEndLine(String str) {
		endLine = str.trim().toLowerCase();
		return this;
	}

	public String getEndLine() {
		return endLine;
	}

}
