USE analytics;
SET SQL_SAFE_UPDATES=0;
INSERT INTO visitor (age, trackingId) VALUES("20", "4656764467"),("25", "4656761467"),("35", "4657961467");
INSERT INTO location (city, country) VALUES("Kharkiv", "Ukraine"),("Warszawa", "Poland");
INSERT INTO navigation (browser, device, language, platform, provider)VALUES("Opera", "PC", "uk", "Windows", "Kharkiv Online"),("Chrome",  "PC", "uk", "Windows", "Poland Online"),("Internet Explorer",  "PC", "uk", "Windows", "Kharkiv Online");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id)VALUES("2015-03-20 15:00:00", "2015-03-20 14:00:00", 1, 1, 1, 1),("2015-03-19 15:00:00", "2015-03-19 14:00:00", 1, 2, 2, 2),("2015-04-21 15:00:00", "2017-04-21 14:00:00", 1, 1, 3, 3);
UPDATE navigation SET session_id=1 WHERE id=1;
UPDATE navigation SET session_id=2 WHERE id=2;
UPDATE navigation SET session_id=3 WHERE id=3;
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "product number 1", "2015-03-20 15:00:00", "1"),("basket", "add", "Product 2/23432426/25", "product number 2", "2015-03-21 15:00:00", "2");
INSERT INTO event (category, action, label, description, date, session_id) VALUES("order", "placed", "23432423", "345,345345", "2015-03-20 15:00:00", "1"),("order", "placed", "7684345234", "983984,8757634,542381", "2015-03-25 15:00:00", "2");
INSERT INTO organic_referer (value) VALUES("mail.ru"),("google.com"),("daum.net"),("eniro.se"),("naver.com"),("yahoo.com"),("msn.com"),("bing.com"),("aol.com"),("lycos.com"),("ask.com"),("altavista.com"),("search.netscape.com"),("cnn.com"),("about.com"),("mamma.com"),("alltheweb.com"),("voila.fr"),("search.virgilio.it"),("baidu.com"),("alice.com"),("yandex.com"),("najdi.org.mk"),("aol.com"),("mamma.com"),("seznam.cz"),("search.com"),("wp.pl"),("online.onetcenter.org"),("szukacz.pl"),("yam.com"),("pchome.com"),("kvasir.no"),("sesam.no"),("ozu.es"),("terra.com"),("mynet.com"),("ekolay.net"),("rambler.ru");
INSERT INTO page (path, title) VALUES ("/basket", "Basket"),("/news", "News"),("/contact-us", "Contact Us");
INSERT INTO page_view (created, referer, page_id, session_id) VALUES ("2015-03-20 15:00:00", "https://www.google.com.ua/url?sa=t&rct=j&q=&esrc=s&source=web&cd=6&ved=0CDYQFjAF&url=https%3A%2F%2Fwww.ruelala.com%2F&ei=NvYgVavYDISisAGPu4OYCA&usg=AFQjCNFeQhICGZ8m4qJMIwbDaQyE4-rOow&sig2=cx7DtkdnCmnL0w6nwcEv5g", "1", "1"),("2015-03-21 15:00:00", "http://shop.dynalias.org/news", "3", "2"), ("2015-03-22 15:00:00", "http://shop.dynalias.org/contact-us", "1", "2");
INSERT INTO transaction (id, revenue, shipping, tax, session_id) VALUES ("1", "0", "0", "0", "1"), ("2", "0", "0", "0", "2");
INSERT INTO transaction_entry (id, SKU, category, name, price, quantity, transaction_id) VALUES ("1", "54546434", "phone", "Meizu mx1", "456.34", "1", "1"), ("2", "98764", "phone", "Motorola moto g", "150", "1", "2");

#2015-05-04
INSERT INTO visitor (age, trackingId) VALUES("28", "4656953467");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-04 15:00:00", "2015-05-04 14:00:00", "1", "1", "1", 4);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-04 14:30:00", 4);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 4);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 11, 3);

#2015-05-05
INSERT INTO visitor (age, trackingId) VALUES("38", "4617453467");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-05 15:00:00", "2015-05-05 14:00:00", "1", "1", "1", 4);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-05 14:30:00", 5);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 5);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 5, 4);

#2015-05-06
INSERT INTO visitor (age, trackingId) VALUES("18", "4017453467");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-06 15:00:00", "2015-05-06 14:00:00", "1", "1", "3", 5);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-06 14:30:00", 6);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 6);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 6, 5);

#2015-05-07
INSERT INTO visitor (age, trackingId) VALUES("18", "4017450067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-07 15:00:00", "2015-05-07 14:00:00", "1", "1", "3", 6);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-07 14:30:00", 7);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 7);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 5, 6);

#2015-05-08
INSERT INTO visitor (age, trackingId) VALUES("19", "4017250067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-08 15:00:00", "2015-05-08 14:00:00", "1", "1", "2", 7);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-08 14:30:00", 8);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 8);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 9, 7);

#2015-05-09
INSERT INTO visitor (age, trackingId) VALUES("49", "4019950067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-09 15:00:00", "2015-05-09 14:00:00", "1", "1", "1", 8);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-09 14:30:00", 9);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 9);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 9, 8);

#2015-05-10
INSERT INTO visitor (age, trackingId) VALUES("21", "3217250067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-10 15:00:00", "2015-05-10 14:00:00", "1", "1", "2", 9);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-10 14:30:00", 10);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 10);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 4, 9);

#2015-05-11
INSERT INTO visitor (age, trackingId) VALUES("17", "9617250067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-11 15:00:00", "2015-05-11 14:00:00", "1", "1", "3", 10);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-11 14:30:00", 11);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 11);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 8, 10);

#2015-05-12
INSERT INTO visitor (age, trackingId) VALUES("15", "1117250067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-12 15:00:00", "2015-05-12 14:00:00", "1", "1", "2", 11);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-12 14:30:00", 12);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 12);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 6, 11);

#2015-05-13
INSERT INTO visitor (age, trackingId) VALUES("20", "4627250067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-13 15:00:00", "2015-05-13 14:00:00", "1", "1", "1", 12);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-13 14:30:00", 13);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 13);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 3, 12);

#2015-05-14
INSERT INTO visitor (age, trackingId) VALUES("22", "4019450067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-14 15:00:00", "2015-05-14 14:00:00", "1", "1", "1", 13);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-14 14:30:00", 14);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 14);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 7, 13);

#2015-05-15
INSERT INTO visitor (age, trackingId) VALUES("23", "4060250067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-15 15:00:00", "2015-05-15 14:00:00", "1", "1", "1", 14);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-15 14:30:00", 15);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 15);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 5, 14);

#2015-05-16
INSERT INTO visitor (age, trackingId) VALUES("24", "40172043067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-16 15:00:00", "2015-05-16 14:00:00", "1", "1", "1", 15);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-16 14:30:00", 16);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 16);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 5, 15);

#2015-05-17
INSERT INTO visitor (age, trackingId) VALUES("21", "4317250067");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id) VALUES("2015-05-17 15:00:00", "2015-05-17 14:00:00", "1", "1", "2", 16);
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Meizu mx1/54546434/456.34", "Meizu mx1", "2015-05-17 14:30:00", 17);
INSERT INTO transaction (revenue, shipping, tax, session_id) VALUES ("0", "0", "0", 17);
INSERT INTO transaction_entry (SKU, category, name, price, quantity, transaction_id) VALUES ("54546434", "phone", "Meizu mx1", "456.34", 6, 16);