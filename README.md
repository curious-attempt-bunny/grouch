# Grouch

Use Grouch if you want a concise API for interacting with CouchDb from the JVM.

## Example Usage

Once you have Grouch and it's dependencies on the path it's as simple as this to use:

[Example.groovy](curious-attempt-bunny/grouch/blob/master/src/example/groovy/Example.groovy)
    import com.curiousattemptbunny.grouch.*
    
    grouch = new Grouch('http://localhost:5984')
    if (grouch.mydb.exists()) grouch.mydb.delete()
    grouch.mydb.create()
    grouch.mydb << [_id: 'mydoc', aField: 'aValue' ]
    assert grouch.mydb.mydoc.aField == 'aValue'

Note: a public maven repository is a TODO item. For now you can build both yourself using gradle - see [build.gradle](https://github.com/curious-attempt-bunny/grouch/blob/master/build.gradle).

## Further Development

[Let me know](http://www.curiousattemptbunny.com/2011/01/grouch-dsl-for-couchdb.html) if you find this useful. Fork it and send me a pull request if you want to try your hand at adding to it yourself.

