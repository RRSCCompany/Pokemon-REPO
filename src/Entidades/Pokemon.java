/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Renan
 */
@Entity
@Table(name = "pokemon")
@NamedQueries({
    @NamedQuery(name = "Pokemon.findAll", query = "SELECT p FROM Pokemon p")})
public class Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pokemon")
    private Integer idPokemon;
    @Basic(optional = false)
    @Column(name = "nome_pokemon")
    private String nomePokemon;
    @Basic(optional = false)
    @Column(name = "tipo_pokemon")
    private String tipoPokemon;
    @Basic(optional = false)
    @Column(name = "forca_pokemon")
    private double forcaPokemon;
    @Basic(optional = false)
    @Column(name = "saudavel_pokemon")
    private short saudavelPokemon;

    public Pokemon() {
    }

    public Pokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public Pokemon(Integer idPokemon, String nomePokemon, String tipoPokemon, double forcaPokemon, short saudavelPokemon) {
        this.idPokemon = idPokemon;
        this.nomePokemon = nomePokemon;
        this.tipoPokemon = tipoPokemon;
        this.forcaPokemon = forcaPokemon;
        this.saudavelPokemon = saudavelPokemon;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getNomePokemon() {
        return nomePokemon;
    }

    public void setNomePokemon(String nomePokemon) {
        this.nomePokemon = nomePokemon;
    }

    public String getTipoPokemon() {
        return tipoPokemon;
    }

    public void setTipoPokemon(String tipoPokemon) {
        this.tipoPokemon = tipoPokemon;
    }

    public double getForcaPokemon() {
        return forcaPokemon;
    }

    public void setForcaPokemon(double forcaPokemon) {
        this.forcaPokemon = forcaPokemon;
    }

    public short getSaudavelPokemon() {
        return saudavelPokemon;
    }

    public void setSaudavelPokemon(short saudavelPokemon) {
        this.saudavelPokemon = saudavelPokemon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPokemon != null ? idPokemon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pokemon)) {
            return false;
        }
        Pokemon other = (Pokemon) object;
        if ((this.idPokemon == null && other.idPokemon != null) || (this.idPokemon != null && !this.idPokemon.equals(other.idPokemon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPokemon + ";" + nomePokemon + ";" + tipoPokemon + ";" + forcaPokemon + ";" + (saudavelPokemon==1?"Sim":"Não");
    }
    
}
