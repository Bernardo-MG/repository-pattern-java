#!/bin/bash
#
# Creates the test databases.
#
# This script requires a MySQL and a PostgreSQL services, with the default super user,
# where the test databases will be created.

set -o nounset
set -e

# MySQL tables
mysql -e "create database IF NOT EXISTS test_jpa_hibernate;" -uroot
mysql -e "create database IF NOT EXISTS test_jpa_hibernate_modify;" -uroot
mysql -e "create database IF NOT EXISTS test_spring_jdbc;" -uroot
mysql -e "create database IF NOT EXISTS test_spring_jdbc_modify;" -uroot
mysql -e "create database IF NOT EXISTS test_jpa_eclipselink;" -uroot
mysql -e "create database IF NOT EXISTS test_jpa_eclipselink_modify;" -uroot

# PostgreSQL tables
psql -c 'create database test_jpa_hibernate;' -U postgres
psql -c 'create database test_jpa_hibernate_modify;' -U postgres
psql -c 'create database test_spring_jdbc;' -U postgres
psql -c 'create database test_spring_jdbc_modify;' -U postgres
psql -c 'create database test_jpa_eclipselink;' -U postgres
psql -c 'create database test_jpa_eclipselink_modify;' -U postgres

exit 0
