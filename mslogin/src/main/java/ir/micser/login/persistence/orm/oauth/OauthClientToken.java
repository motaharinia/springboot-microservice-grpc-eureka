/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.micser.login.persistence.orm.oauth;


import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Dev3
 */
@Entity
@Table(name = "oauth_client_token")
public class OauthClientToken implements Serializable {

    @Id
    @Column(name = "authentication_id")
    private String authentication_id;

    @Column(name = "token_id")
    private String token_id;

    @Lob
    @Column(name = "token")
    private Byte[] token;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "client_id")
    private String client_id;

    //getter-setter:
    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public Byte[] getToken() {
        return token;
    }

    public void setToken(Byte[] token) {
        this.token = token;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

}
