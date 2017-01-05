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
package org.castafiore.iot.sample;

import org.castafiore.iot.driver.DefinitionRegistryClient;

public class Util {
	
	public final static String ENDPOINT = "http://72.13.93.222/iot";
	
	public final static String WS_ENDPOINT = ENDPOINT.replaceFirst("http", "ws");
	
	public final static DefinitionRegistryClient client = new DefinitionRegistryClient(ENDPOINT);

}
