package inscricao.faces.mngbeans;

import inscricao.entity.Candidato;
import inscricao.entity.Idioma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import utfpr.faces.support.PageBean;

/**
 *
 * @author Wilson
 */
@ManagedBean
@SessionScoped
public class InscricaoBean extends PageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Idioma[] idiomas = {
        new Idioma(1, "Inglês"),
        new Idioma(2, "Alemão"),
        new Idioma(3, "Francês")
    };
    private int CPF;
    private boolean editar = false;
    private ListaInscricoesBean listaInscricoesBean = (ListaInscricoesBean) getBean("listaInscricoesBean");
    private Candidato candidato = new Candidato(idiomas[0]); // inicialmente ingles
    private List<SelectItem> idiomaItemList;
    private boolean erro = false;
    private boolean encontrou = true;

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public boolean isEncontrou() {
        return encontrou;
    }

    public void setEncontrou(boolean encontrou) {
        this.encontrou = encontrou;
    }

    public ListaInscricoesBean getListaInscricoesBean() {
        return listaInscricoesBean;
    }

    public void setListaInscricoesBean(ListaInscricoesBean listaInscricoesBean) {
        this.listaInscricoesBean = listaInscricoesBean;
    }

    public List<SelectItem> getIdiomaItemList() {
        if (idiomaItemList != null) {
            return idiomaItemList;
        }
        idiomaItemList = new ArrayList<>();
        for (Idioma id : idiomas) {
            idiomaItemList.add(new SelectItem(id.getCodigo(), id.getDescricao()));
        }
        return idiomaItemList;
    }

    public String confirmaAction() {
        if (editar) {
            candidato.setDataHora(new Date());
            candidato.setIdioma(idiomas[candidato.getIdioma().getCodigo() - 1]);
            listaInscricoesBean.alterarCandidato(candidato);
            return "confirma";
        } else {
            candidato.setDataHora(new Date());
            candidato.setIdioma(idiomas[candidato.getIdioma().getCodigo() - 1]);
            boolean b = listaInscricoesBean.adicionarCandidato(candidato);
            if (b) {
                erro = false;
                return "confirma";
            }
            erro = true;
            return "";
        }
    }

    public String pesquisarCPF() {
        candidato = listaInscricoesBean.procurarCPF(CPF);
        if (candidato != null) {
            encontrou = true;
            editar = true;
            return "inscricao";
        }
        encontrou = false;
        return "";
    }

    public String alterar() {
        editar = true;
        erro = false;
        return "inscricao";
    }

    public String cadastrarNovo() {
        editar = false;
        erro = false;
        candidato = new Candidato(idiomas[0]);
        return "inscricao";
    }

    public String novaInscricao() {
        erro = false;
        candidato = new Candidato(idiomas[0]);
        return "";
    }
    public String alterar(Candidato candidato){
        editar = true;
        erro = false;
        this.candidato = candidato;
        return "inscricao";
    }
    public String excluir(Candidato candidato){
        listaInscricoesBean.removerCandidato(candidato);
        return "";
    }
}
