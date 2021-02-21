# Program Organization
Context Diagram:
Our mobile application Social Grub will be based on user entries and information provided by the user. We are using firebase to maintain and interact with the data being supplied by the user. The diagram below expresses how each portion interacts with user story ids. As we continue to add additional features this diagram will be changing along with development changes. 

![contextDiagram](https://user-images.githubusercontent.com/62866287/108636334-8efcbb00-7452-11eb-9829-9648fb32dc03.png)


Container Diagram:
All basic features of the application will have functionality through the UI of the application. The application will make 
API calls to the firebase database and google services to attain information stored and a user's camera. Depending on more user stories added there may be more pending API calls to add to our architecture.

![containerDiagram](https://user-images.githubusercontent.com/62866287/108636608-5d84ef00-7454-11eb-8c59-13fac41c94c4.png)


Component Diagram:
The vital API calls made for security purposes are being made to our database which handles resetTing passwords and communicates with the user via email. The functionality feature that makes an API call is to Google services for camera access which is an essential feature of our app mapping to user story ID: 005.

![compDiagram](https://user-images.githubusercontent.com/62866287/108637392-6081de80-7458-11eb-9103-c0597f414584.png)


# Code Design
The major classes of our design are based on the class User, class RecipePost, and class ExplorePage. The class User contains all user information and different methods that describe actions that the user can do in the app such as the method editProfile, etc. The class ExplorePage has methods to filter a search result or go to the basic search bar. The class RecipePost has multiple subclasses underneath it that the diagram will display.

![UMLdiagram](https://user-images.githubusercontent.com/62866287/108637725-cc187b80-7459-11eb-8063-509b4564bcdc.png)



# Database Diagram (ER)
We are using Firebase which is a NoSQL cloud database to store user information as well as other classes that our code will contain. The entities describe our major classes which are class User, class Post, class RecipeList, class commentSection. 

![erDiagram](https://user-images.githubusercontent.com/62866287/108637759-01bd6480-745a-11eb-9174-7de7fa88acde.png)


# UI Design
The user will be able to interact with the UI of the app based on the app layout. The user will have numerous options from creating a post, viewing a post, searching a post, etc. Additionally we will supply UI where making a post is accessible and easy for the user such as providing user text boxes. 
![UIdiagram](https://user-images.githubusercontent.com/62866287/108638201-06831800-745c-11eb-9ca2-be660028bbc1.png)


# Business Rules
We will ensure user security. Additionally, all user accounts will be public and viewable by a follower and following based environment to maintain a social media feel to the application. 

# Resource Management 
Due to the constraints our current Database has we will only be making the full app experience available to a limited number of users. However, all planned application features and more will be available.

# Security
Our users’ information will be protected in the database so no user can access another user’s information. We will use firebase to authenticate users emails when they sign up and firebase already has a built in security feature.

# Performance
All requested information by the user will be automatic. Such as searching recipes up, searching users, modifying their profile. All performance aspects will be focused on responsiveness speed. Such as being able to quickly upload a post without the file upload lagging.

# Scalability
Our app will be scalable to where there are numerous users on the app and they are all interacting with each other. At the current development phase we have a scalability to a limited number of users based on the available resources to us. Additionally, when the number of users begin to rise, we will look to expand our database systems to meet the expansion needs. Our end goal is to allow users to continue to grow their profile and have more users as well. 

# Interoperability
At the moment there will be no outside interaction with other software, except the database system. The database will be interacting with user data and posts.

# Internationalization/Localization
We do not plan on providing non-English interfaces. It is simply beyond the scope of our time and budget.

# Input/Output
Users will have text fields to enter their username/password at the login screen. Invalid usernames and passwords will cause an error message that says “Invalid Credentials”.
When a user wants to post a recipe they will be given 5 input fields: Title, description, ingredients, tags, and picture. The first three will be handled with a textbox that will have a character limit. The user will be able to select tags from a dropdown menu. Pictures can be selected from the user’s camera roll or they can take a new picture for their recipe. When searching for other recipes users will be given the option to put their dietary restrictions in the filtering system. Users will also be able to search for other users in our database. 

# Error Processing
When it comes to error processing, we consider the errors that can occur during user registration, and user login. During login registration, the program specifies any errors involving the new user using an identical username, or email, when creating a new account. We would want the error processing to be corrective in this case. This can be accomplished by accessing the database with all the user information to identify and match any identical usernames, or emails, through a specific search algorithm, that already exists to prevent any error when it comes to accessing another user’s information. Otherwise, anyone who tries to create an account with a username, or email, already in the database will have an error message to signify an account already exists with the attempted username, or email, to create a new account. On the other hand, any error processing that occurs with logging in to an account should be corrective.  When a username inputs his username and password, it must follow the exact format that was used when the user created their account. Otherwise, they will display an error message to signify an invalid login attempt.

# Fault Tolerance
Our system will be able to detect a fault tolerance through multiple algorithms that will detect a valid login, valid post, and valid post inputs. There will be a fault tolerance for searches to detect valid searches based on usernames and a filtering system that will be provided to the user. 

# Architectural Feasibility 
We have designed the architecture around zero -cost tools, meaning that we are using a limited database. Therefore we plan on limiting the number of users to limit the amount of stored data.

# Overengineering
Our system will have algorithms built in place that produce certain outputs to let the user know that an invalid input was made. All errors will be standard outputs. For example, a user will search a user, if entry is invalid, application will state invalid search. 

# Build vs Buy Decisions
We will use a free database system called firebase and use google’s camera system so users can post pictures of their recipes. 

# Reuse
We are using google’s firebase for our database and an api access to their camera to take pictures to allow users to post. 

# Change Strategy
At the moment we are coding our application with Java which is a language that can be implemented in numerous systems. The concern we have is the database management aspect of the project. Based on the resources we have at our disposal we may need to expand based on user feedback if the application ever does expand to a multiple number of users. If the database system we currently have is not the best option then we may change to a more flexible database. 


