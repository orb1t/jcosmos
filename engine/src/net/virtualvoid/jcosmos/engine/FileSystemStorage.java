/*
 The contents of this file are subject to the Mozilla Public License
 Version 1.1 (the "License"); you may not use this file except in
 compliance with the License. You may obtain a copy of the License at
 http://www.mozilla.org/MPL/

 Software distributed under the License is distributed on an "AS IS"
 basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 License for the specific language governing rights and limitations
 under the License.

 The Initial Developer of the Original Code is Johannes Rudolph.
 Portions created by the Initial Developer are Copyright (C) 2008
 the Initial Developer. All Rights Reserved.

 Contributor(s):
    Johannes Rudolph <johannes_rudolph@gmx.de>
*/

package net.virtualvoid.jcosmos.engine;

import java.net.MalformedURLException;
import java.net.URL;

import net.virtualvoid.crypto.SHA1Hash;
import net.virtualvoid.jcosmos.ModuleStorage;

public class FileSystemStorage implements ModuleStorage{
	public URL getModuleLocation(SHA1Hash id) {
		try {
			return new URL("file:"+id.asString()+"/");
		} catch (MalformedURLException e) {
			throw new Error(e);
		}
	}
}
