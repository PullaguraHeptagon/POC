# POC
Student POC

1. change "spring.datasource.url" in application.properties to your local workspace file.
2. Do Maven clean install
3. Start the spring boot application.
4. Open http://localhost:8080/swagger-ui.html
5. Goto AuthController do an initial signup by following request.

{
    "username":"Sample Username",
    "email":"Sample Email",
    "password":"Sample Password"
}

6. Afterwards, in the same AuthController do the signin to get the Authrization Token. Properties are probably above ones which we created previously.

{
    "username":"Sample Username",
    "password":"Sample Password"
}

7. Collect the token value from the response.
8. Use this token as a Bearer token.

9. You can view the HSQL console on http://localhost:8080/h2-console
