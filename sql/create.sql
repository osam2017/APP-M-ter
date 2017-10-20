
create DATABASE OSAM;
use OSAM;
CREATE TABLE pingping_army (
	id VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	army_number VARCHAR(15) NOT NULL,
	name VARCHAR(15) NOT NULL,
	mil_class INT(11) NOT NULL,
	belong INT(11) NOT NULL,
	PRIMARY KEY (id,army_number)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;




insert into pingping_army(id, password ,name,army_number,mil_class,belong) values('ssqs11','ddsas1122','김승구','11-44190799',1,2);
insert into pingping_army(id, password ,name,army_number,mil_class,belong) values('jklnm113','greed0909','성제혁','10-56620991',2,4);
insert into pingping_army(id, password ,name,army_number,mil_class,belong) values('jhk2403','sksix22','강혁수','13-29088811',3,12);
insert into pingping_army(id, password ,name,army_number,mil_class,belong) values('osam222','dsds1888','권혁수','16-04042901',2,10);




CREATE TABLE pingping_passInfo (
	id VARCHAR(20) NOT NULL,
	moment datetime NOT NULL
)