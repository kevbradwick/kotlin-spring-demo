Kotlin Web API Demo
===================

This is a demo project of how to build and run a [Spring](http://spring.io/) rest api using
[Kotlin](https://kotlinlang.org).

The data is Vehicle Testing Station data taken from [data.gov.uk](https://data.gov.uk/).

### Endpoints

Search by VTS site number.

    GET /vts/site-number/{number}

Search by name e.g. "kwik fit"

    GET /vts/name/{name}
    
Search by post code (full or partial)

    GET /vts/post-code/{postCode}
    
### Running the API

    ./gradlew bootRun
