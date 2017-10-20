var mysql = require('mysql');

var connection = mysql.createConnection
({ host :'localhost',  
port : 3306,
 user : 'army_manager',
 password : '12345', 
 database:'ARMY' 
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

