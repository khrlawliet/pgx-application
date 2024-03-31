```markdown
# PGX Application

This is a Java Maven project that provides a RESTful API for managing persons. It uses Spring Boot, JUnit for testing, and includes exception handling.

## Installation

Clone the repository and install the dependencies with Maven.

```bash 
  git clone https://github.com/khrlawliet/pgx-application.git
  cd pgx-application
  mvn clean install
```

## Usage/Examples

The application provides a RESTful API with the following endpoints:

- `POST /api/v1/persons`: Create a new person
- `GET /api/v1/persons`: Fetch all persons
- `GET /api/v1/persons/{id}`: Get a person by ID

## Running Tests

To run tests, use the following Maven command:

```bash
  mvn test
```

## API Reference

#### Create a new person

```http
  POST /api/v1/persons
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `person`  | `object` | **Required**. Person to create |

#### Get all persons

```http
  GET /api/v1/persons
```

#### Get person by ID

```http
  GET /api/v1/persons/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. ID of person to fetch |

## Contributing

Contributions are always welcome! Please read the contributing guidelines first.

## License

[MIT](https://choosealicense.com/licenses/mit/)
```

This README includes sections for the project title, installation instructions, usage examples, API reference, running tests, contributing, and license. You should replace the placeholders with the actual details of your project.