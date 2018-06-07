# Mr. Build Server

## Background

Build pipelines are really a part of your software.  The most enabling part of your software.  Jez Humble discusses the idea of the walking skeleton.  This is the idea that prior to any real work being put into the nuts and bolts of the features you intend to deliver, a skeleton system with a fully automated delivery to production should be in place. 

Further, the shape and specifics of your delivery pipeline will tend to be in step with the architecture of your system.

So the build system should allow incremental addition.  It should be entirely based in code, so that it can be controlled and stored along side the feature code that makes up the system.  

## Approach 

Each instance of Mr. Build Server will be delivered as a single docker image that will allow the user to define the infrastructure that supports it.  This isolates any usage and scaling specifically to the system that will be using it.  

The one element that is centralized is the Dashboard - each of the instances can connect to one or more Dashboard, so that interested parties can view build status and other elements of the individual pipelines as appropriate.

## Building & Testing

You'll need a local docker host to start.  (https://www.docker.com)

To build and load the image locally, and then run integration tests: `sbt clean docker:publishLocal it:test` 


## Contributing 

Lots of work to do.  All help appreciated.
