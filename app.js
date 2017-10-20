
var mysql = require('mysql');

var http = require('http');
var express = require('express');
var cookieParser = require('cookie-parser');
var session=require('express-session');
var app = express();
var bodyParser = require('body-parser'); 

app.use(express.static('image'));

var connection = mysql.createConnection
({ host :'localhost',  
port : 3306,
 user : 'root',
 password : '1234', 
 database:'OSAM' 
 });

 
app.use(cookieParser());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use(session({
 secret: 'test',
 resave: false,
 saveUninitialized: true
}));

 
 
connection.connect(function(err) {
    if (err) {
        console.error('mysql connection error');
        console.error(err);
        throw err;
    }else{
        console.log("연결에 성공하였습니다.");
    }
});



/*
connection.query('SELECT * from army_info', function(err, rows, fields) {
  if (err) throw err;

  console.log(rows);
  console.log('The solution is: ', rows[0].id);
});
*/


/*

app.use(function (request, response) {
    var id = request.param('id','none');
    var pwd = request.param('pwd','none');
    var output = '';

    if(request.session.auth_user) {
        console.log('already login');
        output = 'already login';
    } else if(id=='test' && pwd == 'test') {
        request.session.auth_user = 'test';
        console.log('login success');
        output = 'login success';
    } else {
        output  = 'please login';
        console.log('please login');
    }
    response.send(output);
});

*/

http.createServer(app).listen(5052, function() {
    console.log('Server is running at http://127.0.0.1:80');
});



app.use(function (request, response) {
    var id = request.param('id','none');
    var pwd = request.param('pwd','none');
    var output = '';
	
	
//	response.send("SELECT * from army_info where id='"+id +"'");
	

	connection.query("select id,name ,mil_class ,army_number from pingping_army where id in (select id from pingping_army where id = '"+id+"' and password= '"+pwd+"');" , function(err, rows, fields) {
	


	  if (err) 
	  {
		console.error('mysql connection error');
        console.error(err);
	  }
	  else
	  {

			
			
			if(request.session.auth_user) 
			{
				console.log('already login');
				output = 'already login';
			}
			else if(id=='test' && pwd == 'test')
			{
				request.session.auth_user = 'test';
				console.log('login success');
				output = 'login success';
			}
			
			response.send(rows);
			
			
	  }
		});

				
		
});






/*
app.use(function (request, response)
		{
				var id = request.param('id','none');
				var pwd = request.param('pwd','none');	
				var output = '';
				 isAvail(id,pwd);
				 response.send(output);
				
    });



function isAvail(id,pass)
{
	
});

}	

*/










