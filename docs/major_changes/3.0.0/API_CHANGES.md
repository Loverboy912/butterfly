
# Butterfly 3.0.0 API Changes

This page documents all API changes introduced by Butterfly 3.0.0.

Notice that it does not include additions, that is documented in [Butterfly 3.0.0 New Features](https://paypal.github.io/butterfly/major_changes/3.0.0/NEW_FEATURES.md).

### Moved classes, interfaces and packages

| From | To | Notes |
|---|---|---|
|`com.paypal.butterfly.facade`|`com.paypal.butterfly.api`|Notice API project `butterfly-api` has been created, replacing `butterfly-facade`.|
|`com.paypal.butterfly.extensions.api.metrics`|`com.paypal.butterfly.api`|Those interfaces were moved to `butterfly-api` project. Also, `AbortDetails` class was converted to an interface.|
|`com.paypal.butterfly.facade.Configuration`|`com.paypal.butterfly.api.Configuration`|This class was converted to an interface and moved to `butterfly-api` project. Notice also that its constructor and setters are not available anymore. To create a `Configuration` object, use the `newConfiguration()` methods in `com.paypal.butterfly.api.ButterflyFacade`.|
|`com.paypal.butterfly.facade.ButterflyFacade`|`com.paypal.butterfly.api.ButterflyFacade`|This interface was moved to `butterfly-api` project. Notice also that now every `transform` method returns `com.paypal.butterfly.api.TransformationResult` and don't throw `ButterflyException` anymore (except the ones that take the template class as a `String` parameter).|

### Removed classes and interfaces

Notice that all these removed classes and interfaces were already marked as deprecated in the latest minor versions of Butterfly 2.

| Class or interface | Replacement | Notes | TO BE DEPRECATED |
|---|---|---|---|
|`com.paypal.butterfly.utilities.xml.XmlElement`|`com.paypal.butterfly.utilities.xml.XmlXPathElement`|||
|`com.paypal.butterfly.extensions.api.upgrade.UpgradePath`|`None`|This class has been removed from the API in favor of simplicity. Users can request upgrade transformations by using regular transform methods under `ButterflyFacade`|YES|

### Removed methods

Notice that all these removed methods were already marked as deprecated in the latest minor versions of Butterfly 2.

| Method | Replacement | Notes | TO BE DEPRECATED |
|---|---|---|:---:|
|`com.paypal.butterfly.extensions.api.TransformationUtility.abortOnFailure()`|`com.paypal.butterfly.extensions.api.TransformationUtility.isAbortOnFailure()`||YES|
|`com.paypal.butterfly.extensions.api.TransformationUtility.abortOnFailure(boolean, String)`|`com.paypal.butterfly.extensions.api.TransformationUtility.abortOnFailure(String)`||YES|
|`com.paypal.butterfly.utilities.maven.MavenGoal.setFailAtEnd()`|NA|Removed after upgrading `org.apache.maven.shared:maven-invoker` from version 2.2 to 3.0.1, which removed method `org.apache.maven.shared.invoker.InvocationRequest.setFailureBehavior(String)`|YES|
|`com.paypal.butterfly.extensions.api.metrics.AbortDetails.getExceptionClass()`|`com.paypal.butterfly.api.AbortDetails.getExceptionClassName()`||YES|
|`com.paypal.butterfly.facade.Configuration.Configuration()`|`com.paypal.butterfly.api.ButterflyFacade.newConfiguration()`|`Configuration` class has been converted to an interface. The factory method in the facade should be used instead to get a new configuration object|YES|
|`com.paypal.butterfly.facade.Configuration.Configuration(File, boolean)`|`com.paypal.butterfly.api.ButterflyFacade.newConfiguration()`|`Configuration` class has been converted to an interface. The factory method in the facade should be used instead to get a new configuration object|YES|
|`com.paypal.butterfly.facade.api.ButterflyFacade.getRegisteredExtension()`|`com.paypal.butterfly.facade.api.ButterflyFacade.getExtensions()`|Butterfly 3 supports multiple extensions. With this change, a list of extensions is returned, instead of just one|YES|

### Methods whose signature didn't change, but documented result did

This section list methods that had their signature preserved, in terms of return type and list of parameters, but that might have had their contract changed in terms of documented behavior or exceptions they might throw.

| Method | What changed |
|---|---|
|`com.paypal.butterfly.extensions.api.Extension.getRootPomFile(File)`|Instead of returning `null` it now throws `IOException`, if pom file does not exist, or any error happens when trying to read it. Also it throws `XmlPullParserException` if any error happens when trying to parse the pom file.|
|`com.paypal.butterfly.extensions.api.Extension.automaticResolution(File)`|If no transformation template can be resolved, instead of throwing an exception, `null` is returned.|

### CLI changes

1. The format for the JSON result file generated by the CLI, if option `-r` is used, has changed. Basically it contains now more information than it used too, including transformation metrics. Read end user documentation for further details.

### Other changes

1. Transformation utility `com.paypal.butterfly.utilities.file.FindFiles` now returns `com.paypal.butterfly.extensions.api.TUExecutionResult.Type.VALUE`, instead of `com.paypal.butterfly.extensions.api.TUExecutionResult.Type.WARNING`, if no files are found.