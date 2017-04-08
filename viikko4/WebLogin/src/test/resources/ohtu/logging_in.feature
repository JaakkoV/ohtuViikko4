Feature: As a registered user can log in with valid username/password-combination

Scenario: user can login with correct password
Given login is selected
When correct username "jukka" and password "akkuj" are given
Then user is logged in

Scenario: user can not login with incorrect password
Given login is selected
When correct username "jukka" and incorrect password "wrong" are given
Then user is not logged in and error message is given

Scenario: nonexistent user can not login to
Given login is selected
When  incorrect username "dsaggsd" and any random password "adfopgkas" is given
Then  user is not logged in and error message is given
    
Scenario: creation successful with correct username and password
        Given new user is selected
        When  correct username "jukkis" and password "qwerty123!" are given to create new
        Then  user gets welcome page

Scenario: creation fails with too short username and valid passord
        Given new user is selected
        When  too short username "jk" and password "qwerty123!" are given to create new
        Then user is not created and error "username should have at least 3 characters" is reported   

 Scenario: creation fails with correct username and too short password
        Given new user is selected
        When  correct username "jukkis" and short password "qwe!" are given to create new
        Then user is not created and error "password should have at least 8 characters" is reported 

Scenario: creation fails with correct username and password consisting of letters
        Given new user is selected
        When  correct username "jukkis" and false password "jjjjjjjjjj" are given to create new
        Then user is not created and error "password can not contain only letters" is reported 

    Scenario: creation fails with already taken username and valid password
        Given new user is selected
        When taken username "pekka" and password "qwerty123!" are given to create new
        Then user is not created and error "username is already taken" is reported 

    Scenario: creation fails when password and password confirmation do not match
        Given new user is selected
        When  correct username "ukkis" and mismatching passwords "qwerty123!" are given to create new
        Then user is not created and error "password and password confirmation do not match" is reported