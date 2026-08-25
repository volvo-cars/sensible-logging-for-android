# How to Contribute

We'd love to accept your patches and contributions to this project. There are
just a few small guidelines you need to follow.

## Module structure

This project publishes three separate Maven artifacts (all under the `sh.vcm.sensiblelogging` group),
built from three Gradle modules:

| Gradle module | Maven artifact                       | Type  | Depends on           |
|---------------|---------------------------------------|-------|----------------------|
| `:core`       | `sensible-logging-core`               | `.jar`| —                     |
| `:android`    | `sensible-logging-android`            | `.aar`| `sensible-logging-core` (`api`) |
| `:lifecycle`  | `sensible-logging-lifecycle`          | `.aar`| `sensible-logging-android` |

- **`core`** is a plain Kotlin/JVM module with **no Android SDK dependency at all**, so it can be consumed by
  non-Android JVM modules as well as Android modules. It contains the core API: `Logger`, `Channel`,
  `Filter`, `Formatter`, etc. Do not add code here that needs Android platform classes (e.g. `android.util.Log`,
  `android.content.SharedPreferences`, `android.app.*`) — that belongs in `android` instead.
- **`android`** is a `com.android.library` module that depends on (and re-exports, via `api`) `core`. It
  contains the Android-specific pieces built on top of the core library, such as `LogCatChannel` and
  `SharedPreferencesCategoryFilter`.
- **`lifecycle`** is a `com.android.library` module that depends on `android` (and transitively on `core`).
  It contains AndroidX Lifecycle-observer based logging helpers for `Application`/`Activity`/`Fragment`/
  `Service`.

When adding a feature, put it in the lowest-level module that supports it.

Note that the Gradle module directory/path names (`core`, `android`, `lifecycle`) intentionally differ from
their published artifact ids (`sensible-logging-core`, `sensible-logging-android`,
`sensible-logging-lifecycle`); each module sets its artifact id explicitly via
`mavenPublishing { coordinates(artifactId = "...") }` in its `build.gradle.kts`, since the Vanniktech
`maven-publish` plugin otherwise defaults the artifact id to the Gradle project name.

## Code Reviews

All submissions, including submissions by project members, require review. We
use GitHub pull requests for this purpose. Consult
[GitHub Help](https://help.github.com/articles/about-pull-requests/) for more
information on using pull requests.

### Commit, Push \& Review
The entire process of constructing or fixing a feature or bug

1. Make sure there is a Github issue for the task at hand.
2. Branch from the latest `main` and include the issue number for your task in the branch name, e.g: `feature/#123_add_support_for_flux_capacitors` or `bugfix/#123_fix_flux_capacitor` etc.
3. Work on your branch and prepend commit messages with your issue number: `#123: enable the flux capacitor`
4. Make sure each new source file has a license header. Either copy it from another file or refer to the [addlicense tool instructions from Google](https://opensource.google/documentation/reference/releasing/preparing#license-headers)
5. When you are ready, do a pull request and follow the PR template. Make sure you utilise the [closing keywords](https://help.github.com/articles/closing-issues-using-keywords/) for the issue to be closed when merged.
   1. The current license headers was added using: `$ docker run --platform linux/amd64 -it -v ${PWD}:/src ghcr.io/google/addlicense -c "Volvo Cars Corporation" .`
6. When the number of required reviewers for PR approval coupled with CI integration success is passed, __you__ have the responsibility to merge it, do it!
