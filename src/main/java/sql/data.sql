insert into mas.address (city, street_name, street_number, zip_code)
values ('Warsaw', 'Tyszkiewicza', 13, '01-157'),
       ('Warsaw', 'Dzika', 1, '01-157'),
       ('Warsaw', 'Piekna', 2, '01-001'),
       ('Warsaw', 'Al. Jerozolimskie', 37, '01-121'),
       ('Warsaw', 'Okopowa', 56, '01-137'),
       ('Warsaw', 'Kostruktorska', 10, '01-504');

insert into mas.user (id_customer, first_name, last_name, phone_number, username, email, birthdate,
                          id_customer_address, level)
values (1, 'Anna', 'Miklash', 123456789, 'anna', 'anna@email.com', '1994-06-22', 1, 'Regular');


insert into mas.company(email, company_name, phone_number, id_company_address)
VALUES ('hq_company@gmail.com', 'RENT', 123456789, 2);

insert into mas.rental_location (email, rental_location_name, phone_number, id_rental_location_address, id_company)
VALUES ('first_rental_location@gmail.com', 'Rent-a-Car', 123456789, 3, 1),
       ('second_rental_location@gmail.com', 'HERTZ', 987654321, 4, 1),
       ('third_rental_location@gmail.com', 'Cars for Rent', 567891234, 5, 1);


insert into mas.business_hours (id_rental_location, day, time_from, time_to)
VALUES (1, 'MONDAY', 8, 20),
       (1, 'TUESDAY', 8, 20),
       (1, 'WEDNESDAY', 8, 20),
       (1, 'THURSDAY', 8, 20),
       (1, 'FRIDAY', 8, 20),
       (1, 'SATURDAY', 8, 20),
       (1, 'SUNDAY', 10, 20);

insert into mas.business_hours (id_rental_location, day, time_from, time_to)
VALUES (2, 'MONDAY', 8, 20),
       (2, 'TUESDAY', 8, 20),
       (2, 'WEDNESDAY', 8, 20),
       (2, 'THURSDAY', 8, 20),
       (2, 'FRIDAY', 8, 20),
       (2, 'SATURDAY', 8, 20),
       (2, 'SUNDAY', 10, 20);

insert into mas.business_hours (id_rental_location, day, time_from, time_to)
VALUES (3, 'MONDAY', 8, 20),
       (3, 'TUESDAY', 8, 20),
       (3, 'WEDNESDAY', 8, 20),
       (3, 'THURSDAY', 8, 20),
       (3, 'FRIDAY', 8, 20),
       (3, 'SATURDAY', 8, 20),
       (3, 'SUNDAY', 10, 20);


insert into mas.car (id_car, availability, color, daily_rent_cost, horse_power, inspection_date, is_safe_to_drive, make,
                     model, registration_plate, id_rental_location, body_style, manufacture_year, passenger_capacity)
VALUES (1, 'AVAILABLE', ARRAY ['white", "black'], 70, 112, '2021-05-01', true, 'BMW', '118i', 'WY12345', 1,
        'HATCHBACK', 2009, 4),
       (2, 'AVAILABLE', ARRAY ['gray', 'black'], 90, 100, '2021-05-02', true, 'Mercedes', '118i', 'WY12346', 1, 'SEDAN',
        2012, 5),
       (3, 'AVAILABLE', ARRAY ['silver'], 50, 120, '2021-05-03', true, 'Ford', 'Fiesta', 'WY54678', 1, 'SEDAN', 2012,
        5),
       (4, 'AVAILABLE', ARRAY ['red'], 120, 130, '2021-05-04', true, 'Hyundai', 'Tucson', 'WA12634', 1, 'SUV', 2012, 5),
       (5, 'AVAILABLE', ARRAY ['white', 'black'], 70, 120, '2021-05-05', true, 'Audi', 'A4', 'WK43246', 2, 'SEDAN',
        2018, 5),
       (6, 'AVAILABLE', ARRAY ['green'], 120, 94, '2021-05-06', true, 'Nissan', 'Murano', 'WU54321', 2, 'CONVERTIBLE',
        2017, 2),
       (7, 'AVAILABLE', ARRAY ['black'], 100, 100, '2021-05-07', true, 'Hyundai', 'i20', 'WL98756', 2, 'HATCHBACK',
        2016, 5),
       (8, 'AVAILABLE', ARRAY ['silver'], 90, 121, '2021-05-08', true, 'BMW', '325i', 'WW11111', 2, 'SEDAN', 2015, 5),
       (9, 'AVAILABLE', ARRAY ['yellow'], 105, 135, '2021-05-09', true, 'Audi', 'Q7', 'WE55555', 3, 'SUV', 2019, 5),
       (10, 'AVAILABLE', ARRAY ['black'], 150, 107, '2021-05-10', true, 'Ford', 'Escort', 'WP98706', 3, 'SEDAN', 2020,
        5),
       (11, 'AVAILABLE', ARRAY ['red'], 140, 140, '2021-05-11', true, 'Chevrolet', 'Camaro', 'WU66666', 3,
        'CONVERTIBLE', 2017, 2),
       (12, 'AVAILABLE', ARRAY ['white'], 80, 200, '2021-05-12', true, 'BMW', '118i', 'WQ75357', 3, 'HATCHBACK', 2014,
        4);


insert into mas.insurance (additional_condition, cost_per_day, description, name)
VALUES ('Somehow manage to catch a bird but have no idea what to do next, so play with it until it dies of shock a nice warm laptop ' ||
        'for me to sit on munch, munch, chomp, chomp so cats are a queer kind of folk love and waffles and wake up human for food at 4am.',
        30, 'Here goes a detailed description as to what the insurance covers', 'Best Insurance Ever'),
       ('tares at human while pushing stuff off a table. Lick butt thug cat . Poop on the floor, break a planter, sprint, eat own hair, vomit hair, ' ||
        'hiss, chirp at birds, eat a squirrel, hide from fireworks,',
        20, 'Here goes a detailed description as to what the insurance covers', 'Stay Safe Insurance');

insert into mas.insurance(additional_price_per_passenger, cost_per_day, description, name)
values (10, 23, 'Here goes a detailed description as to what the insurance covers', 'Take Care Insurance'),
       (12, 20, 'Here goes a detailed description as to what the insurance covers', 'Better Health Insurance');

insert into mas.insurance(cost_per_day, description, personal_effects_value, name)
values (30, 'Here goes a detailed description as to what the insurance covers', 2000, 'Your Stuff Safe Insurance'),
       (25, 'Here goes a detailed description as to what the insurance covers', 1000, 'Dont Stress Insurance');


insert into mas.insurance_type(id_insurance, insurance_type)
values (1, 'COLLISION_DAMAGE_INSURANCE'),
       (2, 'COLLISION_DAMAGE_INSURANCE'),
       (3, 'PERSONAL_ACCIDENTS_INSURANCE'),
       (4, 'PERSONAL_ACCIDENTS_INSURANCE'),
       (5, 'PERSONAL_EFFECTS_COVERAGE'),
       (6, 'PERSONAL_EFFECTS_COVERAGE');


insert into mas.reservation (id_reservation, date_from, date_to, reservation_status, total_people_number,
                             id_car, id_customer)
VALUES (uuid_generate_v4(), '2021-07-01', '2021-07-06', 'CREATED', 4, 5, 1);


insert into mas.reservation_insurance(id_reservation, id_insurance)
values ('75bf5bab-982e-49f6-bf66-895b7899413e', 2),
       ('75bf5bab-982e-49f6-bf66-895b7899413e', 6);


