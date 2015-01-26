"use strict";

angular.module('es.logongas.ix3.configuration').constant("ix3UserConfiguration", {
    bootstrap: {
        version: 3
    },
    server: {
        api: getContextPath() + "/api"
    },
    format: {
        date: {
            default: "dd/MM/yyyy"
        }
    },
    pages: {

    },
    security: {
        defaultStatus: 200,
        acl: [
        ]
    },
    crud: {
        defaultParentState:"",
        defaultHtmlBasePath:"views"
    }
});