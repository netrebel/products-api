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
