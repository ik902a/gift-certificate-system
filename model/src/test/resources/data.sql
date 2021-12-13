INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (1, 'First', 'Some description 1', 50, 90, '2021-08-12T08:12:15', '2021-08-12T08:12:15');
INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (2, 'Second', 'Some description 2', 70, 42, '2021-08-12T08:12:15', '2021-08-12T08:12:15');
INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (3, 'Third', 'Some description 3', 10, 120, '2021-08-12T08:12:15', '2021-08-12T08:12:15');
INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (4, 'Fourth', 'Some description 4', 30, 30, '2021-08-12T08:12:15', '2021-08-12T08:12:15');

INSERT INTO tags (id, name) VALUES ('1', 'tag1');
INSERT INTO tags (id, name) VALUES ('2', 'tag2');
INSERT INTO tags (id, name) VALUES ('3', 'tag3');

INSERT INTO gift_certificates_tags (gift_certificate_id, tag_id) VALUES ('2', '1');
INSERT INTO gift_certificates_tags (gift_certificate_id, tag_id) VALUES ('2', '3');

INSERT INTO users (id, login, password, role) VALUES ('1', 'user1', 'password', 'ROLE_USER');
INSERT INTO users (id, login, password, role) VALUES ('2', 'user2', 'password', 'ROLE_USER');
INSERT INTO users (id, login, password, role) VALUES ('3', 'user3', 'password', 'ROLE_USER');

INSERT INTO orders (id, date, cost, user_id) VALUES ('1', '2021-08-12T08:12:15', 55.55, 1);
INSERT INTO orders (id, date, cost, user_id) VALUES ('2', '2021-08-12T08:12:15', 45.45, 1);
INSERT INTO orders (id, date, cost, user_id) VALUES ('3', '2021-08-12T08:12:15', 65.65, 2);

INSERT INTO gift_certificates_orders (gift_certificate_id, order_id, quantity) VALUES ('2', '1', 3);
INSERT INTO gift_certificates_orders (gift_certificate_id, order_id, quantity) VALUES ('2', '3', 2);