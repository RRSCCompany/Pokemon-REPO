package DAOs; 
import Entidades.Pokemon; 
import DAOs.DAOGenerico; 
import static DAOs.DAOGenerico.em; 
import java.util.ArrayList; 
import java.util.List;

public class DAOPokemon extends DAOGenerico<Pokemon> { 
    public DAOPokemon()  {
        super(Pokemon.class); 
    }
    public int autoIdPokemon() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPokemon) FROM Pokemon e  ").getSingleResult(); 
        if (a != null) { 
            return a + 1; 
        } else { 
            return 1; 
        }
    } 

    public List<Pokemon> listByNome(String nomePokemon) {

        return em.createQuery("SELECT e FROM Pokemon e WHERE e.nomePokemon) LIKE : nomePokemon").setParameter("nome", "%" +nomePokemon+ "%").getResultList();
    }

    public List<Pokemon> listById(int id) {
        return em.createQuery("SELECT e FROM Pokemon e WHERE e.idPokemon = : id " ).setParameter("id", id).getResultList(); 
    }
    public List<Pokemon> listInOrderNome() { 
        return em.createQuery("SELECT e FROM Pokemon e ORDER BY e.nomePokemon").getResultList();
    }

    public List<Pokemon> listInOrderId() {
        return em.createQuery("SELECT e FROM Pokemon e ORDER BY e.idPokemon").getResultList();
    }


public List<String> listInOrderNomeStrings(String qualOrdem) { 
        List<Pokemon> lf; 
        if (qualOrdem.equals("id")) { 
            lf = listInOrderId(); 
        } else { 
            lf = listInOrderNome(); 
        } 

        List<String> ls = new ArrayList<>(); 
        for (int i = 0; i < lf.size(); i++) { 
            ls.add(lf.get(i).getIdPokemon() + "-" + lf.get(i).getNomePokemon());
        } 
        return ls; 
    } 

    public static void main(String[] args) { 
        DAOPokemon daoPokemon = new DAOPokemon();
        List<Pokemon> listaPokemon = daoPokemon.list(); 
        for (Pokemon pokemon : listaPokemon) { 
            System.out.println(pokemon.getIdPokemon()+ "-" + pokemon.getNomePokemon()); 
        } 
    }

}

