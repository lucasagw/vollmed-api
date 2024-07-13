alter table doctors add column active tinyint;
update doctors set active = 1;
alter table doctors MODIFY active TINYINT not null;