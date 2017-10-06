#!/bin/bash
#
# Creates the test databases.
#
# This script requires a MySQL and a PostgreSQL services, with the default super user,
# where the test databases will be created.

set -o nounset
set -e

# MySQL tables
mysql -e "create database IF NOT EXISTS repository_test;" -uroot

# PostgreSQL tables
psql -c 'create database repository_test;' -U postgres

exit 0
