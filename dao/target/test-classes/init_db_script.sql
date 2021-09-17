INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (1, 'First', 'Some description 1', 50, 90, '2021-08-12T08:12:15', '2021-08-12T08:12:15');
INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (2, 'Second', 'Some description 2', 70, 42, '2021-08-12T08:12:15', '2021-08-12T08:12:15');
INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (3, 'Third', 'Some description 3', 10, 120, '2021-08-12T08:12:15', '2021-08-12T08:12:15');
INSERT INTO gift_certificates (id, name, description, price, duration, create_date, last_update_date) 
VALUES (4, 'Fourth', 'Some description 4', 30, 30, '2021-08-12T08:12:15', '2021-08-12T08:12:15');

INSERT INTO tags (`id`, `name`) VALUES ('1', 'tag1');
INSERT INTO tags (`id`, `name`) VALUES ('2', 'tag2');
INSERT INTO tags (`id`, `name`) VALUES ('3', 'tag3');

INSERT INTO gift_certificates_tags (`gift_certificate_id`, `tag_id`) VALUES ('2', '1');
INSERT INTO gift_certificates_tags (`gift_certificate_id`, `tag_id`) VALUES ('2', '3');