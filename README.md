## Определение третьей нормальной формы (3НФ)

Для того чтобы база данных находилась в 3НФ, необходимо выполнение следующих условий:

1. **База данных должна быть в второй нормальной форме (2НФ)**.
2. **Все неключевые атрибуты должны зависеть только от первичного ключа**, исключая транзитивные зависимости.

## Структура таблиц и их зависимости

### Таблица `directions`
- Поля: `id`, `name`
- Первичный ключ: `id`
- Все атрибуты зависят только от первичного ключа.

### Таблица `years`
- Поля: `id`, `year`
- Первичный ключ: `id`
- Не содержит неключевых атрибутов, зависимых от других атрибутов.

### Таблица `university_groups`
- Поля: `id`, `number`, `user_count`, `direction_id`, `year_id`
- Первичный ключ: `id`
- Внешние ключи на таблицы `directions` и `years` обеспечивают целостность данных.
- Все поля функционально зависят от первичного ключа.

### Таблица `users`
- Поля: `id`, `email`, `firstname`, `surname`, `patronymic`, `password`, `university_group_id`
- Первичный ключ: `id`
- Внешний ключ на таблицу `university_groups`.
- Все атрибуты зависят только от первичного ключа.

### Таблица `corpus`
- Поля: `id`, `town`, `street`, `house`
- Первичный ключ: `id`
- Нет зависимостей от других атрибутов.

### Таблица `auditory`
- Поля: `id`, `number`, `capacity`, `corpus_id`
- Первичный ключ: `id`
- Внешний ключ на таблицу `corpus`.
- Все атрибуты зависят только от первичного ключа.

### Таблица `event`
- Поля: `id`, `start_time`, `end_time`, `user_count`, `description`, `auditory_id`, `user_id`
- Первичный ключ: `id`
- Внешние ключи на таблицы `auditory` и пользователей.
- Все атрибуты зависят только от первичного ключа.

## Заключение

Таким образом, база данных соответствует третьей нормальной форме (3НФ) по следующим причинам:

- Все таблицы имеют четко определенные первичные ключи.
- Все неключевые атрибуты функционально зависят только от своих первичных ключей и **не имеют транзитивных зависимостей**.
- Использование внешних ключей помогает поддерживать целостность данных и связи между таблицами без нарушения нормализации.

Эти аспекты обеспечивают эффективное управление данными и минимизируют избыточность, что является основным признаком 3НФ.
