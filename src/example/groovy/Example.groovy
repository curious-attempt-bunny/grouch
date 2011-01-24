import com.curiousattemptbunny.grouch.*

grouch = new Grouch('http://localhost:5984')
if (grouch.mydb.exists()) grouch.mydb.delete()
grouch.mydb.create()
grouch.mydb << [_id: 'mydoc', aField: 'aValue' ]
assert grouch.mydb.mydoc.aField == 'aValue'


