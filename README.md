# telegram-bot-for-block-1C

[![Maintainability](https://api.codeclimate.com/v1/badges/a99a88d28ad37a79dbf6/maintainability)](https://codeclimate.com/github/codeclimate/codeclimate/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/a99a88d28ad37a79dbf6/test_coverage)](https://codeclimate.com/github/codeclimate/codeclimate/test_coverage)

_Deploying on Oracle WebLogic 12c using MySQL DB with procedure to get OData connect content for selected client in TG Bot._

For deploy on Tomcat need edit web.xml and App.java (add HttpServlet).

Add configuration file in _src/main/resources_ with:

    ## for TelegramBot  
    TOKEN =  
    BOTNAME =  
    GROUP =  

    ## for MYSQL DB  
    HOST =  
    USER =  
    PSW =  
    PROCEDURE =
    
    ## for MS SQL
    HOSTMS = 
    USERMS = 
    PSWMS = 
    TABLEMS = 


In DB MySQL created procedure which return OData connect content by id client, which enter in request TG Bot.
Name and call procedure write in config file at "PROCEDURE" like "PROCEDURE = call get_OData_content".

In DB MSSQL used to record the final action performed by the user in Bot. 