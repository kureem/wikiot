/*
 * Copyright (C) 2007-2008 Castafiore
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */

package org.castafiore.ui.js;

import org.castafiore.ui.JQuery;

/**
 * This class represents a Javascript variable.
 * It should be used in conjuction with the {@link JQuery} class
 * It contains the necessary javascript to compute a value
 * 
 * 
 * @author Kureem Rossaye<br>
 *          kureem@gmail.com
 * Oct 22, 2008
 */
public final class JSVar implements JSObject {
	
	private String ref_;
	
	private String serverData_;
	
	
	/**
	 * 
	 * @param ref - the piece of javascript that computes the value of the Var
	 * @param serverData	- optional server data
	 */
	public JSVar(String ref, String serverData)
	{
		this.ref_ = ref;
		
		this.serverData_ = serverData;
	}

	public JSVar(String ref)
	{
		this.ref_ = ref;
		
		this.serverData_ = "";
	}
	
	public JSVar(JSObject ref)
	{
		this.ref_ = ref.getJavascript();
		
		this.serverData_ = "";
	}
	
	/**
	 * checks if this Var is null
	 * @return
	 */
	public boolean isNull()
	{
		return (ref_ == null && serverData_ == null);
	}
	
	/**
	 * returns the piece of javascript
	 * normally used internally by the engine
	 * @return
	 */
	public String getJavascript()
	{
		return ref_;
	}
	
	/**
	 * returns the server data if found for the 
	 * @return
	 */
	public String getServerData()
	{
		return serverData_;
	}
	
	
	/**
	 * build an expression based on the specified variable and specified operator
	 * @param var
	 * @param operator
	 * @return
	 */
	public Expression equate(String var, String operator)
	{
		/**
		 * fsdsdf OP sfasdfa
		 */
		return new Expression( this.getJavascript() + " "+operator+" " + "\"" + var + "\"");
	}
	
	 
	/**
	 * builds an expression based on the specified {@link JSVar} and operator
	 * @param var
	 * @param operator
	 * @return
	 */
	public Expression equate(JSVar var, String operator)
	{
		/**
		 * fsdsdf OP sfasdfa
		 */
		return new Expression( this.getJavascript() + " "+operator+" " + var.getJavascript());
	}
	
	
	/**
	 * builds an expression that verifies if the specified {@link JSVar} equals this {@link JSVar}
	 * @param var
	 * @return
	 */
	public Expression equal(JSVar var)
	{
		return this.equate(var, "==");
	}
	
	/**
	 * builds an expression that verifies if the specified variable equals this {@link JSVar}
	 * @param var
	 * @return
	 */
	public Expression equal(String var)
	{
		return this.equate(var, "==");
	}
	
	
	/**
	 * builds an expression that verifies if the specified {@link JSVar} is greater this {@link JSVar}
	 * @param var
	 * @return
	 */
	public Expression greater(JSVar var)
	{
		return this.equate(var, ">");
	}
	
	/**
	 * builds an expression that verifies if the specified {@link JSVar} is less this {@link JSVar}
	 * @param var
	 * @return
	 */
	public Expression less(JSVar var)
	{
		return this.equate(var, "<");
	}
	
	/**
	 * builds an expression that verifies if the specified {@link JSVar} less or equal this {@link JSVar}
	 * @param var
	 * @return
	 */
	public Expression lessOrEqual(JSVar var)
	{
		return this.equate(var, "<=");
	}
	
	/**
	 * builds an expression that verifies if the specified {@link JSVar} greater or equal this {@link JSVar}
	 * @param var
	 * @return
	 */
	public Expression greaterOrEqual(JSVar var)
	{
		return this.equate(var, ">=");
	}
	
}
