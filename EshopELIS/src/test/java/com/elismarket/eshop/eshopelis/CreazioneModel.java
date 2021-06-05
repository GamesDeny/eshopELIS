package com.elismarket.eshop.eshopelis;

import com.elismarket.eshop.eshopelis.helper.CategoriaHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.helper.TipoMetodoHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class CreazioneModel {

    @Autowired
    static UtenteHelper utenteHelper;

    @Autowired
    static CategoriaHelper categoriaHelper;

    @Autowired
    static TipoMetodoHelper tipoMetodoHelper;

    @Autowired
    static ProdottoHelper prodottoHelper;

    public static Categoria creaCategoria(String nome) {
        Categoria c = new Categoria();

        c.setNome(nome);

        return c;
    }

    public static Feedback creaFeedback(String oggetto, Long utente_id) {
        Feedback f = new Feedback();

        f.setOggetto(oggetto);
        f.setDescrizione("1");
        f.setUtente(utenteHelper.findById(utente_id));

        return f;
    }

    public static Pagamento creaPagamento(Long tipoMetodo_id) {
        Pagamento p = new Pagamento();

        p.setDescrizione("1");
        p.setIsDefault(Boolean.TRUE);
        p.setTipoMetodo(tipoMetodoHelper.findById(tipoMetodo_id));

        return p;
    }

    public static Prodotto creaProdotto(String nome, Long utente_id) {
        Prodotto p = new Prodotto();

        p.setNome(nome);
        p.setDescrizione("1");
        p.setPrezzo(1f);
        p.setMinOrd(1);
        p.setMaxOrd(10);
        p.setSconto(0);
        p.setQuantita(10);
        p.setImage("1");
        p.setUtente(utenteHelper.findById(utente_id));

        return p;
    }

    public static Proposta creaProposta(String nome, Long utente_id, Long categoria_id) {
        Proposta p = new Proposta();

        p.setNome(nome);
        p.setPrezzoProposto(1f);
        p.setDescrizione("1");
        p.setQuantita(10);
        p.setSubmissionDate(LocalDate.now());
        p.setUtente(utenteHelper.findById(utente_id));
        p.setCategoria(categoriaHelper.findById(categoria_id));

        return p;
    }

    public static RigaOrdine creaRigaOrdine(Long prodotto_id) {
        RigaOrdine r = new RigaOrdine();
        Prodotto p = prodottoHelper.findById(prodotto_id);

        r.setPrezzoTotale(p.getPrezzo());
        r.setScontoApplicato(p.getPrezzo() * p.getSconto());
        r.setQuantitaProdotto(p.getQuantita() % 4);
        r.setProdotto(prodottoHelper.findById(prodotto_id));

        return r;
    }

    public static TipoMetodo creaTipoMetodo(String nome) {
        TipoMetodo t = new TipoMetodo();

        t.setNome(nome);

        return t;
    }

    public static Utente creaUtente(String nome, Boolean isAdmin, Integer siglaResidenza) {
        Utente u = new Utente();

        u.setNome(nome);
        u.setCognome(nome);
        u.setLogged(Boolean.FALSE);
        u.setDataNascita(LocalDate.of(2001, 1, 1));
        u.setIsAdmin(isAdmin);

        u.setUsername(nome);
        u.setPassword(Utente.hashPassword(nome.toUpperCase() + nome + nome + nome + nome + nome + 1));
        u.setMail(nome + nome + nome + "@" + nome + nome + nome + "." + nome + nome);
        u.setSiglaResidenza(siglaResidenza);

        return u;
    }

}

