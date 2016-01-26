# NingZeta-Sample-Querydsl

General usage example of querydsl with Spring JPA.

Meta-model are generated using maven plugin [maven-apt-plugin](https://github.com/querydsl/apt-maven-plugin).

#### Description:
**Entity**
* _Customer:_ Information for customer.
* _Country:_ Name and ISO [3166-1-alpha-2 code](http://www.iso.org/iso/country_names_and_code_elements)

**Data File**
* Located at : `src\main\resources\data.sql`. It will be automatically loaded into the _embedded H2 DB_ at spring-boot start up.

**Use-Case**
* Display all the customers sorted.
* Display list of customer from a certain country.
* Display a customer information and which country belongs to with the country flag.
* Display all customer within an age-range.

## Technology use
* Spring-Boot
* Spring Data JPA
* Querydsl
* Embedded H2 database
* Thymleaf (Templating)
* Bootstrap (Easy UI)
* Flag-icon-css (flag svg)

Refer to `pom.xml` for details.

## Requirement

* Maven 3.x
* Java 1.7+

## How to ?
1) Describe the Entiy and **Annotate** with @Entity.
```java
@Entity
class Country {
   ...
}
````
2) Add the maven dependency.
```xml
<!-- Querydsl dependencies -->
 <dependency>
	<groupId>com.mysema.querydsl</groupId>
	<artifactId>querydsl-apt</artifactId>
	<version>${querydsl.version}</version>
</dependency>
<dependency>
	<groupId>com.mysema.querydsl</groupId>
	<artifactId>querydsl-jpa</artifactId>
	<version>${querydsl.version}</version>
</dependency>
````
3) Generate the meta-model using maven plugin.
````xml
<!-- Generate meta-model classes -->
<plugin>
  <groupId>com.mysema.maven</groupId>
  <artifactId>maven-apt-plugin</artifactId>
  <version>1.0.4</version>
  <executions>
    <execution>
      <phase>generate-sources</phase>
      <goals>
        <goal>process</goal>
      </goals>
      <configuration>
        <outputDirectory>generated-sources</outputDirectory>
        <processor>com.mysema.query.apt.jpa.JPAAnnotationProcessor</processor>
      </configuration>
    </execution>
  </executions>
</plugin>
````
The generated meta-model will appears under the generated-sources at the base of the maven project. I like to use in ide with class-path so its not under target directory.

Generate the sources with `mvn generate-sources`

3) Now extends the custom repository with the `org.springframework.data.querydsl.QueryDslPredicateExecutor`.
````java
public interface CountryRepository extends JpaRepository<Country, Long>,
											QueryDslPredicateExecutor<Country>{
}
````


## Usage
`mvn clean spring-boot:run` and hit the url `http://localhost:8080`

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
