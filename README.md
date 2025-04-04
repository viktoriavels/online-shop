# online-shop-arch

Проект состоит из трех микросервисов.

 ## notification

Ответственность: отправляет уведомления через Telegram и почту.

Стек:  
- Spring Boot
- Spring Cloud(Kafka)
- Apache kafka
- Spring Mail 
- Java 23

## orders

Ответственность: резервирует товары. Управляет бизнес-процессом заказа. 

Стек:
- Spring Boot
- Spring Cloud(OpenFeign)
- Apache kafka
- Camunda
- Java 23

## service-storage

Отвественность: API склада товаров.

Стек:
- Spring Boot
- Java 23
- Spring Data JPA
- Hibernate
