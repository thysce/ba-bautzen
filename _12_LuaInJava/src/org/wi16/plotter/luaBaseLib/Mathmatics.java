package org.wi16.plotter.luaBaseLib;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

public class Mathmatics extends LuaTable {

	public Mathmatics() {
		this.set("sqrt", new SquareRoot());
	}

	public class SquareRoot extends VarArgFunction {
		@Override
		public Varargs invoke(Varargs args) {
			args.argcheck(args.isnumber(1), 1, "sqrt() radiant must be a number");
			args.argcheck(args.narg() == 1, 0, "sqrt() must be called with one single argument");
			double radiant = args.checkdouble(1);
			double squareRoot = Math.sqrt(radiant);
			LuaValue result = LuaValue.valueOf(squareRoot);
			return LuaValue.varargsOf(new LuaValue[] { result });
		}
	}
}
