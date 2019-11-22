Feature: As a user I want to create a bookmark of a book with a title and author

  
Scenario: Creating a bookmark of a book with a title and author
    Given bookmark of a book is initialized
    When bookmark title is set to "title"
    And  bookmark author is set to "author"
    Then the title should be "title" and author "author"