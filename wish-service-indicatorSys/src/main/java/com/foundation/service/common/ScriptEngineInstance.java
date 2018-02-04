package com.foundation.service.common;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
  *
  * @ClassName: ScriptEngineInstance
  * @Description: 单例生成脚本执行引擎
  * @Author Samwang
  * @Date 2016/8/4 17:07
  * @Version V1.0
 */
public class ScriptEngineInstance{
	private static class ScriptEngineLazy {
		private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
	}

	private ScriptEngineInstance(){
	}

	public static ScriptEngine  getInstance(){
		return ScriptEngineLazy. engine;
	}

}