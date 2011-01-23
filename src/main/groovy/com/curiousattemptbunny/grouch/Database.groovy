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
	
	/**
	 * Saves the document to the database. 
	 * @param document
	 * @return Couchdb response JSON. e.g.
	 * <code>[ok:true, id:457917a5b6a8a8b3a101b52623000d77, rev:1-9c9af396ba27d000b043b3c66664fbe4</code>
	 */
	def leftShift(document) {
		if (document._id) {
			return addOrUpdateDocumentWithId(document) 
		} else {
			return addDocumentWithoutId(document)
		}
	}
	
	private def addOrUpdateDocumentWithId(document) {
		return httpBuilder.request( PUT, JSON, {
			uri.path = "/$name/${document._id}"
			body = document
		} );
	}
	
	private def addDocumentWithoutId(document) {
		return httpBuilder.request( POST, JSON, {
			uri.path = "/$name/"
			body = document
		} )
	}
}
