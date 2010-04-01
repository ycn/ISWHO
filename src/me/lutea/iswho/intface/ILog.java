package me.lutea.iswho.intface;

import java.util.List;

public interface ILog {
	public enum LEVEL {
		ERROR, WARN, INFO, DEBUG
	};

	void log(LEVEL lv, String info);

	void log(LEVEL lv, String info, List<String> rawdata);

	void log(LEVEL lv, String info, Exception e);
}
