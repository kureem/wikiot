/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.castafiore.iot.builder;

import org.castafiore.ui.layout.EXXHTMLFragment;

public class EXSource extends EXXHTMLFragment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EXSource() {
		super("source", "templates/EXHelp.xhtml");
	}

	public void refreshContent() {
		// Application app =
		// getAncestorOfType(EXWorkspace.class).getDescendentOfType(EXLayout.class).getApplication();

		try {
			// setValue(mapper.writeValueAsString((Application)DT.dt));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
