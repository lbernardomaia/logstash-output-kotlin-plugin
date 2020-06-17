# Logstash Output Kotlin Plugin

This Kotlin plugin is based on the Java Output Plugin available here:
 
https://www.elastic.co/guide/en/logstash/current/java-output-plugin.html
 
### To build the plugin

```
./gradlew build gem
```

### To install

```
bin/logstash-plugin install --no-verify --local /path/to/logstash-output-kotlin_output_plugin-0.0.1.gem
```

### To test

Default Conf:
```
bin/logstash -f /path/to/kotlin_output_plugin_default.conf
```
Custom Conf: 
```
bin/logstash -f /path/to/kotlin_output_plugin_custom.conf
```