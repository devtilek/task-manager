# Task Manager

A Spring Boot REST API for managing users and their tasks.

## Stack

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- MapStruct
- Lombok
- Gradle

## Run PostgreSQL

```bash
docker compose up -d
```

The default database configuration is:

- Database: `task_manager`
- Username: `postgres`
- Password: `postgres`
- Port: `5432`

You can override the connection with `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables.

## Run the application

```bash
./gradlew bootRun
```

On Windows:

```powershell
.\gradlew.bat bootRun
```

## API

### Users

- `GET /api/users` - get all users
- `GET /api/users/{id}` - get a user
- `POST /api/users` - create a user
- `PUT /api/users/{id}` - update a user
- `DELETE /api/users/{id}` - delete a user and their tasks

Create user:

```json
{
  "name": "Aktilek"
}
```

### Tasks

- `GET /api/tasks` - get all tasks
- `GET /api/tasks/{id}` - get a task
- `POST /api/tasks` - create a task
- `PUT /api/tasks/{id}` - update a task
- `DELETE /api/tasks/{id}` - delete a task

Create task:

```json
{
  "title": "Learn Spring Boot",
  "description": "Build a REST API with Spring Boot",
  "userId": "USER_UUID"
}
```

## Error handling

The API returns `404 Not Found` for missing users/tasks and `400 Bad Request` for validation errors.
