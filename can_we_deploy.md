# Task 1: Can we deploy?

As QA , our role should be always aiming to provide high quality working product . Provided Pet API achieved being only 
working product but with many findings which make me definitely recommending not to deploy it due to below findings :

1. Pet API doesn't have any used Authorization
    * Deploying API without authorization means that anyone can access it without any restriction 
    * Having such case may lead to different Security risks like Data exposure for not authorized users , Injecting malicious data into our DB

 * My recommendation : Increase our api security by applying one authorization type 

2. The HTTP methods and their related status codes are designed with specific semantics to ensure clarity and predictability in how they are used
and based on provided swagger provided as API Documentation , I found :

    * HTTP Status Code of GET Method in case resource has been found should be 200 OK , however it is 201 Created
       * My recommendation : Change HTTP status code to be 200 OK in such case
      
    * HTTP Status Code of GET Method in case resource has not been found should be 404 Not Found , however it is 201 Created
       * My recommendation : Change HTTP status code to be 404 Not Found in such case
      
    * HTTP Status Code for deleting not existing resource be 204 No Content , however it is 200 OK
       * My recommendation : Change HTTP status code to be 204 No Content in such case
      
    * PetID declared as Integer Parameter , when it is sent with invalid format or not sent at all for GET/Remove/Update, 
      API should fail giving HTTP Status 400 Bad Request 
       * My recommendation : Apply validation on received Pet ID
         * In case validation failure for GET Pet with invalid Pet ID , change status code from 201 Created to 400 Bad Request
         * In case validation failure for PUT Pet with invalid Pet ID , change status code from 200 OK to 400 Bad Request
         * In case validation failure for DELETE Pet with invalid Pet ID , change status code from 200 OK to 400 Bad Request
    
    * POST Method should not be used to Update resource , it should be used to create new resource , submit data for processing.
       * My recommendation : Fix Swagger Specification as PUT is already working for updating existing resource
      
    * Using POST Method for deleting resource from server is not recommended , it is against the standard usage of HTTP methods
       * My recommendation : Instead of using POST Method for deleting existing Pet , we should use another appropriate method as DELETE

   
3. In context of Schema validation , it is mentioned that Pet category max length should be 50. In such case API should validate received category
if it greater than 50 digits , then API should fail the in case of POST/PUT methods with Status Code 400 . So that we can avoid having schema validation failure 
if the created Pet with exceeded category length was called by GET Method
  * My recommendation : Apply validation on Category field max length received in case of POST/PUT methods request . If it is >50 digits , reply back with 400 Bad Request Status Code
