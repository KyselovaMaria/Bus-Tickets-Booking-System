# Bus-Tickets-Booking-System

## Каса автовокзалу - Task description

Розклад автобусів: номер рейсу, кінцевий та проміжний пункти, час відправлення.

• Кількість вільних місць на кожному рейсі.

• Вибір найближчого рейсу до заданого пункту (за наявності вільних місць).

• Оформлення квитків, оформлення посадкової відомості.

• Попередній продаж, повернення квитків.

## Installation

To run the project locally, follow these steps:

1. Clone the repository: git clone https://github.com/KyselovaMaria/Bus-Tickets-Booking-System.git
  
2. Open the project in your preferred IDE.

3. Set up the database: Install and configure PostgreSQL on your system.

4. Run the application: The application should be running on http://localhost:9091/


## Configuration
The project uses the `application.properties` file to configure the database connection:

server.port=9091

spring.datasource.platform=postgres

spring.datasource.url=jdbc:postgresql://localhost:5433/BusTickets

spring.datasource.username=postgres

spring.datasource.password=root

spring.jpa.database=POSTGRESQL

spring.jpa.show-sql=true

spring.jpa.generate-ddl=true

spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

spring.datasource.initialization-mode=always


spring.mvc.throw-exception-if-no-handler-found=true

spring.resources.add-mappings=false

## SO you have to check the password, username and create database with the right name, *the first three routes will be created automatically



## Overview screenshots
![image](https://github.com/KyselovaMaria/Bus-Tickets-Booking-System/assets/88087036/552e85da-1948-48cb-b9ea-3ef9b80725a8)
![image](https://github.com/KyselovaMaria/Bus-Tickets-Booking-System/assets/88087036/c13d84f5-0e86-4aef-9aff-c129ac1ccac5)
![image](https://github.com/KyselovaMaria/Bus-Tickets-Booking-System/assets/88087036/8af96a1d-7aeb-4287-b7cc-45311d827edc)
![image](https://github.com/KyselovaMaria/Bus-Tickets-Booking-System/assets/88087036/315e8ec8-a3f4-46da-a257-d3176eeabeb2)
![image](https://github.com/KyselovaMaria/Bus-Tickets-Booking-System/assets/88087036/6f14ef9f-a5a2-4a61-9cab-c68c55ab7294)
![image](https://github.com/KyselovaMaria/Bus-Tickets-Booking-System/assets/88087036/8a077991-c3f6-4fe0-b950-17b497023005)

