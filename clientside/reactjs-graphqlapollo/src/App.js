import React from 'react';
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";

import {WebSocketLink} from 'apollo-link-ws';
import {HttpLink} from 'apollo-link-http';
import {split} from 'apollo-link';
import {getMainDefinition} from 'apollo-utilities';
import ApolloClient from "apollo-client";
import {InMemoryCache} from 'apollo-cache-inmemory';
import {ApolloProvider} from 'react-apollo';

import AdminUserList from "./page/adminUser/AdminUserList";
import AdminUserCreate from "./page/adminUser/AdminUserCreate"
import AdminUserUpdate from "./page/adminUser/AdminUserUpdate"
import AdminUserView from "./page/adminUser/AdminUserView"
import AdminUserDelete from "./page/adminUser/AdminUserDelete"

//لینک ارتباط وب سوکت برای سابسکریپشن
const wsLink = new WebSocketLink({
    uri: `ws://localhost:8082/graphql`,
    options: {
        reconnect: true
    }
});
//لینک ارتباط وب برای کوئری و میوتیشن
const httpLink = new HttpLink({
    uri: `http://localhost:8082/graphql`,
});

// تشخیص نوع عملیات کوئری ها
const splitLink = split(
    ({query}) => {
        const {kind, operation} = getMainDefinition(query);
        return kind === 'OperationDefinition' && operation === 'subscription';
    },
    wsLink,
    httpLink,
);

// ساخت کلاینت برای ارسال به سرور
const client = new ApolloClient({
    link: splitLink,
    cache: new InMemoryCache({
        addTypename: false
    })
});

export default function App() {
    return (
        <ApolloProvider client={client}>
            <Router>
                <div>
                    <Switch>
                        <Route path="/adminUserDelete/:Id">
                            <AdminUserDelete/>
                        </Route>
                        <Route path="/adminUserView/:Id">
                            <AdminUserView/>
                        </Route>
                        <Route path="/adminUserUpdate/:Id">
                            <AdminUserUpdate/>
                        </Route>
                        <Route path="/adminUserCreate">
                            <AdminUserCreate/>
                        </Route>
                        <Route path="/adminUserList">
                            <AdminUserList/>
                        </Route>
                    </Switch>
                </div>
            </Router>
        </ApolloProvider>
    );
}