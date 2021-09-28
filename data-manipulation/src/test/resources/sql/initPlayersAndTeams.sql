INSERT INTO public.team (id, date_created, currency, name) VALUES (1, '2021-09-28 18:33:25.000000', 'GBP', 'Liverpool');
INSERT INTO public.team (id, date_created, currency, name) VALUES (2, '2021-09-28 18:33:25.000000', 'EUR', 'Borrusia Dortmund');
INSERT INTO public.team (id, date_created, currency, name) VALUES (3, '2021-09-28 18:33:25.000000', 'PLN', 'Pilica Koniecpol');
INSERT INTO public.team (id, date_created, currency, name) VALUES (4, '2021-09-28 18:33:25.000000', 'EUR', 'Juventus Turyn ');

INSERT INTO public.player(id, age, date_created, name, surname, date_updated) VALUES (1, 23, '2021-09-28 18:33:25.000000', 'Adam', 'Adamczyk', null);
INSERT INTO public.player(id, age, date_created, name, surname, date_updated) VALUES (2, 43, '2021-09-28 18:33:25.000000', 'Tomasz', 'Nowak', null);
INSERT INTO public.player(id, age, date_created, name, surname, date_updated) VALUES (3, 33, '2021-09-28 18:33:25.000000', 'Borys', 'Szewczenko', null);
INSERT INTO public.player(id, age, date_created, name, surname, date_updated) VALUES (4, 27, '2021-09-28 18:33:25.000000', 'Krzysztof', 'Ącki', null);

INSERT INTO public.player_assigment(assgned_player_id, team_owner_id) VALUES (1,2);
INSERT INTO public.player_assigment(assgned_player_id, team_owner_id) VALUES (1,3);
