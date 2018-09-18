Meta:
@regression
@author Yuri Vaz

Narrative:
As a diff tool user
I want to insert different strings on left and right sides
So that I can verify that the differences are correctly spotted

Scenario: Insert Strings of different length
Given I successfully set the left side of id 2112 to '<leftString>'
And I successfully set the right side of id 2112 to '<rightString>'
When I get the diff result for id 2112
Then I verify that the diff response is of type DIFFERENT_LENGTH

Examples:
|leftString     |rightString    |
|1234           |12345678       |
|12345678       |1234           |
|abcd           |abcdefgh       |
|abcdefgh       |abcd           |
|abcd           |abc12345       |
|abc12345       |abcd           |

Scenario: Insert Strings with same length and different values
Given I successfully set the left side of id 2112 to '<leftString>'
And I successfully set the right side of id 2112 to '<rightString>'
When I get the diff result for id 2112
Then I verify that the diff response is of type DIFFERENT_CHARS
And I verify that the diff response detail is '<responseMessage>'

Examples:
|leftString             |rightString        |responseMessage                                   |
|1234                   |1233               |Values are different on char(s) [3].              |
|1234                   |6234               |Values are different on char(s) [0].              |
|1234                   |123F               |Values are different on char(s) [3].              |
|1234                   |V234               |Values are different on char(s) [0].              |
|abcx                   |abcd               |Values are different on char(s) [3].              |
|nbcd                   |abcd               |Values are different on char(s) [0].              |
|1234abcp               |1234abcd           |Values are different on char(s) [7].              |
|1234abcd               |5234abcd           |Values are different on char(s) [0].              |
|123Sabcd               |1234abcd           |Values are different on char(s) [3].              |
|1234abcd               |1234agcd           |Values are different on char(s) [5].              |
|1a2b3g4d               |1x2b3c4d           |Values are different on char(s) [1] [5].          |
|1a2b3c4d               |1a2vjd9g           |Values are different on char(s) [3-7].            |
|1a2b3c4d               |122m3r40           |Values are different on char(s) [1] [3] [5] [7].  |
|1a2b3c4d               |0d2xvcmh           |Values are different on char(s) [0-1] [3-4] [6-7].|
|1a2bhcxb               |1a2b3c4d           |Values are different on char(s) [4] [6-7].        |
|1a2bvk48               |1a2b3c4d           |Values are different on char(s) [4-5] [7].        |
|sa9b3snm               |1a2b3c4d           |Values are different on char(s) [0] [2] [5-7].    |
|1a2b3c4d               |jaxd3s90           |Values are different on char(s) [0] [2-3] [5-7].  |
|xp253cjo               |1a2b3k4d           |Values are different on char(s) [0-1] [3] [5-7].  |
|1asg3c4s               |xa2b3c4d           |Values are different on char(s) [0] [2-3] [7].    |
