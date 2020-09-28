FROM mongo:latest

RUN mongod

COPY . .
RUN node ./scripts/import-data.js