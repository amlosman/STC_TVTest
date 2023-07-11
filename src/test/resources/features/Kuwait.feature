Feature:  Kuwait packages

  Scenario: open Kuwait page and subscribe to lite package
    Given I navigated to STC TV site
    Then I should see STC TV page displayed
    When I click on country selection
    Then I should see country list
    When I select Country "Kuwait"
    Then I should see Lite Price"1.2" and "KWD"
    And I should see Classic Price"2.5"
    And I should see Premium Price "4.8"
    And I should see country icon "Kuwait" is displayed
