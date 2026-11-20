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

## Testing

Run the fast service unit tests without starting PostgreSQL:

```bash
./gradlew test --tests 'com.taskmanager.taskmanager.service.*'
```

On Windows PowerShell:

```powershell
.\gradlew.bat test --tests "com.taskmanager.taskmanager.service.*"
```

The repository also contains a Spring Boot application-context test. To run the **full** test suite, start PostgreSQL with `docker compose up -d` first and then run `./gradlew test`. The GitHub Actions workflow runs isolated service unit tests on pushes and pull requests; it does not require a database.

## Development notes

- API validation uses Jakarta Bean Validation on incoming DTOs.
- Task creation requires an existing user ID; missing users and tasks return HTTP 404.
- Deleting a user also deletes their tasks through the JPA relationship.
- Local PostgreSQL credentials in `docker-compose.yml` are development defaults only. Use separate, strong credentials and environment variables outside local development.
