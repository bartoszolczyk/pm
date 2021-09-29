INSERT INTO public.team (id, date_created, currency, name, balance , provision) VALUES (1, '2021-09-28 18:33:25.000000', 'GBP', 'Liverpool',100000,0.10);
INSERT INTO public.team (id, date_created, currency, name, balance , provision) VALUES (2, '2021-09-28 18:33:25.000000', 'EUR', 'Borrusia Dortmund',77,0.09);
INSERT INTO public.team (id, date_created, currency, name, balance , provision) VALUES (3, '2021-09-28 18:33:25.000000', 'PLN', 'Pilica Koniecpol',100000,0.01);
INSERT INTO public.team (id, date_created, currency, name, balance , provision) VALUES (4, '2021-09-28 18:33:25.000000', 'EUR', 'Juventus Turyn ',100000,0.03);

INSERT INTO public.player(id, age, date_created, name, surname, date_updated, months_of_experience) VALUES (1, 23, '2021-09-28 18:33:25.000000', 'Adam', 'Adamczyk', null,10);
INSERT INTO public.player(id, age, date_created, name, surname, date_updated, months_of_experience) VALUES (2, 43, '2021-09-28 18:33:25.000000', 'Tomasz', 'Nowak', null,15);
INSERT INTO public.player(id, age, date_created, name, surname, date_updated, months_of_experience) VALUES (3, 33, '2021-09-28 18:33:25.000000', 'Borys', 'Szewczenko', null,40);
INSERT INTO public.player(id, age, date_created, name, surname, date_updated, months_of_experience) VALUES (4, 27, '2021-09-28 18:33:25.000000', 'Krzysztof', 'Ącki', null,500);

INSERT INTO public.player_assigment(assgned_player_id, team_owner_id) VALUES (1,2);
INSERT INTO public.player_assigment(assgned_player_id, team_owner_id) VALUES (1,3);
