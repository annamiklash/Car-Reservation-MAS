insert into address (city, street_name, street_number, zip_code)
values ('Warsaw', 'Tyszkiewicza', 13, '01-157'),
       ('Warsaw', 'Dzika', 1, '01-157'),
       ('Warsaw', 'Piekna', 2, '01-001'),
       ('Warsaw', 'Al. Jerozolimskie', 37, '01-121'),
       ('Warsaw', 'Okopowa', 56, '01-137'),
       ('Warsaw', 'Kostruktorska', 10, '01-504');



insert into users (id_user, first_name, last_name, phone_number, username, email, birthdate,
                   id_user_address, discount, admin_access_level, pesel, id_rental_location)
values (1, 'Anna', 'Miklash', 123456789, 'anna', 'anna@email.com', '1994-06-22', 1, 0, null, null, null);

-- update users
-- set birthdate = '1994-06-22'
-- where id_user = 1;

insert into user_type (id_user, user_type)
values (1, 'CUSTOMER');


insert into company(email, company_name, phone_number, id_company_address)
VALUES ('hq_company@gmail.com', 'RENT', 123456789, 2);

insert into rental_location (email, rental_location_name, phone_number, id_rental_location_address, id_company,
                             location_type)
VALUES ('first_rental_location@gmail.com', 'Rent-a-Car', 123456789, 3, 1, 'OPEN'),
       ('second_rental_location@gmail.com', 'HERTZ', 987654321, 4, 1, 'OPEN'),
       ('third_rental_location@gmail.com', 'Cars for Rent', 567891234, 5, 1, 'OPEN');

select *
from rental_location;

insert into business_hours (id_rental_location, day, time_from, time_to)
VALUES (1, 'MONDAY', 8, 20),
       (1, 'TUESDAY', 8, 20),
       (1, 'WEDNESDAY', 8, 20),
       (1, 'THURSDAY', 8, 20),
       (1, 'FRIDAY', 8, 20),
       (1, 'SATURDAY', 8, 20),
       (1, 'SUNDAY', 10, 20);

insert into business_hours (id_rental_location, day, time_from, time_to)
VALUES (2, 'MONDAY', 8, 20),
       (2, 'TUESDAY', 8, 20),
       (2, 'WEDNESDAY', 8, 20),
       (2, 'THURSDAY', 8, 20),
       (2, 'FRIDAY', 8, 20),
       (2, 'SATURDAY', 8, 20),
       (2, 'SUNDAY', 10, 20);

insert into business_hours (id_rental_location, day, time_from, time_to)
VALUES (3, 'MONDAY', 8, 20),
       (3, 'TUESDAY', 8, 20),
       (3, 'WEDNESDAY', 8, 20),
       (3, 'THURSDAY', 8, 20),
       (3, 'FRIDAY', 8, 20),
       (3, 'SATURDAY', 8, 20),
       (3, 'SUNDAY', 10, 20);

select u
from users u
where u.username is not null;


insert into car (id_car, availability, color, daily_rent_cost, horse_power, inspection_date, is_safe_to_drive, make,
                 model, registration_plate, id_rental_location, body_style, manufacture_year, passenger_capacity,
                 miles_per_gallon, miles_per_charge, exhaust_pipe_count, is_self_driving, is_manual_gear_box, type)
VALUES (1, 'AVAILABLE', ARRAY ['white", "black'], 70, 112, '2021-05-01', true, 'BMW', '118i', 'WY12345', 1,
        'HATCHBACK', 2009, 4, 10, null, 2, null, null, 'FUEL'),
       (2, 'AVAILABLE', ARRAY ['gray', 'black'], 90, 100, '2021-05-02', true, 'Mercedes', '118i', 'WY12346', 1, 'SEDAN',
        2012, 5, 14, null, 2, null, null, 'FUEL'),
       (3, 'AVAILABLE', ARRAY ['silver'], 50, 120, '2021-05-03', true, 'Ford', 'Fiesta', 'WY54678', 1, 'SEDAN', 2012,
        5, 16, null, 1, null, null, 'FUEL'),
       (4, 'AVAILABLE', ARRAY ['red'], 120, 130, '2021-05-04', true, 'Hyundai', 'Tucson', 'WA12634', 1, 'SUV', 2012, 5,
        13, null, 1, null, null, 'FUEL'),
       (5, 'AVAILABLE', ARRAY ['white', 'black'], 70, 120, '2021-05-05', true, 'Audi', 'A4', 'WK43246', 2, 'SEDAN',
        2018, 5, 10, null, 2, null, null, 'FUEL'),


       (6, 'AVAILABLE', ARRAY ['green'], 120, 94, '2021-05-06', true, 'Nissan', 'Murano', 'WU54321', 2, 'CONVERTIBLE',
        2017, 2, 30, 50, 1, false, true, 'HYBRID'),
       (7, 'AVAILABLE', ARRAY ['black'], 100, 100, '2021-05-07', true, 'Hyundai', 'i20', 'WL98756', 2, 'HATCHBACK',
        2016, 5, 30, 50, 1, false, true, 'HYBRID'),
       (8, 'AVAILABLE', ARRAY ['silver'], 90, 121, '2021-05-08', true, 'BMW', '325i', 'WW11111', 2, 'SEDAN', 2015, 5,
        32, 50, 1, false, true, 'HYBRID'),
       (9, 'AVAILABLE', ARRAY ['yellow'], 105, 135, '2021-05-09', true, 'Audi', 'Q7', 'WE55555', 3, 'SUV', 2019, 5,
        33, 50, 1, false, true, 'HYBRID'),
       (10, 'AVAILABLE', ARRAY ['black'], 150, 107, '2021-05-10', true, 'Ford', 'Escort', 'WP98706', 3, 'SEDAN', 2020,
        5, 31, 50, 1, false, false, 'HYBRID'),

       (11, 'AVAILABLE', ARRAY ['red'], 140, 140, '2021-05-11', true, 'Chevrolet', 'Camaro', 'WU66666', 3,
        'CONVERTIBLE', 2017, 2, null, 250, null, false, null, 'ELECTRIC'),
       (12, 'AVAILABLE', ARRAY ['white'], 130, 200, '2021-05-12', true, 'Tesla', 'Model S', 'WQ75357', 3, 'HATCHBACK',
        2018,
        5, null, 250, null, true, null, 'ELECTRIC');


insert into insurance(cost_per_day, description, name)
values (30, 'Here goes a detailed description as to what the insurance covers', 'Your Stuff Safe Insurance'),
       (25, 'Here goes a detailed description as to what the insurance covers', 'Dont Stress Insurance'),
       (20, 'Here goes a detailed description as to what the insurance covers', 'Stay Safe Insurance'),
       (10, 'Here goes a detailed description as to what the insurance covers', 'Take Care Insurance'),
       (12, 'Here goes a detailed description as to what the insurance covers', 'Better Health Insurance');



insert into reservation (id_reservation, date_from, date_to, reservation_status, total_people_number,
                         id_car, id_user)
VALUES (uuid_generate_v4(), '2021-07-01', '2021-07-06', 'COMPLETE', 4, 5, 1);

insert into reservation_insurance(id_reservation, id_insurance)
values ((select id_reservation from reservation fetch first row only), 2),
       ((select id_reservation from reservation fetch first row only), 5);
