create table company(
	id integer NOT NULL, 
	name character varying, 
	CONSTRAINT company_pkey PRIMARY KEY (id));

create table person(
	id integer NOT NULL, 
	name character varying, 
	company_id integer references company(id), 
	CONSTRAINT person_pkey PRIMARY KEY (id));
	
insert into company (id, name) values 
    (1, 'Company1'),
    (2, 'Company2'),
    (3, 'Company3'),
    (4, 'Company4'),
    (5, 'Company5');

insert into person (id, name, company_id) values 
	(1, 'Max', 1),
	(2, 'Piter', 1),
	(3, 'Jon', 1),
	(4, 'Cruze', 2),
	(5, 'Anna', 2),
	(6, 'Sofia', 2),
	(7, 'Ignat', 2),
	(8, 'Kolia', 3),
	(9, 'Anton', 3),
	(10, 'Irina', 3),
	(11, 'Kirill', 4),
	(12, 'Nastia', 4),
	(13, 'Vova', 4),
	(14, 'Marina', 4),
	(15, 'Maria', 4),
	(16, 'Angela', 5),
	(17, 'Semen', 5),
	(18, 'Nadegda', 5),
	(19, 'Elena', 5),
	(20, 'Vika', 5);
	
select c.name, p.name from person p 
join company c on c.id = p.company_id
where c.id != 5;

select c.name, count(p.name) as count_person
from company c
join person p on c.id = p.company_id
group by c.name
having count(p.name) = (select count(p.name) from person p
                        group by p.company_id
                        order by count(p.company_id) desc
                        limit 1);
;

select c.name, count(p.name) as count_person
from company c
join person p on c.id = p.company_id
group by c.name
having count(p.name) = (select max(max_count) 
						from (select count(p.name) as max_count
						      from company c
							  join person p on c.id = p.company_id
						      group by c.name) as max_person);