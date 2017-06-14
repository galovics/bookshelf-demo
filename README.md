**Introduction**

The showcase project contains a simple application focusing on the book domain.

The application uses Spring Boot with an H2 in-memory database and it intentionally has multiple data-access performance flaws. 
The goal of the project is to find out which performance issues are present and to fix it.

There are multiple test cases for each issue so you can easily verify that the issues are soled.

Hibernate query logging and formatting is enabled by default to easily see what queries are running under the hood.

**Test classes**

The current test classes which are present:
* `AuthorServiceImplTest`
* `BookServiceImplTest`

**Running the tests**

`mvn clean verify`

**Test failure messages**

`Select query count differs from the expected. Actual count was 2 but expected 1 queries`

This test failure indicates that the service call should only execute a single query instead of 2. 

`Oops, you are selecting more data than needed. Remaining columns: [ISBN, LANGUAGE, PAGE_COUNT]`

This means that when the service is returning only a subset of the entity attributes, we don't need to query all of them to have an efficient execution.
This is the case when the service returns `SimpleBookView` where only two attributes `id` and `name` are present. 
In this case there is no need to query the `isbn`, `language`, `pageCount` attributes which are coming from the entity.