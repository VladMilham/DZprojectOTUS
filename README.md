# Gatling Load Testing - Project OTUS

Проект нагрузочного тестирования веб-приложения WebTours с использованием Gatling.

## Структура проекта

ProjectOTUS/
├── src/
│ └── test/
│ ├── resources/
│ │ ├── users.csv # Фидер с пользователями
│ │ ├── depart.csv # Фидер с городами отправления
│ │ ├── arrive.csv # Фидер с городами прибытия
│ │ ├── gatling.conf # Конфиг Gatling
│ │ └── logback-test.xml # Конфиг логирования
│ └── scala/
│ └── simulations/
│ ├── CommonScenario.scala # Основной сценарий
│ ├── Debug.scala # Debug симуляция
│ └── CapacityTest.scala # Тест поиска максимума
├── build.sbt # Конфиг sbt
└── README.md


## Требования

- Java 17+
- Scala 2.13
- sbt 1.11.7+
- Gatling 3.10.4

## Запуск

Debug тест на 1 пользователя
sbt "Gatling/testOnly simulations.Debug"

Тест поиска максимума
sbt "Gatling/testOnly simulations.fMax"

Тест стабильности
sbt "Gatling/testOnly simulations.stub"


## Результаты

Отчеты генерируются в `target/gatling/`