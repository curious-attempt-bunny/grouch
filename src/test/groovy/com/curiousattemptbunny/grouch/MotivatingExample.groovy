package com.curiousattemptbunny.grouch

couchdb = new Grouch('http://localhost')
couchdb.mydb.delete()
couchdb.mydb.create()
couchdb.mydb << [_id: 'mydoc', aField: 'aValue' ]