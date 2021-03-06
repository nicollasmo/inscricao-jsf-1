/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inscricao.faces.mngbeans;

import inscricao.entity.Candidato;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Samsung
 */
@ManagedBean
@ApplicationScoped
public class ListaInscricoesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Candidato> inscricoes = new Vector<>();

    private DataModel<Candidato> inscricoesDataModel = new ListDataModel<>(inscricoes);

    public DataModel<Candidato> getInscricoesDataModel() {
        return inscricoesDataModel;
    }

    public void setInscricoesDataModel(DataModel<Candidato> inscricoesDataModel) {
        this.inscricoesDataModel = inscricoesDataModel;
    }

    public List<Candidato> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Candidato> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public boolean adicionarCandidato(Candidato candidato) {
        for (Candidato candidato1 : inscricoes) {
            if (candidato.getCpf() == candidato1.getCpf()) {
                return false;
            }
        }
        this.inscricoes.add(candidato);
        return true;
    }

    public Candidato procurarCPF(int CPF) {
        for (Candidato candidato : inscricoes) {
            if (CPF == candidato.getCpf()) {
                return candidato;
            }
        }
        return null;
    }

    public void alterarCandidato(Candidato candidato) {
        for (Candidato candidato1 : inscricoes) {
            if (candidato.getCpf() == candidato1.getCpf()) {
                this.inscricoes.remove(candidato1);
                this.inscricoes.add(candidato);
            }
        }
    }
    public void removerCandidato(Candidato candidato){
        this.inscricoes.remove(candidato);
    }

}
