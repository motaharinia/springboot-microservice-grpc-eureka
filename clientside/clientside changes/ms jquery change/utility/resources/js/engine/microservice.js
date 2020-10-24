

var serverEnum = {
    "MSLOGIN":"http://mbz.ir:8080",
        "MSGEO": "http://mbz.ir:8080",
};
var requestTypeEnum = {
    //enumKey:data
    "GET": "get",
        "POST_HTML": "postHtml",
        "POST_JSON": "postJson",
        "OPTION": "option",
        "PUT": "put"
};
var responseDataTypeEnum = {
    //enumKey:data
    "HTML": "html",
        "JSON": "json",
        "XML": "xml",
        "SCRIPT": "script",
        "JSONP": "jsonp",
        "TEXT": "text",
        "BINARY": "binary"
};
var mimeTypeEnum = {
    //enumKey:data
    "XML": "application/xml",
        "JSON": "application/json",
        "TEXT": "text/plain",
        "HTML": "text/html",
        "IMAGE": "image/png"
};

var microservice = {


    "urlEnum":{
        "MSGEO": {
            "COUNTRY": {
                "CREATE": {
                    "href": serverEnum.MSGEO + "/backPanel/country/create",
                    "method": requestTypeEnum.POST_JSON,
                    'responseDataType': responseDataTypeEnum.JSON

                }
            }
        }
    }
}

