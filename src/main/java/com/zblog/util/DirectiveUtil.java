package com.zblog.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractTemplateView;

import com.zblog.common.exception.MustBooleanException;
import com.zblog.common.exception.MustDateException;
import com.zblog.common.exception.MustNumberException;
import com.zblog.common.exception.MustSplitNumberException;
import com.zblog.common.exception.MustStringException;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;


public abstract class DirectiveUtil {

	/**
	 * @author ray.wang
	 * @date 2013-6-21 上午01:03:59
	 * @DESC 模板调用类型
	 */
	public enum InvokeType {
		body, custom, sysDefined, userDefined
	}

	/**
	 * @DESC 将参数params赋值到TemplateModel
	 * @param env
	 * @param params
	 * @return
	 * @throws TemplateException
	 */
	public static Map<String, TemplateModel> addParamsToVariable(Environment env, Map<String, TemplateModel> params) throws TemplateException {
		Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
		String key;
		TemplateModel value;
		for (Map.Entry<String, TemplateModel> entry : entrySet) {
			key = entry.getKey();
			value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, entry.getValue());
		}
		return origMap;
	}

	public static Boolean getBool(String name, Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateBooleanModel) {
			return ((TemplateBooleanModel) model).getAsBoolean();
		} else if (model instanceof TemplateNumberModel) {
			return !(((TemplateNumberModel) model).getAsNumber().intValue() == 0);
		} else if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			// 空串应该返回null还是true呢？
			if (!StringUtils.isEmpty(s)) {
				return !(s.equals("0") || s.equalsIgnoreCase("false") || s.equalsIgnoreCase("f"));
			} else {
				return null;
			}
		} else {
			throw new MustBooleanException(name);
		}
	}

	/**
	 * @DESC 获取RequestContext
	 *       ViewResolver中的exposeSpringMacroHelpers必须为true
	 * @param env
	 * @return
	 * @throws TemplateException
	 */
	public static RequestContext getContext(Environment env) throws TemplateException {
		TemplateModel ctx = env.getGlobalVariable(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
		if (ctx instanceof AdapterTemplateModel) {
			return (RequestContext) ((AdapterTemplateModel) ctx).getAdaptedObject(RequestContext.class);
		} else {
			throw new TemplateModelException("RequestContext '" + AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE + "没有找到datamodel");
		}
	}

	public static Date getDate(String name, Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateDateModel) {
			return ((TemplateDateModel) model).getAsDate();
		} else if (model instanceof TemplateScalarModel) {
			DateEditor editor = new DateEditor();
			editor.setAsText(((TemplateScalarModel) model).getAsString());
			return (Date) editor.getValue();
		} else {
			throw new MustDateException(name);
		}
	}

	public static Integer getInt(String name, Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isEmpty(s)) {
				return null;
			}
			try {
				return Integer.parseInt(s);
			}
			catch (NumberFormatException e) {
				throw new MustNumberException(name);
			}
		} else if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().intValue();
		} else {
			throw new MustNumberException(name);
		}
	}

	public static Integer[] getIntArray(String name, Map<String, TemplateModel> params) throws TemplateException {
		String str = DirectiveUtil.getString(name, params);
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		String[] arr = StringUtils.split(str, ",");
		Integer[] ids = new Integer[arr.length];
		int i = 0;
		try {
			for (String s : arr) {
				ids[i++] = Integer.valueOf(s);
			}
			return ids;
		}
		catch (NumberFormatException e) {
			throw new MustSplitNumberException(name, e);
		}
	}

	public static Long getLong(String name, Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isEmpty(s)) {
				return null;
			}
			try {
				return Long.parseLong(s);
			}
			catch (NumberFormatException e) {
				throw new MustNumberException(name);
			}
		} else if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().longValue();
		} else {
			throw new MustNumberException(name);
		}
	}

	public static String getString(String name, Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) model).getAsString();
		} else if ((model instanceof TemplateNumberModel)) {
			return ((TemplateNumberModel) model).getAsNumber().toString();
		} else {
			throw new MustStringException(name);
		}
	}

	/**
	 * @DESC params复制到env
	 * @param env
	 * @param params
	 * @param origMap
	 * @throws TemplateException
	 */
	public static void removeParamsFromVariable(Environment env, Map<String, TemplateModel> params, Map<String, TemplateModel> origMap)
	        throws TemplateException {
		if (params.size() <= 0) {
			return;
		}
		for (String key : params.keySet()) {
			env.setVariable(key, origMap.get(key));
		}
	};

}
