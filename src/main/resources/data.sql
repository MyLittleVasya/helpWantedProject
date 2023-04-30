insert into USERS(id, username, password, user_role, email, reputation) values (
0, '1', '$2a$10$0wOzbNKWDqP3m3DOIISObuIsS.Tb8kpzv5Jh5yVsj50TlIb4E/hcG', 'USER', 'www.refwotwot@gmail.com', 0
                                                        );
insert into USERS(id, username, password, user_role, email, reputation) values (
1, '2', '$2a$10$0wOzbNKWDqP3m3DOIISObuIsS.Tb8kpzv5Jh5yVsj50TlIb4E/hcG', 'USER', 'www.refwotwot2@gmail.com', 0
                                                                               );

insert into USER_SKILLS(user_id, skill) values (
0, 'JAVA'
                                               );

insert into USER_SKILLS(user_id, skill) values (
0, 'SPRING'
                                               );
insert into USER_SKILLS(user_id, skill) values (
1, 'JAVA2'
                                               );

insert into USER_SKILLS(user_id, skill) values (
1, 'SPRING2'
                                               );

insert into tasks(id, name, description, short_description, finished, author_id) values (
0, 'TestTask', 'Task for testing', 'Short description of test task', false, 0
                                                                                        );

insert into tasks(id, name, description, short_description, finished, author_id) values (
1, 'TestTask2', 'Task for testing2', 'Short description of test task2', false, 1
                                                                                        );
insert into task_tags (task_id, tag) values (
0, 'Java'
                                            );
insert into task_tags (task_id, tag) values (
0, 'Spring'
                                            );
insert into task_tags (task_id, tag) values (
1, 'Java2'
                                            );
insert into task_tags (task_id, tag) values (
1, 'Spring2'
                                            );
insert into volunteers (id, executor, task_id, user_id) values (
0, true, 0, 0
                                                               );
insert into volunteers (id, executor, task_id, user_id) values (
1, false, 1, 0
                                                               );

