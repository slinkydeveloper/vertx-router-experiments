# Vertx-router-experiments
Code for benchmarking experiments about a Router implementation

## Create a new Router implementation
There are some scripts that helps you to test and benchmark a new Router implementation:

```bash
./generate_benchmark.sh SimpleClassName
```

```bash
./generate_test.sh SimpleClassName
```

The method Router.initializeForBench() should initialize the router for this routes:

```
/feed
/users/popular
/users/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})
/users/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/events
/users/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/likes
/users/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/pages
/users/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/friends
/users/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/feed
/posts/popular
/posts/:post_id/tagged
/posts/:post_id/photos
/posts/:post_id/photos/:photo_id
/events/popular
/events/:event_id
/events/:event_id/partecipants
/events/:event_id/invited
/events/:event_id/feed
/pages/popular
/pages/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})
/pages/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/likes
/pages/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/events
/pages/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/feed
/pages/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})/feed/(?<postid>[a-zA-Z][a-zA-Z0-9]{3,20})
```

The routes params can be only alphanumeric of size from 3 to 20 chars, and they don't finish with `/`

Note that implementing `addRoute(String)` and `addRoute(Pattern)` the default implementation of `Router.initializeForBench()` will automatically fill the `Router`

## Generate graphs

```
cd chart-generator
npm install
node index.js
```

