# Experience Assignment 1

Author: 577136@stud.hvl.no

### Tasks

* Installation: Software Development Environment
    * JRE
    * IDE 
    * Maven
    * Git
* Experiment: Heroku and Platform as a Service

Noting on following topics:

* technical problems that you encountered during installation of the software development environment
and how you have solved them

* how you have validated (checked) that the software development environment is working

* technical problems encountered with the Heroku platform and how you solved them

* any pending issues with this assignment which you did not manage to solve

## Installation

### Java RE

Since I've already had serveral classes with proramming in Java, I already had a setup of Java running.

Verified Java installation with the following command.

```
$ java --version
java 13.0.2 2020-01-14
Java(TM) SE Runtime Environment (build 13.0.2+8)
Java HotSpot(TM) 64-Bit Server VM (build 13.0.2+8, mixed mode, sharing)
```


### Maven

This tool is already installed. Can be fetched with using [Homebrew](https://brew.sh/);

```
$ brew install maven
````

Verifying the Maven setup:

```
$ mvn -v
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 14.0.1, vendor: N/A, runtime: /usr/local/Cellar/openjdk/14.0.1/libexec/openjdk.jdk/Contents/Home
Default locale: en_NO, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.6", arch: "x86_64", family: "mac"
```

### IDE

I'm have a collection of IDE's installed. I can, usually deciding after the size of the project, choose between the following IDE's that is installed: Visual Studio Code, Eclipse or IntelliJ.

Verified the IDE with creating a Maven project in IntelliJ.
Wrote a HelloWorld.java program and compiled and ran it.
Received the "java: error: release version 5 not supported" error when running the Maven project.
Turns out I had forgotten to change the setting for the compiler for the project from 1.5 -> 13. 
Changing this, and the program compiled and worke perfectly.
Google Result for a similar problem on [StackOverflow](https://stackoverflow.com/questions/59601077/intellij-errorjava-error-release-version-5-not-supported)

Output from IntelliJ:
```
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java 
-javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=62253:/Applications/IntelliJ IDEA.app/Contents/bin 
-Dfile.encoding=UTF-8 
-classpath /Users/kenneth/GIT/java.test/target/classes helloworld.HelloWorld

Hello world

Process finished with exit code 0
```


### Git

Already installed Git CLI tools as I use them for private projects.
Also use [GitKraken](https://www.gitkraken.com/) as my GUI client for managing my repositories.

```
$ git version
git version 2.22.0
$ git init dat250.h20
Initialized empty Git repository in /Users/kenneth/dat250.h20/.git/
```

# Experiment: Heroku and Platform as a Service

Following the [guide](https://devcenter.heroku.com/articles/getting-started-with-java) suggested from the assignment.


### Heroku CLI

Installed via brew:
```
brew install heroku/brew/heroku
```

Logged in:
```
kenneth@kefo  ~ > heroku login
heroku: Press any key to open up the browser to login or q to exit: 
Opening browser to https://cli-auth.heroku.com/auth/cli/browser/{token}?requestor={id}
Logging in... done
Logged in as fossen.kenneth@gmail.com
```

Followed the guide.
Skipped the step of adding Papertail to the account as it required a "Verification" based on me adding my Credit Card.
Solved by skipping the task, as I'm not adding my card to a Online Service.

```
$ heroku addons:create papertrail
Creating papertrail on ⬢ damp-reaches-93136... !
 ▸    Please verify your account to install this add-on plan (please enter a credit card)
 ▸    For more information, see https://devcenter.heroku.com/categories/billing Verify now
 ▸    at https://heroku.com/verify
```

Except this, there was no issues running/configuring or using Heroku services from my DevEnv.

Exampl output from a session:
```
$ heroku scale web=1
Scaling dynos... done, now running web at 1:Free
 kenneth@kefo  ~/GIT/heroku/java-getting-started   master  heroku ps 
Free dyno hours quota remaining this month: 550h 0m (100%)
Free dyno usage for this app: 0h 0m (0%)
For more information on dyno sleeping and how to upgrade, see:
https://devcenter.heroku.com/articles/dyno-sleeping

=== web (Free): java -jar target/java-getting-started-1.0.jar (1)
web.1: up 2020/08/19 12:39:52 +0200 (~ 17m ago)

$ heroku open 
$ heroku status 
Apps:      No known issues at this time.
Data:      No known issues at this time.
Tools:     No known issues at this time.
$ heroku logs --num 2
2020-08-19T10:56:56.968467+00:00 heroku[router]: at=info method=GET path="/hello" host=damp-reaches-93136.herokuapp.com request_id=8b1e0be3-4ed0-4e74-bb03-701177762ca1 fwd="77.16.53.103" dyno=web.1 connect=1ms service=145ms status=200 bytes=3561 protocol=https
2020-08-19T10:57:25.511101+00:00 heroku[router]: at=info method=GET path="/" host=damp-reaches-93136.herokuapp.com request_id=0283955a-a274-49e5-82ac-0c792cac89c4 fwd="77.16.53.103" dyno=web.1 connect=1ms service=9ms status=200 bytes=7329 protocol=https
```

Kenneth 
 

