/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inscricao.faces.mngbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Samsung
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private String login;

    private String senha;

    private boolean logado = true;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public String realizarLogin() {
        if (login.equals("admin") && senha.equals("admin")) {
            this.logado = true;
            return "inscricao";
        }
        this.logado = false;
        return null;
    }
}
