Scenario: Get All Profile
Given I am Admin User
And Exist the following Profiles in the repository
| id | names |
| 1  | John  |
| 2  | Jane  |
When I get all Profiles
Then I should get a list of Profiles with length 2

Scenario: Get Profile by Id
Given I am Admin User
And Exist the following Profiles in the repository
| id | names |
| 1  | John  |
| 2  | Jane  |
When I get Profile with id 1
Then I should get a Profile with id 1