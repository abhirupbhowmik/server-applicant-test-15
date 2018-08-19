/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (driver_id, date_created, online_status, password, username) values (1, now(),  'OFFLINE',
'driver01pw', 'driver01');

insert into driver (driver_id, date_created,  online_status, password, username) values (2, now(),  'OFFLINE',
'driver02pw', 'driver02');

insert into driver (driver_id, date_created,  online_status, password, username) values (3, now(),  'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (driver_id, date_created,  online_status, password, username) values (4, now(),  'ONLINE',
'driver04pw', 'driver04');

insert into driver (driver_id, date_created,  online_status, password, username) values (5, now(),  'ONLINE',
'driver05pw', 'driver05');

insert into driver (driver_id, date_created,  online_status, password, username) values (6, now(),  'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)
insert into driver (driver_id, coordinate, date_coordinate_updated, date_created,  online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', 
 now(), now(),  'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (driver_id, coordinate, date_coordinate_updated, date_created,  online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127',
 now(), now(),  'ONLINE',
'driver08pw', 'driver08');


INSERT INTO car(car_id, total_seat, car_type, purchase_date, license_no, rating, driver_id, manufacturer)
VALUES(1, 4, 'PETROL', now(),  'PM-1234', 4.5, null,'BMW');

INSERT INTO car(car_id, total_seat, car_type, purchase_date, license_no, rating, driver_id,manufacturer)
VALUES(2, 5, 'DIESEL', now(),  'DM-4567', 5, null,'BMW');

INSERT INTO car(car_id, total_seat, car_type, purchase_date, license_no, rating, driver_id,manufacturer)
VALUES(3, 6, 'ELECTRIC', now(),  'EM-6789', 7,8, 'Benz');

INSERT INTO car(car_id, total_seat, car_type, purchase_date, license_no, rating, driver_id,manufacturer)
VALUES(4, 7, 'HYBRID', now(),  'WB-7001', 9, 4,'Audi');

INSERT INTO car(car_id, total_seat, car_type, purchase_date, license_no, rating, driver_id,manufacturer)
VALUES(5, 7, 'PETROL', now(),  'WB-7010', 2, 5,'Fiat');

INSERT INTO car(car_id, total_seat, car_type, purchase_date, license_no, rating, driver_id,manufacturer)
VALUES(6, 7, 'DIESEL', now(),  'WB-7011', 10, 6,'Suzuki');

