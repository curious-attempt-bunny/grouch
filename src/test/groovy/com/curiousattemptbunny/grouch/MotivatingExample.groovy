package com.curiousattemptbunny.grouch

import groovyx.net.http.HTTPBuilder
import spock.lang.*
import static groovyx.net.http.Method.*

class MotivatingExample extends Specification {
	
	// TODO non-standard paths (e.g. http://localhost/couchdb/ )
	
	def couchdb
	def testDb
	
	def setup() {
		couchdb = new Grouch('http://127.0.0.1:5984/')
		
		couchdb."grouch-test-db".create()
		
		testDb = couchdb."grouch-test-db"
	}
	
	def cleanup() {
		couchdb."grouch-test-db".delete()
	}
	
	def "we can add and read a new document"() {
		given:
		testDb << [_id: 'mydoc', aField: 'aValue' ]

		expect:
		testDb.mydoc.aField == 'aValue'
	}

	def "we can determine if a database exists"() {
		expect:
		testDb.exists()
		!couchdb.nonExistantDb.exists()
	}
}