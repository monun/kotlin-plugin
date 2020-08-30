# Kotlin compatible Bukkit plugin

### Support for Bukkit plugins compiled with Kotlin.

**Write the following in *plugin.yml* file.**
```yaml
...
depend: [kotlin]
...
```

**To include coroutines library, add the project property when executing tasks.**
```
gradlew shadowJar -PwithCoroutines
... or
gradlew distJar -PwithCoroutines
```