/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011-2016 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

/** This is generated, do not edit by hand. **/

#include <jni.h>

#include "Proxy.h"

namespace de {
namespace appwerft {
namespace fortunewheelview {
	namespace wheel {

class WheelViewProxy : public titanium::Proxy
{
public:
	explicit WheelViewProxy();

	static void bindProxy(v8::Local<v8::Object>, v8::Local<v8::Context>);
	static v8::Local<v8::FunctionTemplate> getProxyTemplate(v8::Isolate*);
	static void dispose(v8::Isolate*);

	static jclass javaClass;

private:
	static v8::Persistent<v8::FunctionTemplate> proxyTemplate;

	// Methods -----------------------------------------------------------
	static void getSelectedIndex(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setSelectedIndex(const v8::FunctionCallbackInfo<v8::Value>&);
	static void getTotalItems(const v8::FunctionCallbackInfo<v8::Value>&);

	// Dynamic property accessors ----------------------------------------

};

	} // namespace wheel
} // fortunewheelview
} // appwerft
} // de
