insert into USERS(username, password, user_role, email, reputation) values (
'1', '$2a$10$0wOzbNKWDqP3m3DOIISObuIsS.Tb8kpzv5Jh5yVsj50TlIb4E/hcG', 'USER', 'www.refwotwot@gmail.com', 0
                                                        );
insert into USERS( username, password, user_role, email, reputation) values (
'2', '$2a$10$0wOzbNKWDqP3m3DOIISObuIsS.Tb8kpzv5Jh5yVsj50TlIb4E/hcG', 'USER', 'www.refwotwot2@gmail.com', 0
                                                                               );

insert into USER_SKILLS(user_id, skill) values (
1, 'JAVA'
                                               );

insert into USER_SKILLS(user_id, skill) values (
1, 'SPRING'
                                               );
insert into USER_SKILLS(user_id, skill) values (
2, 'JAVA2'
                                               );

insert into USER_SKILLS(user_id, skill) values (
2, 'SPRING2'
                                               );

insert into tasks(name, description, short_description, finished, author_id) values (
'TestTask', 'Task for testing', 'Short description of test task', false, 1
                                                                                        );

insert into tasks(name, description, short_description, finished, author_id) values (
'TestTask2', 'Task for testing2', 'Short description of test task2', false, 2
                                                                                        );
insert into task_tags (task_id, tag) values (
1, 'Java'
                                            );
insert into task_tags (task_id, tag) values (
1, 'Spring'
                                            );
insert into task_tags (task_id, tag) values (
2, 'Java2'
                                            );
insert into task_tags (task_id, tag) values (
2, 'Spring2'
                                            );
insert into volunteers (executor, task_id, user_id) values (
false, 1, 1
                                                               );
insert into volunteers (executor, task_id, user_id) values (
false, 2, 1
                                                               );

