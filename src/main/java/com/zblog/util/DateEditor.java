package com.zblog.util;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * @author ray.wang
 * @date 2013-6-21 上午01:02:28
 * @DESC 日期格式工具类
 */
public class DateEditor extends PropertyEditorSupport {

	public static final DateFormat DF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");

	public static final int SHORT_DATE = 10;

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DateEditor.DF_LONG.format(value) : "");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		try {
			if (text.length() <= DateEditor.SHORT_DATE) {
				setValue(new java.sql.Date(DateEditor.DF_SHORT.parse(text).getTime()));
			} else {
				setValue(new java.sql.Timestamp(DateEditor.DF_LONG.parse(text).getTime()));
			}
		}
		catch (ParseException ex) {
			IllegalArgumentException iae = new IllegalArgumentException("无法解析此日志格式: " + ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}
	}
}
