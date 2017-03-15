CREATE TABLE users(
 user_id  VARCHAR(255) NOT NULL,
 username VARCHAR(45) NOT NULL,
 role enum('Root','Admin','User', 'Watcher') DEFAULT 'User',
 created_by VARCHAR(255),
 email VARCHAR(30) NOT NULL,
 password_hash VARCHAR(64) NOT NULL,
 last_login DATETIME,
 PRIMARY KEY (user_id)
);

CREATE TABLE userdetails(
 user_id VARCHAR(255) NOT NULL,
 country VARCHAR(100) DEFAULT 'Ukraine',
 city VARCHAR(40),
 address VARCHAR(255),
 phone VARCHAR(20),
 first_name VARCHAR(120),
 mid_init VARCHAR(25),
 last_name VARCHAR(120),
 sex VARCHAR(10),
 mobile VARCHAR(20),
 mobile2 VARCHAR(20),
 notes VARCHAR(255),
 activation_date DATE NOT NULL,
 PRIMARY KEY (user_id),
 FOREIGN KEY FK_user_details (user_id) REFERENCES users(user_id)
);

CREATE TABLE groups(
  group_id VARCHAR(255) NOT NULL,
  group_name VARCHAR(20) NOT NULL,
  description VARCHAR(200),
  PRIMARY KEY (group_id)
);

CREATE TABLE user_group(
  group_id VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (group_id,user_id),
  FOREIGN KEY FK_user_group_to_users (user_id) REFERENCES users(user_id),
  FOREIGN KEY FK_user_group_to_group (group_id) REFERENCES groups(group_id)
);

CREATE TABLE classes(
  class_id VARCHAR(255) NOT NULL,
  name VARCHAR(120) NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (class_id)
);

CREATE TABLE units(
  unit_id VARCHAR(255) NOT NULL,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY (unit_id)
);

CREATE TABLE products(
  prod_id VARCHAR(255) NOT NULL,
  name VARCHAR(120) NOT NULL,
  description VARCHAR(255),
  class_id VARCHAR(255) NOT NULL,
  unit_id VARCHAR(255) NOT NULL,
  added_by VARCHAR(255) NOT NULL,
  PRIMARY KEY (prod_id),
  FOREIGN KEY FK_products_to_classes (class_id) REFERENCES classes(class_id),
  FOREIGN KEY FK_products_to_units (unit_id) REFERENCES units(unit_id),
  FOREIGN KEY FK_products_to_users (added_by) REFERENCES users(user_id)
);

CREATE TABLE stash(
  prod_id VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  amount DECIMAL(20,2) DEFAULT '0.00',
  required_amount DECIMAL(20,2) DEFAULT '0.00',
  status enum('Active','Suspended','Closed') DEFAULT 'Closed',
  deadline DATETIME,
  PRIMARY KEY (prod_id,user_id),
  FOREIGN KEY FK_stash_products (prod_id) REFERENCES products(prod_id),
  FOREIGN KEY FK_stash_to_users (user_id) REFERENCES users(user_id)
);

CREATE TABLE premises(
  premise_id VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  attachment MEDIUMBLOB,
  attachment2 MEDIUMBLOB,
  attachment3 MEDIUMBLOB,
  PRIMARY KEY (premise_id)
);

CREATE TABLE movements(
  move_id VARCHAR(255) NOT NULL,
  prod_id VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  premise_id VARCHAR(255) NOT NULL,
  amount DECIMAL(20,2) DEFAULT '0.00',
  motion_date DATETIME,
  PRIMARY KEY (move_id),
  FOREIGN KEY FK_movements_products (prod_id) REFERENCES products(prod_id),
  FOREIGN KEY FK_movements_to_users (user_id) REFERENCES users(user_id),
  FOREIGN KEY FK_movements_to_premises (premise_id) REFERENCES premises(premise_id)
);

