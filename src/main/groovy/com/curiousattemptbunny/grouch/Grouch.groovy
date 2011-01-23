package com.curiousattemptbunny.grouch

class Grouch {
	private String couchDbUrl
	private def databases = [:].withDefault { name -> new Database(name: name) }
	
	def Grouch(String couchDbUrl) {
		this.couchDbUrl = couchDbUrl
	}
	
	def propertyMissing(String name) {
		databases[name]	
	}
}
