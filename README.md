## Overview

This is a multi-module project that contains Jackson-based JAX-RS providers for following data formats:

* [JSON](https://github.com/FasterXML/jackson-core)
* [Smile](https://github.com/FasterXML/jackson-dataformat-smile) (binary JSON)
* [CBOR](https://github.com/FasterXML/jackson-dataformat-cbor) (another kind of binary JSON)
* [XML](https://github.com/FasterXML/jackson-dataformat-xml)
* [YAML](https://github.com/FasterXML/jackson-dataformat-yaml)

Providers implement JAX-RS `MessageBodyReader` and `MessageBodyWriter` handlers for specific
data formats. They also contain SPI settings for auto-registration.

## Status

[![Build Status](https://travis-ci.org/FasterXML/jackson-jaxrs-providers.svg?branch=master)](https://travis-ci.org/FasterXML/jackson-jaxrs-providers) [![test](https://github.com/victoryang00/jackson-jaxrs-providers/actions/workflows/test.yml/badge.svg)](https://github.com/victoryang00/jackson-jaxrs-providers/actions/workflows/test.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider/)
[![Javadoc](https://javadoc.io/badge/com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider.svg)](http://www.javadoc.io/doc/com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider)
[![Tidelift](https://tidelift.com/badges/package/maven/com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider)](https://tidelift.com/subscription/pkg/maven-com-fasterxml-jackson-jaxrs-jackson-jaxrs-json-provider?utm_source=maven-com-fasterxml-jackson-jaxrs-jackson-jaxrs-json-provider&utm_medium=referral&utm_campaign=readme)

## Maven dependency

To use JAX-RS on Maven-based projects, use dependencies like:

```xml
<dependency>
  <groupId>com.fasterxml.jackson.jaxrs</groupId>
  <artifactId>jackson-jaxrs-json-provider</artifactId>
  <version>2.12.0</version>
</dependency>
```

(above is for JSON provider; modify appropriately for other providers)

## Usage: registering providers

Due to auto-registration, it should be possible to simply add Maven dependency
(or include jar if using other build systems) and let JAX-RS implementation discover
provider.
If this does not work you need to consult documentation of the JAX-RS implementation for details.  

Here are some links that may help:

* [Configure Jackson as JSON Provider in JAX-RS 2.0](https://stackoverflow.com/questions/18741954/configure-jackson-as-json-provider-in-jax-rs-2-0)
* [JAX-RS and Open Liberty: BYO Jackson](https://openliberty.io/blog/2020/11/11/byo-jackson.html)
* [Using Jackson as JSON provider in Jersey 2.x](https://cassiomolin.com/2016/08/10/using-jackson-as-json-provider-in-jersey-2x/)

### Usage: registering supporting datatypes module

Starting with Jackson 2.8, there is a small supporting datatype module, `jackson-datatype-jaxrs` (see
under `datatypes/`).
It will not be auto-registered automatically (unless user calls `ObjectMapper.findAndRegisterModules()`);
instead, user has to register it by normal means:

```java
ObjectMapper mapper = JsonMapper.builder()
    .addModule(new Jaxrs2TypesModule())
    .build();
```

and ensuring that configured mapper is used by JAX-RS providers.

It is possible that later versions of providers may offer additional ways to get datatype module registered.

### Annotations on resources

In addition to annotation value classes, it is also possible to use a subset
of Jackson annotations with provider.

Here is a short list of supported annotations that work with all formats:

* `@JsonView` can be used to define active view for specific endpoint
* `@JsonRootName` can be used to specify alternate rootname; most often used with XML, but possibly with JSON as well.
* `@JacksonAnnotationsInside` meta-annotation may be used as a marker, to create "annotation bundles", similar to how they are used with value type annotations
* `com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures` can be used with all provid to enable/disable
    * `SerializationFeature` / `DeserializationFeature` for data-binding configuration
    * `JsonParser.Feature` / `JsonGenerator.Feature` for low(er) level Streaming read/write options

In addition there are format-specific annotations that may be used:

* JSON has:
    * `com.fasterxml.jackson.jaxrs.json.annotation.JSONP` to define `JSONP` wrapping for serialized result


## Module Considerations
* The JSON/JAX-RS module has multiple names depending on the version in use. To enable modular usage, add the requires statement that pertains directly to the implementation you are using. 
```
requires  javax.ws.rs.api; //Older libraries
requires  java.ws.rs; //Newer libraries
requires  jakarta.ws.rs; //Reserved name for Jakarta API
requires  jakarta.ws.rs.api; //Reserved name for Jakarta Impl
```

### Using Jakarta

A note on compatibility of JAXB annotations module, Jakarta 3.0 API libraries for JAXB: with Jackson 2.12,
Jakarta versions can be referenced for the JAXB module by using the classifier "jakarta" in your dependency

```
<dependency>
    <groupId>com.fasterxml.jackson.jaxrs</groupId>
    <artifactId>jackson-jaxrs-json</artifactId>
    <classifier>jakarta</classifier>
</dependency>
``` 

## Support

### Community support

Jackson components are supported by the Jackson community through mailing lists, Gitter forum, Github issues. See [Participation, Contributing](../../../jackson#participation-contributing) for full details.

### Enterprise support

Available as part of the Tidelift Subscription.

The maintainers of `jackson-jaxrs-providers` and thousands of other packages are working with Tidelift to deliver commercial support and maintenance for the open source dependencies you use to build your applications. Save time, reduce risk, and improve code health, while paying the maintainers of the exact dependencies you use. [Learn more.](https://tidelift.com/subscription/pkg/maven-com-fasterxml-jackson-jaxrs-jackson-jaxrs-json-provider?utm_source=maven-com-fasterxml-jackson-jaxrs-jackson-jaxrs-json-provider&utm_medium=referral&utm_campaign=enterprise&utm_term=repo)

-----

## Other

For documentation, downloads links, check out [Wiki](../../wiki)
