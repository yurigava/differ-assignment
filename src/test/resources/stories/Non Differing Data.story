Meta:
@regression
@author Yuri Vaz

Narrative:
As a diff tool user
I want to insert two identical strings
So that I can verify that they are equal

Scenario: Verify if identical strings are verified to be equal
Given I successfully set the left side of id 1 to '<diffString>'
And I successfully set the right side of id 1 to '<diffString>'
When I get the diff result for id 1
Then I verify that the diff response is of type EQUAL

Examples:
|diffString          |
|abcdEFGH1234        |
|abcdefgh1234        |
|abcd1234efgh        |
|abcdefgh            |
|ABCDEFGH            |
|12345678            |
|RXF1YWwgQ29udGVudHM0|
