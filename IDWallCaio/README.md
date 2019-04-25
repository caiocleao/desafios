Read Me:

Folder contaning project: IDWallCaio. Project can be imported on Eclise by native GIT integration. URL's to github page and for cloning can be found below:

Github URL: https://github.com/caiocleao/desafios
Github Clone URL: https://github.com/caiocleao/desafios.git 

Project Executables can be found on the execs packeage. 

#######################################################################################

Executable instrunctions:

MainFormater: Executable created for use of formatter. To change the desired text for formatation, simply change the variable "text" on this object. To change the word limit, adjust the "limit" variable to the desired value and to toggle the justification, change the value of the "justify" boolean variable. True to get justified text, false to get non-justified test.

MainCrawler: Executable created for isolated development testing of crawler functionality.

MainTelegramBot: Telegram bot that uses the Reddit crawler. Must be running to use the bot. Can simply be executed by running this file. "Hello bot" message will be printed on console to let the user know the bot is up and running.
Main 

#######################################################################################

External JARS manually added: 

JSOUP - Can be found on the following link: https://jsoup.org/

JSOUP dependency has been uploaded on repository to avoid any issues of missing dependencies. Manually added to showcase diferent ways of adding dependencies. For sensitive JARs and other dependencies, add JAR file paths to .gitignore so they don't get uploaded to the repository.

Maven dependency added: telegrambots. Xml to add on POM.xml configuration file below:

<dependency>
    <groupId>org.telegram</groupId>
    <artifactId>telegrambots</artifactId>
    <version>4.1</version>
</dependency>

To get the Maven dependency, simply add above code to pom.xml configuration file and update the project.
