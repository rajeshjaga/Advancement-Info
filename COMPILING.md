# TL;DR

- Get a version of gradle that's at least 8
- `git clone <repo>`
- `/path/to/gradle build`

# How to compile this mod

Because I created several mods, which have some things in common, the structure of my mods is a bit different from the example mod that Fabric or Forge provide.

In particular, I don't want the gradle files to be duplicated into every single mod repository, and some common files that contain version info for Fabric, its tools, and some library mods, have been moved to a (common) submodule.

```./gradlew genSources```

```./gradlew build```
# To Test the mod run 
Do not forget to add cloth config to run/mods and then run

`./gradlew runClient`
# Prerequisites

You need a gradle installation which does not come with the mod. At the time of this writing, the version of gradle used is 8. Have atleast 16gb ram to compile or you might run into issues
