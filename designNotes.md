# SocialConferenceCurator - Initial Design Notes

Some very rough initial design notes.

## Components

### Server

- get tweets from Twitter
  - perform regularly (*check Twitter API usage limits*)
  - transform into object
  - store in moderator queue
    - *and regularly backup to database?*
- process curated tweet collection
  - add to display and/or featured collections
  - remove from moderator queue
- send moderator collection to moderator
- send featured collection to presenter
- publish display collection
  - automatically remove older tweets
- process request for feature display
  - replace usual display collection with featured tweet
    - *possibly overlay?*

### Moderator

- request tweets (*should this be automatic?*)
- mark tweets as display/delete/feature
- send moderated tweets to server
  - probably don't need to send deleted tweets (provided server has way of determining deleted (e.g. sequential ID))

### Presenter

- automatically receive updates to featured collection (*do I actually need server to be intermediary for this collection?*)
- request feature tweet display
- delete tweets from feature collection (*maybe it would be useful to keep collection of all feature on server for later retrieval?*)

## Implementation

- java for server
- android apps for clients
  - *would web apps be better?*

## Objects

- tweets
  - *and tweet contents?*
- display page
- feature page
- moderator
- presenter
- collection manager
- tweet retriever
- collection displayer

### Tweet

- fields:
  - status: unmoderated/delete/display/feature (*use an enum here?*)
  - content: json or another object?
  - seqID: int
- methods:
  - basic getters and setters
    - *or should I have delete(), display(), feature()?*
  - isTransmittable() (status != delete)

### Moderator Object

- methods:
  - getMediaQueue()
  - sendMedia()

### Presenter Object

- methods:
  - requestFeatureDisplay()

### CollectionManager

- methods:
  - sendToBeModerated()
  - receiveModerated()
  - sendToPresenter()

## Extensions

- other social media
- multiple moderators/presenters/media streams
- multiple output options

## Web Sockets

- how to tell when connection has been closed from either end?