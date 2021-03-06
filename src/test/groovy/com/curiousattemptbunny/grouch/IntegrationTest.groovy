package com.curiousattemptbunny.grouch

import groovyx.net.http.HTTPBuilder
import spock.lang.*
import static groovyx.net.http.Method.*


/**
 * This integration test requires that you have couchdb deployed and available via http://127.0.0.1:5984
 * 
 * @author Merlyn Albery-Speyer
 */
class IntegrationTest extends Specification {
	
	// TODO non-standard paths (e.g. http://localhost/couchdb/ )
	// TODO _changes (with since support)
	// TODO document gets with parameters
	// TODO view support
	
	def couchdb
	def testDb
	
	def setup() {
		couchdb = new Grouch('http://127.0.0.1:5984/')
		
		testDb = couchdb."grouch-test-db"
		
		// easier to debug than if we do this in cleanup
		if (testDb.exists()) {
			testDb.delete()
		}
		
		couchdb."grouch-test-db".create()
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
	
	def "we don't need to specify a document id to add it"() {
		given:
		def response = testDb << [aField: 'aValue']
		
		expect:
		testDb[response.id].aField == 'aValue'
	}
}