# Program Organization
Our mobile application will be based on user entries and information provided by the user. We are using a firebase database to interact with user entries to store,modify, and delete user entries. For example, a user can create a password with their account to keep their information secure. The password created happens to be stored in the firebase database where it can always be retrieved and modified. There are very few API calls that our system makes which are demonstrated in the diagrams. 

[Context Diagram](https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=ContextDiagram#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D10RZph3hUoe_Zam2a9zN7is9nyKuxTqLo%26export%3Ddownload)
[Container Diagram](https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=ContainerDiagram.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1e0b-F1im4_plfJuo3hIauKduAwMtVSFk%26export%3Ddownload)
[Component Diagram](https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=ComponentDiagram.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1sw1rXp6UCVfK1h8jeatgnL8I_1W_gm6l%26export%3Ddownload)

# Code Design
The major classes of our design are based on the class User, class RecipePost, and class ExplorePage. The class User contains all user information and different methods that describe actions that the user can do in the app such as the method editProfile, etc. The class ExplorePage has methods to filter a search result or go to the basic search bar. The class RecipePost has multiple subclasses underneath it that the diagram will display.

[UML Diagram](https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=UML.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1AotSCkR7CvBqSHkG9sNNN_QBrtKiwv85%26export%3Ddownload) 


# Database Diagram (ER)
We are using Firebase which is a NoSQL cloud database to store user information as well as other classes that our code will contain.

[ER Diagram](https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=ER%20Diagram%20SG.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1EzRK9jxCOUsRtjK-dmy1Jt-xKUDvZJpx%26export%3Ddownload)

# UI Design
The user will be able to interact with the UI of the app based on the app layout. The user will have numerous options from creating a post, viewing a post, searching a post, etc. Additionally we will supply UI where making a post is accessible and easy for the user such as providing user text boxes. 

[UI Design](https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=Ui.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1fkA_jLhNuLvKR3LF7bOw-DyTiRJ8pbLk%26export%3Ddownload)

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


