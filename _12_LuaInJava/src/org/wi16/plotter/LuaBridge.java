package org.wi16.plotter;

import java.io.File;
import org.wi16.plotter.luaBaseLib.Mathmatics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.ResourceFinder;

public class LuaBridge extends Globals implements ResourceFinder {

	public LuaBridge() {
		this.finder = this;
		LoadState.install(this);
		LuaC.install(this);

		// load base library
		set("math", new Mathmatics());
	}

	public Map<String, LuaValue> getGlobalValues() {
		final Map<String, LuaValue> gvals = new HashMap<>();
		Varargs keyvals = LuaValue.NIL;
		do {
			keyvals = next(keyvals.arg1());
			if (keyvals.arg1() != LuaValue.NIL) {
				gvals.put(keyvals.checkjstring(1), keyvals.arg(2));
			}
		} while (keyvals.arg1() != LuaValue.NIL);
		return gvals;
	}

	@Override
	public InputStream findResource(String filename) {
		final File f = new File(filename);
		try {
			return new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
