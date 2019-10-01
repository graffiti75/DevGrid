# DevGrid

## Implementation Steps

I followed the steps below:

1. Create repo on Github.
2. Include basic dependencies in Gradle: `Dagger2`, `Retrofit` and `RecyclerView`.
3. Customize file `gradle.properties`.
4. Add MVP pattern.
5. Import classes to perform basic `Retrofit` call.
6. Add `Retrofit` Interceptors: Cache, NetworkInterceptor and OfflineInterceptor.
7. Add `Timber`.
8. Test basic `Retrofit` call using the `Github API`: `https://developer.github.com/v3`.
9. Create Instrumented Tests to test the app.
10. Create `Interceptor` to modify `Retrofit` Header.
11. Implement `AndroidX`.
12. Create Unit Test to test Login and Password with `Base64`.
13. Apply `MockWebServer` on project to perform tests.
14. Implement `RxJava` on `Retrofit` calls.
15. Insert `Retrofit` result in the `RecyclerView`.
16. Test `Retrofit` call bringing last 5 commits from each repo.
17. Add `NavigationUtils`.
18. Add Loading view before data retrieval.
19. Create Unit Test to test `take(5)` command.
20. Add `CardView` to `Adapters`.
21. Read a QrCode and get its link for the Github repo.
22. Create graphic interface requesting User Password.
23. Fix login feature.
24. Update method to check Internet connection.
25. Update `DataBinding`.
26. Add `Pagination`.
27. Write README.

## Implementation Details

As requested in the project spec:

- The app reads a QrCode with a Github link.
- The app validates this link and informs the user.
- Then, Login screen is opened.
- All the login checks are made.
- If the user perfoms login correctly, a screen is opened, showing its Github repositories.
- Those repositories are showed in a `RecyclerView`, with its proper `Pagination`.
- All repositories are showed: Public and Private repos.
- Clicking on each repo, a Details screen is opened, showing the latest 5 commits from this repo.
- Internet connection check was implemented.
- A lot of tests were created on this project, like: Base64 tests; tests to check the `.take(5)` from `RxJava` Observables; Network tests using `IdlingResource`.

Also, this app is using:

- Kotlin
- SOLID and Clean Code principles
- Retrofit
- Dagger 2
- ConstraintLayout
- Espresso Tests
- RxJava 2
- Timber
- RecyclerView
- CardView
