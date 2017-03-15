INSERT INTO users(user_id, username, role, created_by, email, password_hash, last_login)
  VALUES ('1000', 'root', 'Root', '1000', 'root@root.com', 'd8578edf8458ce06fbc5bb76a58c5ca4', '2017-01-01 10:00:00' );
INSERT INTO users(user_id, username, role, created_by, email, password_hash, last_login)
  VALUES ('2000', 'admin1', 'Admin', '1000', 'admin1@nnm.ru', 'z85sdedf8458ce06fbc5bb76a58c5ca4', '2017-02-01 10:00:00' );
INSERT INTO users(user_id, username, role, created_by, email, password_hash, last_login)
  VALUES ('3000', 'user1', 'User', '2000', 'user1@nnm.ru', 's85sdedf8458ce06fbc5bb76a58c5ca4', '2017-02-01 20:10:00' );
INSERT INTO users(user_id, username, role, created_by, email, password_hash, last_login)
  VALUES ('3001', 'user2', 'User', '2000', 'user2@nnm.ru', 's85sdedf8458ce06fbc5bb76a58c5ca4', '2017-02-01 20:11:00' );
INSERT INTO users(user_id, username, role, created_by, email, password_hash, last_login)
  VALUES ('3002', 'user3', 'User', '2000', 'user3@nnm.ru', 's85sdedf8458ce06fbc5bb76a58c5ca4', '2017-02-01 20:12:00' );
INSERT INTO users(user_id, username, role, created_by, email, password_hash, last_login)
  VALUES ('3003', 'user4', 'User', '2000', 'user4@nnm.ru', 's85sdedf8458ce06fbc5bb76a58c5ca4', '2017-03-01 20:12:00' );

INSERT INTO  userdetails (user_id, city, address, phone, first_name, mid_init, last_name, sex, notes, activation_date)
  VALUES('1000','Kiev', 'Poderevinko str. 12', '380503212121', 'Abram', 'Lvovch', 'Cukerman', 'male', 'a very responsible person', '2017-01-01 00:00:00');
INSERT INTO  userdetails (user_id, city, address, phone, first_name, mid_init, last_name, sex, notes, activation_date)
  VALUES('2000','Kiev', 'Victory str. 22/32', '380503212343', 'Eva', 'Ivanovna', 'Nightingale', 'fimale', 'first admin', '2017-01-01 10:00:00');
INSERT INTO  userdetails (user_id, city, address, phone, first_name, mid_init, last_name, sex, notes, activation_date)
  VALUES('3000','Kiev', 'King Power str. 1/32', '380501234321', 'Ivan', 'Ivanov', 'Glass', 'male', 'first user', '2017-01-01 10:00:00');
INSERT INTO  userdetails (user_id, city, address, phone, first_name, mid_init, last_name, sex, notes, activation_date)
  VALUES('3001','Kiev', 'Powerman str. 10/2', '380503452121', 'Petro', 'Abromov', 'Doubleglass', 'male', 'second user', '2017-01-01 11:00:00');
INSERT INTO  userdetails (user_id, city, address, phone, first_name, mid_init, last_name, sex, notes, activation_date)
  VALUES('3002','Kiev', 'King str. 1/32', '380504234321', 'Ivan', 'Mihailovich', 'Halfglass', 'male', 'thrid user', '2017-01-01 12:00:00');
INSERT INTO  userdetails (user_id, city, address, phone, first_name, mid_init, last_name, sex, notes, activation_date)
  VALUES('3003','Kiev', 'Queen str. 12/39', '380994234321', 'Zinaida', 'Petrovna', 'Kuhelbeker', 'female', 'fourth user', '2017-03-11 19:00:00');

INSERT INTO groups(group_id, group_name, description)
  VALUES(1000, 'Friendly citizens', 'Community about 1000 peoples that are located in Kiev');
INSERT INTO groups(group_id, group_name, description)
  VALUES(1001, 'Fast wheels', 'Groups of people with vehicles for delivering');

INSERT INTO user_group(group_id, user_id)
  VALUES(1000,3000);
INSERT INTO user_group(group_id, user_id)
  VALUES(1000,3001);
INSERT INTO user_group(group_id, user_id)
  VALUES(1001,3002);
INSERT INTO user_group(group_id, user_id)
  VALUES(1000,3003);

INSERT INTO classes(class_id, name, description)
  VALUES(1000, 'Outerwear', 'Outerwear for all');
INSERT INTO classes(class_id, name, description)
  VALUES(1001, 'Underwear', 'Underwear for all');
INSERT INTO classes(class_id, name, description)
  VALUES(1002, 'Foodstuffs', 'Foodstuffs for all');
INSERT INTO classes(class_id, name, description)
  VALUES(1003, 'Money', 'Money for all');

INSERT INTO units(unit_id, name)
  VALUES (1000, 'kg');
INSERT INTO units(unit_id, name)
  VALUES (1001, 'pc');
INSERT INTO units(unit_id, name)
  VALUES (1002, 'hrn');

INSERT INTO products(prod_id, name, description, class_id, unit_id, added_by)
  VALUES('100', 'Overcoat', 'overcoat for winter time', '1000', '1001', '2000');
INSERT INTO products(prod_id, name, description, class_id, unit_id, added_by)
  VALUES('101', 'Hat', 'Hat with with ear flaps', '1000', '1001', '2000');
INSERT INTO products(prod_id, name, description, class_id, unit_id, added_by)
  VALUES('102', 'Cash', 'Cash for paltry things', '1003', '1002', '2000');

INSERT INTO stash(prod_id, user_id, amount, required_amount, status, deadline)
  VALUES('100','3000','10','100','Active','2017-12-01 00:00:00');
INSERT INTO stash(prod_id, user_id, amount, required_amount, status, deadline)
  VALUES('100','3001','0','50','Active','2017-11-20 00:00:00');
INSERT INTO stash(prod_id, user_id, amount, required_amount, status, deadline)
  VALUES('101','3000','50','50','Closed','2017-03-10 00:00:00');
INSERT INTO stash(prod_id, user_id, amount, required_amount, status, deadline)
  VALUES('102','3002','1000.50','2000','Suspended','2017-05-10 00:00:00');
INSERT INTO stash(prod_id, user_id, amount, required_amount, status, deadline)
  VALUES('102','3001','100.50','2000','Active','2017-10-10 00:00:00');

INSERT INTO premises(premise_id, description)
  VALUES('100','Share cash  Ivan Glass-> Ivan Halfglass');
INSERT INTO premises(premise_id, description)
  VALUES('101','Collected spare hats in one hands');
INSERT INTO premises(premise_id, description)
  VALUES('102','Pass overcoats to ATO');

INSERT INTO movements(move_id, prod_id, user_id, premise_id, amount, motion_date)
  VALUES('100','102','3000','100','-1000.50','2017-01-01 20:00:00');
INSERT INTO movements(move_id, prod_id, user_id, premise_id, amount, motion_date)
  VALUES('101','102','3002','100','1000.50','2017-01-01 20:00:00');
INSERT INTO movements(move_id, prod_id, user_id, premise_id, amount, motion_date)
  VALUES('102','101','3000','101','50','2017-02-01 20:00:00');
INSERT INTO movements(move_id, prod_id, user_id, premise_id, amount, motion_date)
  VALUES('103','100','3001','102','-100','2017-02-01 20:00:00');