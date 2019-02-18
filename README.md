# How to run locally

```bash
$ ./gradlew build && java -jar build/libs/products-api-0.0.1-SNAPSHOT.jar
```

API will be available at http://localhost:8080/products

# Run tests
```bash
$ ./gradlew test
```

# API requests examples

## POST

```bash
curl -X POST \
  http://localhost:8080/products \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '[
    {
        "name": "Test 1",
        "label": "PRODUCTS"
    },
    {
        "name": "Test 1",
        "label": "PRODUCTS"
    },
    {
        "name": "Test 2",
        "label": "PRODUCTS"
    },
    {
        "name": "Test 3",
        "label": "PRODUCTS"
    }
]'
```

## Get by Id

```bash
curl -X GET \
  http://localhost:8080/products/1 \
  -H 'cache-control: no-cache'
```

## Delete by Id

```bash
curl -X DELETE \
  http://localhost:8080/products/1 \
  -H 'cache-control: no-cache'
```

# Logs

RollingFileAppender configured to write to `./logs/products-api.log`

# Database (H2)

Admin UI available at: http://localhost:8080/h2

# TODOs

Project requirements:
- [x] You may use any Java framework/platform you prefer (Spring Boot, Jersey, Vert.x, etc).
- [x] Create a Maven or Gradle project to handle build and dependencies
- [x] Use either a lightweight DB (H2) or MySQL to persist data
- [x] Write some basic unit tests to validate functionality

Application requirements:
- [x] Provide basic input validation and return meaningful errors
- [x] Write to a log file as appropriate

Create service:
- [x] Create N records for any given level of the hierarchy, for a given parent node.
- [x] Root level nodes are created by providing no parent node in the request.
- [x] Allow duplicate records in the same request but only store one copy (remove duplicates)
- [x] Return an error if the node name already exists from a prior service call that created it.
- [ ] Create with parent node 

Delete service:
- [x] Delete by Id
- [ ] Delete with children nodes (cascade delete)

List service:
- [ ] List records within a level of the hierarchy, for a given parent node.
