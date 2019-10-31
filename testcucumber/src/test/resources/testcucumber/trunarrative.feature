Feature: trunarrative strapline and team members
  verify that the strapline and team members

  Scenario: Googling TruNarrative result returns trunarrative
    Given I navigate to the google search page
    When I search for “TruNarrative”
    Then the first search result points to https://trunarrative.com/

  Scenario: Verifying the Strapline
    Given I am viewing the homepage of the trunarrative website
    When I view the strapline
    Then the text displayed is "Easy Onboarding.  Smooth Transactions.  Insightful Compliance."

  Scenario: Verifying the jobroles
    Given I navigate to the trunarrative team page
    When I view the leadership team
    Then the following will be displayed
    | Name | Role |
    | John Lord | Founder & CEO |
    | David Eastaugh | CTO |
    | Nicola Janney | Human Resources Manager |
