USE analytics;
SET SQL_SAFE_UPDATES=0;
INSERT INTO visitor (age, trackingId) VALUES("20", "4656764467"),("25", "4656761467"),("35", "4657961467");
INSERT INTO location (city, country) VALUES("Kharkiv", "Ukraine"),("Warszawa", "Poland");
INSERT INTO navigation (browser, device, language, platform, provider)VALUES("Opera", "PC", "uk", "Windows", "Kharkiv Online"),("Chrome",  "PC", "uk", "Windows", "Poland Online"),("Internet Explorer",  "PC", "uk", "Windows", "Kharkiv Online");
INSERT INTO session (closed, open, status, location_id, navigation_id, visitor_id)VALUES("2015-03-20 15:00:00", "2017-03-20 14:00:00", 1, 1, 1, 1),("2015-03-19 15:00:00", "2015-03-19 14:00:00", 1, 2, 2, 2),("2015-04-21 15:00:00", "2017-04-21 14:00:00", 1, 1, 3, 3);
UPDATE navigation SET session_id=1 WHERE id=1;
UPDATE navigation SET session_id=2 WHERE id=2;
UPDATE navigation SET session_id=3 WHERE id=3;
INSERT INTO event (category, action, label, description, date, session_id) VALUES("basket", "add", "Product 1/23432423/456.34", "product number 1", "2015-03-20 15:00:00", "1"),("basket", "add", "Product 2/23432426/25", "product number 2", "2015-03-21 15:00:00", "2");
INSERT INTO event (category, action, label, description, date, session_id) VALUES("order", "placed", "23432423", "345,345345", "2015-03-20 15:00:00", "1"),("order", "placed", "7684345234", "983984,8757634,542381", "2015-03-25 15:00:00", "2");
INSERT INTO organic_referer (value) VALUES("mail.ru"),("google.com"),("daum.net"),("eniro.se"),("naver.com"),("yahoo.com"),("msn.com"),("bing.com"),("aol.com"),("lycos.com"),("ask.com"),("altavista.com"),("search.netscape.com"),("cnn.com"),("about.com"),("mamma.com"),("alltheweb.com"),("voila.fr"),("search.virgilio.it"),("baidu.com"),("alice.com"),("yandex.com"),("najdi.org.mk"),("aol.com"),("mamma.com"),("seznam.cz"),("search.com"),("wp.pl"),("online.onetcenter.org"),("szukacz.pl"),("yam.com"),("pchome.com"),("kvasir.no"),("sesam.no"),("ozu.es"),("terra.com"),("mynet.com"),("ekolay.net"),("rambler.ru");
INSERT INTO page (path, title) VALUES ("/basket", "Basket"),("/news", "News"),("/contact-us", "Contact Us");
INSERT INTO page_view (created, referer, page_id, session_id) VALUES ("2015-03-20 15:00:00", "https://www.google.com.ua/url?sa=t&rct=j&q=&esrc=s&source=web&cd=6&ved=0CDYQFjAF&url=https%3A%2F%2Fwww.ruelala.com%2F&ei=NvYgVavYDISisAGPu4OYCA&usg=AFQjCNFeQhICGZ8m4qJMIwbDaQyE4-rOow&sig2=cx7DtkdnCmnL0w6nwcEv5g", "1", "1"),("2015-03-21 15:00:00", "http://shop.dynalias.org/news", "3", "2"), ("2015-03-22 15:00:00", "http://shop.dynalias.org/contact-us", "1", "2");
INSERT INTO transaction (id, revenue, shipping, tax, session_id) VALUES ("1", "0", "0", "0", "1"), ("2", "0", "0", "0", "2");
INSERT INTO transaction_entry (id, SKU, category, name, price, quantity, transaction_id) VALUES ("1", "67464", "phone", "Meizu mx1", "250", "1", "1"), ("2", "98764", "phone", "Motorola moto g", "150", "1", "2");

