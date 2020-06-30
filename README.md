Веб сервис для ведения каталога событий (фильмов, театральных представлений, концертов)
Разработать REST-сервис, для добавления и получения событий из базы данных, 
каждое событие после добавления в базу получает уникальный идентификатор, 
которые может быть использован для получения события из базы данных:

Требования:
    Для добавления метода использован метод POST
    Событие добавляется в формате JSON
    Для получения метода так же используется формат JSON
    Имена методов задаются на усмотрение разработчика
    Для постоянного хранения событий нужно использовать базу данных на выбор (можно как реляционную так и не реляционную)
    Возможность локального запуска для тестирования

Используемые технологии:
    Spring Boot
    Сборщик проектов (maven, gradle)
    Любые другие технологии на усмотрение разрабочика
    
Тесты в классе EventControllerTest.

Можно запустить ru.netology.test.TestApplication и потестить через:

    get all events
    curl -v http://localhost:8080/api/v1/events
    
    get event by id
    curl -v http://localhost:8080/api/v1/events/1
    
    get event by name
    curl -v http://localhost:8080/api/v1/events/name/potter
    
    get event by date
    curl -v http://localhost:8080/api/v1/events/later/2005-12-12
    
    create event by json
    curl -v -H "Content-Type: application/json" -X POST -d "@data.json" http://localhost:8080/api/v1/events
    
    update event by json
    curl -H "Content-Type: application/json" -X PUT -d "@data.json" http://localhost:8080/api/v1/events/1
    
    delete event by id
    curl -X DELETE localhost:8080/events/2
    
