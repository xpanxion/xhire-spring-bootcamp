#!/usr/bin/env bash
sudo yum -y update

sudo yum -y install mariadb mariadb-server

sudo systemctl start mariadb

mysql -u root < /vagrant/setup_password.sql
mysql -u root -proot < /vagrant/create_db.sql
mysql -u root -proot < /vagrant/insert_data.sql