# Хранилище клиентов и их контактов (Java, Spring Boot, Hibernate, PostgreSQL)

## Описание проекта

Созданное приложение является API, который выполняет роль хранилища
информации о клиентах и их контактой информации. Каждый клиент характеризуется именем.
Каждому клиенту в соответствие может быть поставлена информация о его контактах: 
0 и более телефонных номеров, 0 и более адресов электронной почты. 
В приложении реализованы следующие заявленные функции:
- Добавление нового клиента;
- Добавление нового контакта клиента (телефон или email);
- Получение списка клиентов;
- Получение информации по заданному клиенту (по id);
- Получение списка контактов заданного клиента;
- Получение списка контактов заданного типа заданного клиента.

## Решение

Описание решения поставленной задачи включает в себя следующие этапы:
1. Инициализирован проект, в его структуре выделены основные компоненты: контроллеры,
   сервисы, сущности, репозитории и мапперы;
2. Созданы сущности Client и ContactInfo, которые представляют клиентов и их контактную 
информацию (например, телефонные номера и адреса электронной почты), а также сущность
ContactType для хранения возможных типов контактов;
3. Разработаны DTO классы (`ClientAddDto`, `ClientDto`, `ContactInfoAddDto`, `ContactInfoDto`) 
для передачи данных между клиентом и сервером;
4. Реализованы репозитории `ClientRepository`, `ContactInfoRepository` и `ContactTypeRepository`, 
которые предоставляют стандартные CRUD операции для работы с информацией о клиентах, контактах и их типах;
5. Настроена конфигурация Spring Data JPA для взаимодействия с базой данных;
6. В сервисе `ClientService` реализована основная логика работы с клиентами и их контактами 
(добавление клиента, добавление контакта, получение информации о клиенте и его контактах). 
Логика включает обработку данных, преобразование DTO в сущности и взаимодействие с репозиториями;
7. Разработан контроллер `ClientController` для обработки запросов, поступающих от пользователя;
8. Приложение и база данных обернуты в Docker-контейнеры. Для возможности их совместного запуска
   использовался Docker Compose.

Логику работы приложения можно описать следующим образом: пользователь отправляет запрос со всеми
необходимыми данными. Этот запрос обрабатывается контроллером, в котором вызывается соответствующий
метод сервиса. Для получения результата на сервисе происходит взаимодействие с репозиторием, а также преобразования
DTO в сущности и наоборот. По итогу работы приложения пользователь получает необходимую ему информацию,
а также статус ответа (например, 200 в случае успешного ответа на запрос).

## Инструкция по запуску приложения

Чтобы запустить приложение, выполните следующие шаги:
1. Загрузите исходный код проекта на Ваш компьютер (например, клонируйте репозиторий с
   GitHub или скачайте код в ввиде архива);
2. Перейдите в каталог проекта;
3. Убедитесь, что Docker и Docker Compose установлены на Вашем компьютере;
4. В терминале выполните команду для сборки и запуска Docker-контейнеров:
```
docker-compose up
```
После того, как контейнеры будут запущены, приложение будет доступно по следующему адресу:
http://localhost:8080/api/storage

## Примеры тестовых запросов

### 1. Добавление нового клиента

**Endpoint:** `http://localhost:8080/api/storage/new_client`

**Метод:** `POST`

**Тело запроса:**
```json
{
  "name": "test"
}
```

**Ответ:**
```json
{
  "id": 1,
  "name": "test"
}
```

### 2. Добавление нового контакта клиента

**Endpoint:** `http://localhost:8080/api/storage/new_contact`

**Метод:** `POST`

**Тело запроса:**
```json
{
  "clientId": 1,
  "contactTypeName": "email",
  "value": "test@gmail.com"
}
```

**Ответ:**
```json
{
  "id": 1,
  "contactTypeName": "email",
  "value": "test@gmail.com"
}
```

### 3. Получение списка клиентов

**Endpoint:** `http://localhost:8080/api/storage/clients`

**Метод:** `GET`

**Ответ:**
```json
[
  {
    "id": 1,
    "name": "test"
  }
]
```

### 4. Получение информации по заданному клиенту

**Endpoint:** `http://localhost:8080/api/storage/client/{id}`

**Метод:** `GET`

**Параметры:** `id = 1`

**Ответ:**
```json
{
  "id": 1,
  "name": "test"
}
```

### 5. Получение списка контактов заданного клиента

**Endpoint:** `http://localhost:8080/api/storage/contacts/{clientId}`

**Метод:** `GET`

**Параметры:** `clientId = 1`

**Ответ:**
```json
[
  {
    "id": 1,
    "contactTypeName": "email",
    "value": "test@gmail.com"
  }
]
```

### 6. Получение списка контактов заданного типа заданного клиента

**Endpoint:** `http://localhost:8080/api/storage/contacts/{clientId}/{contactType}`

**Метод:** `GET`

**Параметры:** `clientId = 1, contactType = email`

**Ответ:**
```json
[
  {
    "id": 1,
    "contactTypeName": "email",
    "value": "test@gmail.com"
  }
]
```