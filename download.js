var http = require('http');
var express = require('express');
var cookieParser = require('cookie-parser');
var session=require('express-session');
var app = express();
var bodyParser = require('body-parser'); 


app.use('/', console.log(__dirname + '/image'));

var connection = mysql.createConnection
({ host :'localhost',  
port : 3306,
 user : 'root',
 password : '1234', 
 database:'OSAM' 
 });

 
 
 
 
connection.connect(function(err) {
    if (err) {
        console.error('mysql connection error');
        console.error(err);
        throw err;
    }else{
        console.log("연결에 성공하였습니다.");
    }
});

