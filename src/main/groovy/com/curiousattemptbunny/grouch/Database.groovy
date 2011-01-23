package com.curiousattemptbunny.grouch

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*

class Database {
	String name
	HTTPBuilder httpBuilder
	
	def boolean exists() {
		httpBuilder.request( GET, JSON, { uri.path = "/_all_dbs" } ).contains( name )
	}
	
	def void delete() {
		httpBuilder.request DELETE, { uri.path = "/$name/" }
	}
	
	def void create() {
		httpBuilder.request PUT, { uri.path = "/$name/" }
	}
	
	def propertyMissing(String documentId) {
		return httpBuilder.request( GET, JSON, {
			uri.path = "/$name/$documentId"
		} )
	}
	
	def Database leftShift(document) {
		assert document._id
		httpBuilder.request PUT, JSON, {
			uri.path = "/$name/${document._id}"
			body = document
		}
		return this
	}
}
