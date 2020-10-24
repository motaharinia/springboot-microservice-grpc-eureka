import React from 'react';

class Microservice extends React.Component {
    static serverEnum= {
        "MSLOGIN":"http://mbz.ir:8080",
        "MSGEO": "http://mbz.ir:8080",
    };

    static requestTypeEnum = {
        //enumKey:data
        "GET": "get",
        "POST_HTML": "postHtml",
        "POST_JSON": "postJson",
        "OPTION": "option",
        "PUT": "put"
    };


    static responseDataTypeEnum = {
        //enumKey:data
        "HTML": "html",
        "JSON": "json",
        "XML": "xml",
        "SCRIPT": "script",
        "JSONP": "jsonp",
        "TEXT": "text",
        "BINARY": "binary"
    };

    static mimeTypeEnum = {
        //enumKey:data
        "XML": "application/xml",
        "JSON": "application/json",
        "TEXT": "text/plain",
        "HTML": "text/html",
        "IMAGE": "image/png"
    };




    static urlEnum= {
        "MSLOGIN": {
            "OAUTH":{
               "LOGIN":{
                   href:Microservice.serverEnum.MSLOGIN + "/oauth/token",
                   method:Microservice.requestTypeEnum.POST_HTML ,
                   responseDataType:Microservice.responseDataTypeEnum.HTML,
                   mimeType:Microservice.mimeTypeEnum.JSON

               },
               "LOGOUT":{
                   href:Microservice.serverEnum.MSLOGIN + "/logout",
                   method:Microservice.requestTypeEnum.GET ,
                   responseDataType:Microservice.responseDataTypeEnum.HTML,
                   mimeType:Microservice.mimeTypeEnum.HTML
               }
           },
        },
        "MSGEO": {
            "MEMBERSHIPREQUEST": {
                "CREATE": {
                    href: Microservice.serverEnum.MSGEO + "/membershipRequest/create",
                    method: Microservice.requestTypeEnum.POST_JSON,
                    responseDataType: Microservice.responseDataTypeEnum.JSON,
                    mimeType: Microservice.mimeTypeEnum.JSON
                },
                "CREATEFORMEMBER": {
                    href: Microservice.serverEnum.MSGEO + "/membershipRequest/createForMember",
                    method: Microservice.requestTypeEnum.POST_JSON,
                    responseDataType: Microservice.responseDataTypeEnum.JSON,
                    mimeType: Microservice.mimeTypeEnum.JSON
                },
                "MEETINGDONE": {
                    href: Microservice.serverEnum.MSGEO + "/membershipRequest/meetingDone",
                    method: Microservice.requestTypeEnum.POST_JSON,
                    responseDataType: Microservice.responseDataTypeEnum.JSON,
                    mimeType: Microservice.mimeTypeEnum.JSON
                },
                "MEETINGREJECT": {
                    href: Microservice.serverEnum.MSGEO + "/membershipRequest/reject",
                    method: Microservice.requestTypeEnum.POST_JSON,
                    responseDataType: Microservice.responseDataTypeEnum.JSON,
                    mimeType: Microservice.mimeTypeEnum.JSON
                },
                "MEETINGSET": {
                    href: Microservice.serverEnum.MSGEO + "/membershipRequest/meetingSet",
                    method: Microservice.requestTypeEnum.POST_JSON,
                    responseDataType: Microservice.responseDataTypeEnum.JSON,
                    mimeType: Microservice.mimeTypeEnum.JSON
                }
            }
        }
    };
}



export {
  Microservice
}
