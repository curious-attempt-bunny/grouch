package com.curiousattemptbunny.grouch

import groovyx.net.http.HTTPBuilder

class Grouch {
	private def databases
	
	def Grouch(String couchDbUrl) {
		def httpBuilder = new HTTPBuilder(couchDbUrl)
//		httpBuilder.handler.failure = { resp ->
//		    "Unexpected failure: ${resp.statusLine}"
//		}
		databases = [:].withDefault {
			name -> new Database(name: name, httpBuilder: httpBuilder)
		}
	}
	
	def propertyMissing(String name) {
		databases[name]	
	}
}
