use mysql;
update user SET PASSWORD=PASSWORD("root") WHERE USER='root';
flush privileges;

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' identified by 'root' WITH GRANT OPTION;