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

To Build and Package run either of the following commands
* ``` sh build.sh ```
* ```mvn clean package```

Sample output looks like this
```log
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------------< com.carousell:cli-app >------------------------
[INFO] Building cli-app 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ cli-app ---
[INFO] Deleting /Users/<<username>>/Dev/carousell-cli/target
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ cli-app ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO] Copying 0 resource
[INFO] Copying 2 resources
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ cli-app ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 13 source files to /Users/<<username>>/Dev/carousell-cli/target/classes
[INFO] /Users/<<username>>/Dev/carousell-cli/src/main/java/com/carousell/App.java: /Users/<<username>>/Dev/carousell-cli/src/main/java/com/carousell/App.java uses or overrides a deprecated API.
[INFO] /Users/<<username>>/Dev/carousell-cli/src/main/java/com/carousell/App.java: Recompile with -Xlint:deprecation for details.
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ cli-app ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ cli-app ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/<<username>>/Dev/carousell-cli/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ cli-app ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.carousell.AppTest
# REGISTER user1
Command Response 	:
Success
Test Case Expected 	 : Success
Test Case Actual 	 : Success
===========================================================================
# CREATE_LISTING user1 'Phone model 8' 'Black color, brand new' 1000 'Electronics'
Command Response 	:
100001
Test Case Expected 	 : 100001
Test Case Actual 	 : 100001
===========================================================================
# GET_LISTING user1 100001
Command Response 	:
Phone model 8|Black color, brand new|1000.0|Wed Apr 27 13:45:49 IST 2022|Electronics|user1
Test Case Expected 	 : Phone model 8
Test Case Actual 	 : Phone model 8
===========================================================================
# CREATE_LISTING user1 'Black shoes' 'Training shoes' 100 'Sports'
Command Response 	:
100002
Test Case Expected 	 : 100002
Test Case Actual 	 : 100002
===========================================================================
# REGISTER user2
Command Response 	:
Success
Test Case Expected 	 : Success
Test Case Actual 	 : Success
===========================================================================
# REGISTER user2
Command Response 	:
Error - user already existing
Test Case Expected 	 : Error - user already existing
Test Case Actual 	 : Error - user already existing
===========================================================================
# CREATE_LISTING user2 'T-shirt' 'White color' 20 'Sports'
Command Response 	:
100003
Test Case Expected 	 : 100003
Test Case Actual 	 : 100003
===========================================================================
# GET_LISTING user1 100003
Command Response 	:
T-shirt|White color|20.0|Wed Apr 27 13:45:49 IST 2022|Sports|user2
Test Case Expected 	 : T-shirt
Test Case Actual 	 : T-shirt
===========================================================================
# GET_CATEGORY user1 'Fashion' sort_time asc
Command Response 	:
Error - category not found
Test Case Expected 	 : Error - category not found
Test Case Actual 	 : Error - category not found
===========================================================================
# GET_CATEGORY user1 'Sports' sort_time dsc
Command Response 	:
T-shirt|White color|20.0|Wed Apr 27 13:45:49 IST 2022|Sports|user2
Black shoes|Training shoes|100.0|Wed Apr 27 13:45:49 IST 2022|Sports|user1
Test Case Expected 	 : T-shirt|White color|
Test Case Actual 	 : T-shirt|White color|
===========================================================================
# GET_CATEGORY user1 'Sports' sort_price dsc
Command Response 	:
Black shoes|Training shoes|100.0|Wed Apr 27 13:45:49 IST 2022|Sports|user1
T-shirt|White color|20.0|Wed Apr 27 13:45:49 IST 2022|Sports|user2
Test Case Expected 	 : Black shoes|Training shoes|100
Test Case Actual 	 : Black shoes|Training shoes|100
===========================================================================
# GET_TOP_CATEGORY user1
Command Response 	:
Sports
Test Case Expected 	 : Sports
Test Case Actual 	 : Sports
===========================================================================
# DELETE_LISTING user1 100003
Command Response 	:
Error - listing owner mismatch
Test Case Expected 	 : Error - listing owner mismatch
Test Case Actual 	 : Error - listing owner mismatch
===========================================================================
# DELETE_LISTING user2 100003
Command Response 	:
Success
Test Case Expected 	 : Success
Test Case Actual 	 : Success
===========================================================================
# GET_TOP_CATEGORY user2
Command Response 	:
Sports
Test Case Expected 	 : Sports
Test Case Actual 	 : Sports
===========================================================================
# DELETE_LISTING user1 100002
Command Response 	:
Success
Test Case Expected 	 : Success
Test Case Actual 	 : Success
===========================================================================
# GET_TOP_CATEGORY user3
Command Response 	:
Error - unknown user
Test Case Expected 	 : Error - unknown user
Test Case Actual 	 : Error - unknown user
===========================================================================
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.091 s - in com.carousell.AppTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ cli-app ---
[INFO] Building jar: /Users/<<username>>/Dev/carousell-cli/target/carousell-cli-app.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.203 s
[INFO] Finished at: 2022-04-27T13:45:50+05:30
[INFO] ------------------------------------------------------------------------

```

Run either of the following commands
- Run run.sh file
  * ``` sh run.sh ```

- Run JAR using following command to give inputs
  * ```java -jar target/carousell-cli-app.jar```

- Enter 'exit' command to quit from cli

### Note : 
  - Make sure to use Java > 1.8 and Maven 3.6.X
  
