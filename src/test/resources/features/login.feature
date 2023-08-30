Feature: login_functionality_check

  @TCOO1
  Scenario: Login check with valid credentials
    Given User launch the application
    Then User login with valid credentials
      | username | password |
      | Admin    | admin123 |

#  @TCOO2
#  Scenario: Login check with invalid credentials
#    Given User launch the application
#    Then User login with invalid credentials
#      | username | password |
#      | Thinesh  | Lock@123 |