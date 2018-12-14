package com.climber.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LoggerUtils {
	
	public static Logger getLogger(Class<?> clazz) {

		Logger logger = Logger.getLogger(clazz); // �����µ�Logger
		logger.removeAllAppenders(); // ���Appender���؄e�ǲ���ʹ�ìF�挍���rһ��Ҫ���ڻ�
		logger.setLevel(Level.DEBUG); // �趨Logger���e��
		logger.setAdditivity(true); // �趨�Ƿ�̳и�Logger��Ĭ��Ϊtrue���̳�root������趨false�󽫲�����root��

		FileAppender appender = new RollingFileAppender(); // �����µ�Appender
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("[%d{yyyy-MM-dd HH:mm:ss}] %p %l : %m%n"); // log�������ʽ
		appender.setLayout(layout);

		appender.setFile("logs/" + getTime("yyyy-MM-dd") + ".log"); // log���·��
		appender.setEncoding("UTF-8"); // log���ַ�����
		appender.setAppend(true); // ��־�ϲ���ʽ�� true:���Ѵ���log�ļ�����׷�� false:��log������ǰ��log
		appender.activateOptions(); // ���õ�ǰ����

		logger.addAppender(appender); // ���µ�Appender�ӵ�Logger��
		return logger;
	}

	private static String getTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
}
