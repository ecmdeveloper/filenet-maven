This project contains the pom-file necessary to install the IBM FileNet Content Engine (CE) API. The procedure to do this follows the Maven opinionated approach, requiring minimal configuration.

* Checkout the project from github
* Copy the CE API files to the folder `src/main/resources/ce_api`.
* Now you have to obtain the version of your CE API. The easiest way to do this is to look in the `MANIFEST.MF` file of the `jace.jar` file and obtain the version from  there.
* Next run the Maven command `mvn install -Djace.version=5.2.1.4` with the version number as the value of the `jace.version` property.  
* Let Maven do it's magic

Now you are all set. Add the following dependency to your pom-file and the CE API and all it's dependencies are added to your project:
```xml
<dependency>
   <groupId>com.ibm.filenet</groupId>
   <artifactId>jace</artifactId>
   <version>5.2.1.4</version>
</dependency>
```
Make sure that everytime you upgrade or patch your system you update your repository accordingly.

Also note that the jar files you add this way are necessary if you want to connect using the WSI interface of the Content Engine. If you want to use the EJB interface you need some more dependencies. These are not in the scope of this project.
