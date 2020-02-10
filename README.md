### Setup

In order to run the app, the `application.properties` file needs
to be configured with the mysql path, username and password.

After that, run `mvn spring-boot:run` in terminal in the root
of the project in order to start the server.

### Implementation

I've chosen Spring Boot as the framework of choice because setup
is really fast and I have some experience with it.

I've chosen MySQL as I'm also familiar with ORM configuration.

### Design choices

Because direct peer-to-peer messages can be achieved by creating
group chats with only 2 participants, it seems unreasonable to have
custom implementation for p2p messaging. (a further improvement would
be to mark those group chats as p2p and forbid adding of other participants).

When querying for messages in a group chat, I've decided to change
the url from `[GET] /v1/messages` to `[GET] /v1/messages/{group_chat_id}`
as the GroupChat id had to be passed as a parameter for this query,
and it seemed natural to have it as a path variable.

Also, for authenticated requests (querying group messages and posting
group messages), the authorization token used is the actual user-id. The
reasoning behind being that I wanted to have a flow as "close as it gets"
to an actually authorised flow (getting hold of the User entity by their
authorization token).