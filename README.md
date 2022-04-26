# carousell-cli
A CLI (command line interface) application, which will take input on STDIN and post output on STDOUT.

Carousell is a marketplace platform which allows users to ‘buy’ and ‘sell’ items. The idea is to implement the core functionality of a marketplace with the following entities:

- User
  - Anyone who uses the marketplace
  - Identified by username, which should be unique throughout the platform, but case insensitive.
  - Each user can create any number of listings having any category.
- Listing
  - A listing of an item put up for sale on the marketplace. Only registered users should be allowed to buy or sell items.
  - Listings should have the following fields:
    - Title
    - Description
    - Price
    - Username
    - Creation time
  - Each listing can be associated with only 1 user and 1 category.
- Category
  - Groupings of listings of the same "category". E.g. Electronics, Fashion etc
  - Category reads can be sorted on Price or creation time.



### Build and Run instructions : 

Build and Package

* ```mvn clean package```

Run JAR using following command to give inputs

* ```java -jar target/cli-app-1.0-SNAPSHOT.jar```


### Note : 
  Make sure to use Java > 1.8 and Maven 3.6.X
