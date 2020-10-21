package ir.micser.config.oauth2.custom;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

public class FixIdentifier {
    private static final String CLIENT_FIELDS_FOR_UPDATE = "resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";
    private static final String CLIENT_FIELDS = "client_secret, " + CLIENT_FIELDS_FOR_UPDATE;
    private static final String BASE_FIND_STATEMENT = "select client_id, " + CLIENT_FIELDS
            + " from oauth_client_details";
    private static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";
    private static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";
    private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_client_details (" + CLIENT_FIELDS
            + ", client_id) values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DEFAULT_UPDATE_STATEMENT = "update oauth_client_details " + "set "
            + CLIENT_FIELDS_FOR_UPDATE.replaceAll(", ", "=?, ") + "=? where client_id = ?";
    private static final String DEFAULT_UPDATE_SECRET_STATEMENT = "update oauth_client_details "
            + "set client_secret = ? where client_id = ?";
    private static final String DEFAULT_DELETE_STATEMENT = "delete from oauth_client_details where client_id = ?";
    private static String deleteClientDetailsSql = DEFAULT_DELETE_STATEMENT;
    private static String findClientDetailsSql = DEFAULT_FIND_STATEMENT;
    private static String updateClientDetailsSql = DEFAULT_UPDATE_STATEMENT;
    private static String updateClientSecretSql = DEFAULT_UPDATE_SECRET_STATEMENT;
    private static String insertClientDetailsSql = DEFAULT_INSERT_STATEMENT;
    private static String selectClientDetailsSql = DEFAULT_SELECT_STATEMENT;

    //***********************************************token store sql quotation******************
    private static final String DEFAULT_ACCESS_TOKEN_INSERT_STATEMENT = "insert into oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT = "select token_id, token from oauth_access_token where token_id = ?";
    private static final String DEFAULT_ACCESS_TOKEN_AUTHENTICATION_SELECT_STATEMENT = "select token_id, authentication from oauth_access_token where token_id = ?";
    private static final String DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT = "select token_id, token from oauth_access_token where authentication_id = ?";
    private static final String DEFAULT_ACCESS_TOKENS_FROM_USERNAME_AND_CLIENT_SELECT_STATEMENT = "select token_id, token from oauth_access_token where user_name = ? and client_id = ?";
    private static final String DEFAULT_ACCESS_TOKENS_FROM_USERNAME_SELECT_STATEMENT = "select token_id, token from oauth_access_token where user_name = ?";
    private static final String DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT = "select token_id, token from oauth_access_token where client_id = ?";
    private static final String DEFAULT_ACCESS_TOKEN_DELETE_STATEMENT = "delete from oauth_access_token where token_id = ?";
    private static final String DEFAULT_ACCESS_TOKEN_DELETE_FROM_REFRESH_TOKEN_STATEMENT = "delete from oauth_access_token where refresh_token = ?";
    private static final String DEFAULT_REFRESH_TOKEN_INSERT_STATEMENT = "insert into oauth_refresh_token (token_id, token, authentication) values (?, ?, ?)";
    private static final String DEFAULT_REFRESH_TOKEN_SELECT_STATEMENT = "select token_id, token from oauth_refresh_token where token_id = ?";
    private static final String DEFAULT_REFRESH_TOKEN_AUTHENTICATION_SELECT_STATEMENT = "select token_id, authentication from oauth_refresh_token where token_id = ?";
    private static final String DEFAULT_REFRESH_TOKEN_DELETE_STATEMENT = "delete from oauth_refresh_token where token_id = ?";
    private static String insertAccessTokenSql = DEFAULT_ACCESS_TOKEN_INSERT_STATEMENT;
    private static String selectAccessTokenSql = DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT;
    private static String selectAccessTokenAuthenticationSql = DEFAULT_ACCESS_TOKEN_AUTHENTICATION_SELECT_STATEMENT;
    private static String selectAccessTokenFromAuthenticationSql = DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT;
    private static String selectAccessTokensFromUserNameAndClientIdSql = DEFAULT_ACCESS_TOKENS_FROM_USERNAME_AND_CLIENT_SELECT_STATEMENT;
    private static String selectAccessTokensFromUserNameSql = DEFAULT_ACCESS_TOKENS_FROM_USERNAME_SELECT_STATEMENT;
    private static String selectAccessTokensFromClientIdSql = DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT;
    private static String deleteAccessTokenSql = DEFAULT_ACCESS_TOKEN_DELETE_STATEMENT;
    private static String insertRefreshTokenSql = DEFAULT_REFRESH_TOKEN_INSERT_STATEMENT;
    private static String selectRefreshTokenSql = DEFAULT_REFRESH_TOKEN_SELECT_STATEMENT;
    private static String selectRefreshTokenAuthenticationSql = DEFAULT_REFRESH_TOKEN_AUTHENTICATION_SELECT_STATEMENT;
    private static String deleteRefreshTokenSql = DEFAULT_REFRESH_TOKEN_DELETE_STATEMENT;
    private static String deleteAccessTokenFromRefreshTokenSql = DEFAULT_ACCESS_TOKEN_DELETE_FROM_REFRESH_TOKEN_STATEMENT;

    public static JdbcTokenStore fixTokenStore(JdbcTokenStore jdbcTokenStore) {
        jdbcTokenStore.setInsertAccessTokenSql(insertAccessTokenSql);
        jdbcTokenStore.setSelectAccessTokenSql(selectAccessTokenSql);
        jdbcTokenStore.setSelectAccessTokenAuthenticationSql(selectAccessTokenAuthenticationSql);
        jdbcTokenStore.setSelectAccessTokenFromAuthenticationSql(selectAccessTokenFromAuthenticationSql);
        jdbcTokenStore.setSelectAccessTokensFromUserNameAndClientIdSql(selectAccessTokensFromUserNameAndClientIdSql);
        jdbcTokenStore.setSelectAccessTokensFromUserNameSql(selectAccessTokensFromUserNameSql);
        jdbcTokenStore.setSelectAccessTokensFromClientIdSql(selectAccessTokensFromClientIdSql);
        jdbcTokenStore.setDeleteAccessTokenSql(deleteAccessTokenSql);
        jdbcTokenStore.setInsertRefreshTokenSql(insertRefreshTokenSql);
        jdbcTokenStore.setSelectRefreshTokenSql(selectRefreshTokenSql);
        jdbcTokenStore.setSelectRefreshTokenAuthenticationSql(selectRefreshTokenAuthenticationSql);
        jdbcTokenStore.setDeleteRefreshTokenSql(deleteRefreshTokenSql);
        jdbcTokenStore.setDeleteAccessTokenFromRefreshTokenSql(deleteAccessTokenFromRefreshTokenSql);

        return jdbcTokenStore;
    }

    public static JdbcClientDetailsService fixClientDetailsService(JdbcClientDetailsService clientDetailsService) {
        clientDetailsService.setDeleteClientDetailsSql(deleteClientDetailsSql);
        clientDetailsService.setFindClientDetailsSql(findClientDetailsSql);
        clientDetailsService.setInsertClientDetailsSql(insertClientDetailsSql);
        clientDetailsService.setSelectClientDetailsSql(selectClientDetailsSql);
        clientDetailsService.setUpdateClientDetailsSql(updateClientDetailsSql);
        clientDetailsService.setUpdateClientSecretSql(updateClientSecretSql);

        return clientDetailsService;
    }
}
