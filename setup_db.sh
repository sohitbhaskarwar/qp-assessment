#!/bin/bash

docker pull postgres

docker run --name my-postgres-container -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres

echo "Waiting for PostgreSQL to start..."
sleep 10

docker exec -it my-postgres-container psql -U postgres -c "CREATE DATABASE question_pro_db;"

echo "Database 'question_pro_db' created successfully!"

# Step 5: Stop and Remove the PostgreSQL Container (optional)
# docker stop my-postgres-container
# docker rm my-postgres-container