Application can be run from MailApplication class file.

For visible and for testing application I would recommend using Swagger after running application under link:

http://localhost:8080/swagger-ui.html#/

There is also additional part of application out of what was said in instruction file.

By using Swagger first we need to create new Message. To do so we use message controller:

- POST /api/message - createMessage

In method body from suggested by swagger method body we should remove only Message Id field to avoid error. It is because of possibility that Id that we choose may already exist. So we should use only other fields.

Other methods are:

- GET /api/messages - getAllMessages - Get all messages that exist in Db

- GET /api/message/{messageId} - getMessageById - Get message with selected Id

- GET /api/messages/{emailValue} - getMessagesByEmail - Get all messages with provided email from Db

- PUT /api/message - updateMessage - Update Message if we need change something other than Id

- POST /api/send - sendMessages - Send messages with selected "Magic Number". Sent messages are send to real existing email and printed into console. Sent messages are deleted from Messages Db and moved into Sent Messages Db for backup.

- DELETE /api/message/{messageId} - deleteMessage - Delete message with selected Id

In Swagger under Sent Message Controller we have methods to manage Sent Messages moved there after sending them. This methods are: DELETE /api/sent_message/{sentMessageId} - deleteSentMessage - Delete message with selected Id

- GET /api/sent_messages - getAllMessages - Get all messages that exist in Db

- GET /api/sent_messages/{emailValue} - getMessagesByEmail - Get all messages with provided email from Db

- DELETE /api/sent_messages/{sentMessageId} - deleteSentMessage - Delete message with selected Id


Application have also:
 - Test that cover around 80% of the code and classes;
 - Validation of input data not allowing to insert unexpected format