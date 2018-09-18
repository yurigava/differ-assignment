Meta:
@regression

Narrative:
As a diff tool user
I want to input incorrect data
So that I can verify that I get the correct error messages

Scenario: Verify that data is rejected on the left side if not JSON formatted
When I set the left side to non JSON string '<nonJSON>' on id 1
Then I verify the left response error is Non JSON Format

Examples:
|nonJSON        |
|'123'          |
|'abc'          |
|{123}          |
|{abc}          |
|abcd           |
|a12b           |

Scenario: Verify that data is rejected on the right side if not JSON formatted
When I set the right side to non JSON string '<nonJSON>' on id 1
Then I verify the right response error is Non JSON Format

Examples:
|nonJSON        |
|'123'          |
|'abc'          |
|{123}          |
|{abc}          |
|abcd           |
|a12b           |

Scenario: Verify that data is rejected on the left side if not Base64 formatted
When I set the left side of id 2 to '<nonBase64>'
Then I verify the left response error is non Base64 Format

Examples:
|nonBase64      |
|1              |
|a              |
|A              |
|123            |
|abc            |
|ABC            |
|12345          |
|abcde          |
|ABCDE          |
|1-345          |
|123/5          |
|123*5          |
|1 3t5          |

Scenario: Verify that data is rejected on the right side if not Base64 formatted
When I set the right side of id 2 to '<nonBase64>'
Then I verify the right response error is non Base64 Format

Examples:
|nonBase64      |
|1              |
|a              |
|A              |
|123            |
|abc            |
|ABC            |
|12345          |
|abcde          |
|ABCDE          |
|1-345          |
|123/5          |
|123*5          |
|1 3t5          |

Scenario: Verify error message for null left side
Given I successfully set the right side of a new diff id to 'bGVmdCBudWxs'
When I get the diff result for the new diff id
Then I verify that the diff response is left side with no value

Scenario: Verify error message for null right side
Given I successfully set the left side of a new diff id to 'cmlnaHQgbnVsbGw1'
When I get the diff result for the new diff id
Then I verify that the diff response is right side with no value
