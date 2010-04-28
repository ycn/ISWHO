package me.lutea.iswho.intface;


public interface ILog {
	public enum LEVEL {
		ERROR, WARN, INFO, DEBUG
	};

	void log(LEVEL lv, String info);

	void log(LEVEL lv, String info, Exception e);
}
