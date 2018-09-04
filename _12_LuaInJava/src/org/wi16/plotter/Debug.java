package org.wi16.plotter;

import java.util.Map;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

public class Debug {

	public static void main(String[] args) {
		final LuaBridge g = new LuaBridge();

		LuaValue luaval = g.loadfile("src/test.lua");
		luaval.call();
		Map<String, LuaValue> globalVals = g.getGlobalValues();
		for (String name : globalVals.keySet()) {
			System.out.println(name + " : " + globalVals.get(name));
		}
		g.set("myVar", LuaValue.valueOf(true));
		LuaValue funcVariable = g.get("foo");
		Varargs params = LuaValue.varargsOf(new LuaValue[] { LuaValue.valueOf(4), LuaValue.valueOf(true) });
		Varargs result = funcVariable.invoke(params);
		System.out.println(result.checklong(1));
	}

}
