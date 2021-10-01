INSERT INTO public.player(id, age, date_created, name, surname, date_updated, months_of_experience) VALUES (1, 23, '2021-09-28 18:33:25.000000', 'Adam', 'Adamczyk', null,10);
INSERT INTO public.team (id, date_created, currency, name, balance , provision) VALUES (1, '2021-09-28 18:33:25.000000', 'GBP', 'Liverpool',100000,0.10);
INSERT INTO public.player_assigment(assgned_player_id, team_owner_id) VALUES (1,1);


INSERT INTO public.player(id, age, date_created, name, surname, date_updated, months_of_experience) VALUES (2, 23, '2021-09-28 18:33:25.000000', 'Adam', 'Adamczyk', null,10);
INSERT INTO public.team (id, date_created, currency, name, balance , provision) VALUES (2, '2021-09-28 18:33:25.000000', 'GBP', 'Liverpool',100000,0.10);
INSERT INTO public.player_assigment(assgned_player_id, team_owner_id) VALUES (2,2);
insert INTO transfer_transaction(id, amount, date_created, player, buyer, seller, exchange_rate, buyer_currency, seller_currency)
VALUES (1, 50.00, '2021-10-01 02:21:39.000000', 2, 1, 2, 20, 'EUR', 'PLN' );


INSERT INTO public.player(id, age, date_created, name, surname, date_updated, months_of_experience) VALUES (4, 23, '2021-09-28 18:33:25.000000', 'Adam', 'Adamczyk', null,10);
INSERT INTO public.team (id, date_created, currency, name, balance , provision) VALUES (4, '2021-09-28 18:33:25.000000', 'GBP', 'Liverpool',100000,0.10);
INSERT INTO public.player_assigment(assgned_player_id, team_owner_id) VALUES (4,4);
