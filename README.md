# Paging3Example
[![Kotlin](https://img.shields.io/badge/Kotlin-1.6.10-blue.svg)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android->=21-blue.svg)](https://developer.android.com/)
[![Gradle](https://img.shields.io/badge/Gradle-7.2-blue.svg)](https://docs.gradle.org/7.2/userguide/userguide.html)

Basic android example where you can find how to use [Paging library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview). 
This library helps you load and display pages of data from a larger dataset from local storage or over network.

## Requirements
- Android API 21 - 31
- Gradle 7.2
- Kotlin 1.6.10
- Paging 3.1.1

## Characteristics
- MVVM
- Clean Architecture
- Dagger Hilt
- LiveData
- Coroutines
- Retrofit
- Http3 Logging
- Room

## Functionalities

- Search GitHub repositories by name or description
- List of repositories is displayed in descending order based on the number of stars
   - To be able to get the repositories we use request 
```
GET https://api.github.com/search/repositories?sort=stars&q={searchValue}in:name,description&page={pageNumber}&per_page={pageSize}
```
- Displaying the loading state in a footer. 
   - When the list is loading, a progress spinner is displayed.
   -  In case of an error, the error and a retry button is displayed.

<div class="align-center">
<img width="300" alt="image" src="https://user-images.githubusercontent.com/20024632/160926696-50880b6a-1005-4a80-9d7b-308e6f565179.png">
<img width="300" alt="image" src="https://user-images.githubusercontent.com/20024632/160926563-454b9376-486c-4eed-96a2-a0d85ba15aec.png">
</div>

- Include list separators
- Offline support


## Branches

To be able to understand how everything is implemented, the project is split in different branches:
- *`master`*: Display list of items in a recycler view and automatically requests the correct page when the user has scrolled to the end of the list.
Functionalities implemented in this branch:
  - Search GitHub repositories by name or description
  - List of repositories is displayed in descending order based on the number of stars
  - Displaying the loading state in a footer

- *`list-separators`*: Improve list's readability by adding separators. In this app since the repositories are ordered by the number of stars descending, I included separators every 10k stars.
Functionalities implemented in this branch:
  - Search GitHub repositories by name or description
  - List of repositories is displayed in descending order based on the number of stars
  - Displaying the loading state in a footer
  - Include list separators

- *`room`*: Add offline support to our app by saving the data in a local database.
 That way, the database will be the source of truth for the app and it will always load data from there. 
 Whenever it doesnt have any more data, it requests more from the network and then save it in the database. 
 Because the database is the source of truth, the UI will be automatically updated when more data is saved.
Functionalities implemented in this branch:
  - Search GitHub repositories by name or description
  - List of repositories is displayed in descending order based on the number of stars
  - Displaying the loading state in a footer
  - Offline support

## Notes: 
- This example has been done with Paging 3.1.1
- To know more about Paging Library check https://developer.android.com/topic/libraries/architecture/paging/v3-overview

## Demo
- *`master`*:

https://user-images.githubusercontent.com/20024632/160931480-73131ff6-f548-425c-bb1d-fb0ce0240770.mp4

- *`list-separators`*:

https://user-images.githubusercontent.com/20024632/160932992-714f97b6-2a1e-4225-896d-b2373b2d8f72.mp4


