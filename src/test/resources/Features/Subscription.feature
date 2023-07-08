Feature: Subscription Page Validation

  Background:
    Given I navigated to STC TV site
    Then I should see STC TV page displayed
    When I click on country selection
    Then I should see country list

  Scenario Outline: Validate Choosing Plan
    When I select Country "<Country>"
    Then I should see Lite Price"<LitePrice>" and "<Currency>"
    And I should see Classic Price"<ClassicPrice>"
    And I should see Premium Price "<PremiumPrice>"
    And I should see country icon "<icon>" is displayed

    Examples:
      | Country | LitePrice | Currency | ClassicPrice | PremiumPrice | icon    |
      | Kuwait  | 1.2       | KWD      | 2.5          | 4.8          | Kuwait  |
      | KSA     | 15        | SAR      | 25           | 60           | KSA     |
      | Bahrain | 2         | BHD      | 3            | 6            | Bahrain |



