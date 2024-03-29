Business Need: verification of the api working fine

  @SearchPage
  Scenario: 1
    Given A client of the API
    When A search for pages containing for "furry rabbits" is executed
    Then A page with the title "Sesame Street" is found

  @PageDetails
  Scenario: 2
    Given The result for "furry rabbits" search contains "Sesame Street"
    When The page details for "Sesame Street" are requested
    Then It has a latest timestamp > "2023-08-17"