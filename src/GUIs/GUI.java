
////////////GUI////////////

package GUIs;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import tools.ManipulaArquivo;

import tools.DateTextField;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;import java.util.logging.Logger;
import DAOs.DAOPokemon;
import Entidades.Pokemon;
import java.awt.Point;
import tools.JanelaPesquisar;
public class GUI extends JFrame {

private Container cp;


private JCheckBox cbSaudavel = new JCheckBox("Saudavel", false);
private JLabel lbvazio = new JLabel("");

private JLabel lbID = new JLabel("ID");
private JLabel lbNome = new JLabel("Nome");
private JLabel lbNatureza = new JLabel("Natureza");
private JLabel lbForca = new JLabel("Força");



private JTextField tfID = new JTextField(50);
private JTextField tfNome = new JTextField(50);
private JTextField tfNatureza = new JTextField(50);
private JTextField tfForca = new JTextField(50);
private JTextField tfSaudavel = new JTextField(50);

private JButton btAdicionar = new JButton("Adicionar");
private JButton btListar = new JButton("Listar");
private JButton btLocalizar = new JButton("Localizar");
private JButton btBuscar = new JButton("Buscar");
private JButton btAlterar = new JButton("Alterar");
private JButton btExcluir = new JButton("Excluir");
private JButton btSalvar = new JButton("Salvar");
private JButton btCancelar = new JButton("Cancelar");
private JButton btCarregarDados = new JButton("Carregar");
private JButton btGravar = new JButton("Gravar");
private JToolBar toolBar = new JToolBar();
private JPanel painelNorte = new JPanel();
private JPanel painelCentro = new JPanel();
private JPanel painelSul = new JPanel();
private JTextArea texto = new JTextArea();
private JScrollPane scrollTexto = new JScrollPane();
private JScrollPane scrollTabela = new JScrollPane();

private String acao = "";
private String chavePrimaria = "";

private DAOPokemon controle = new DAOPokemon();
private Pokemon entidade = new Pokemon();


String[] colunas = new String[]{ "ID", "Nome", "Natureza", "Forca", "Saudavel"};
String[][] dados = new String[0][5];
DefaultTableModel model = new DefaultTableModel(dados, colunas);
JTable tabela = new JTable(model);

private JPanel painel1 = new JPanel(new GridLayout(1, 1));
private JPanel painel2 = new JPanel(new GridLayout(1, 1));
private CardLayout cardLayout;

public GUI() {



setDefaultCloseOperation(DISPOSE_ON_CLOSE);

setSize(600, 500);
setTitle("CRUD Canguru - V6a");
setLocationRelativeTo(null);//centro do monitor

cp = getContentPane()

;cp.setLayout(new BorderLayout());
cp.add(painelNorte, BorderLayout.NORTH);
cp.add(painelCentro, BorderLayout.CENTER);
cp.add(painelSul, BorderLayout.SOUTH);

cardLayout = new CardLayout();
painelSul.setLayout(cardLayout);

painel1.add(scrollTexto);
painel2.add(scrollTabela);

texto.setText("\n\n\n\n\n\n");//5 linhas de tamanho
scrollTexto.setViewportView(texto);

painelSul.add(painel1, "Avisos");
painelSul.add(painel2, "Listagem");
tabela.setEnabled(false);

painelNorte.setLayout(new GridLayout(1, 1));
painelNorte.add(toolBar);

painelCentro.setLayout(new GridLayout(4, 2));


painelCentro.add(lbNome);
painelCentro.add(tfNome);
painelCentro.add(lbNatureza);
painelCentro.add(tfNatureza);
painelCentro.add(lbForca);
painelCentro.add(tfForca);
painelCentro.add(cbSaudavel);
painelCentro.add(lbvazio);

toolBar.add(lbID);
toolBar.add(tfID);
toolBar.add(btAdicionar);
toolBar.add(btLocalizar);
toolBar.add(btBuscar);
toolBar.add(btListar);
toolBar.add(btAlterar);
toolBar.add(btExcluir);
toolBar.add(btSalvar);
toolBar.add(btCancelar);

btAdicionar.setVisible(false);

btAlterar.setVisible(false);
btExcluir.setVisible(false);
btSalvar.setVisible(false);
btCancelar.setVisible(false);


tfNome.setEditable(false);
tfNatureza.setEditable(false);
tfForca.setEditable(false);
cbSaudavel.setEnabled(false);
texto.setEditable(false);


tfNome.setText("Coloque o nome de seu Pokemon!");
tfNatureza.setText("Qual a Natureza dele?");
tfForca.setText("Qual sua força?");
cbSaudavel.setSelected(false);

btBuscar.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
      btAdicionar.setVisible(false);

      cardLayout.show(painelSul, "Avisos");
      scrollTexto.setViewportView(texto);
      if (tfID.getText().trim().isEmpty()) {
         JOptionPane.showMessageDialog(cp, "ID nâo pode ser vazio");
         tfID.requestFocus();
         tfID.selectAll();
      } else {
         chavePrimaria = tfID.getText();//para uso no adicionar
         entidade = controle.obter(Integer.valueOf(tfID.getText()));
         if (entidade == null) {//nao encontrou
            btAdicionar.setVisible(true);
            btAlterar.setVisible(false);
            btExcluir.setVisible(false);
            tfNome.setText("");
            tfNatureza.setText("");
            tfForca.setText("");
            cbSaudavel.setSelected(false);
            texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");//limpa o campo texto

         } else {//encontrou
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            tfNome.setText(entidade.getNomePokemon());
            tfNatureza.setText(entidade.getTipoPokemon());
            tfForca.setText(String.valueOf(entidade.getForcaPokemon()));
            
            cbSaudavel.setSelected(entidade.getSaudavelPokemon()==1?true:false);

            btAlterar.setVisible(true);
            btLocalizar.setVisible(false);
            btExcluir.setVisible(true);
            texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");//limpa o campo texto

            tfNome.setEditable(false);
            tfNatureza.setEditable(false);
            tfForca.setEditable(false);
            cbSaudavel.setEnabled(false);
         }
      }
   }
});
btAdicionar.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
      acao = "adicionar";
      tfID.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
      tfID.setEditable(false);
      tfNome.requestFocus();
      btSalvar.setVisible(true);
      btCancelar.setVisible(true);
      btBuscar.setVisible(false);
      btLocalizar.setVisible(false);
      btListar.setVisible(false);
      btAlterar.setVisible(false);
      btExcluir.setVisible(false);

      btAdicionar.setVisible(false);
      texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
      tfNome.setEditable(true);
      tfNatureza.setEditable(true);
      tfForca.setEditable(true);
      cbSaudavel.setEnabled(true);
   }
});

btAlterar.addActionListener(new ActionListener() {   @Override
   public void actionPerformed(ActionEvent e) {
      acao = "alterar";
      tfID.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
      tfID.setEditable(false);
      tfNome.requestFocus();
      btSalvar.setVisible(true);
      btCancelar.setVisible(true);
      btBuscar.setVisible(false);
      btLocalizar.setVisible(false);
      btListar.setVisible(false);
      btAlterar.setVisible(false);
      btExcluir.setVisible(false);
      texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
      tfNome.setEditable(true);
      tfNatureza.setEditable(true);
      tfForca.setEditable(true);
      cbSaudavel.setEnabled(true);
   }
});

btCancelar.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
      btSalvar.setVisible(false);
      btCancelar.setVisible(false);
      btBuscar.setVisible(true);
      btListar.setVisible(true);
      tfID.setEditable(true);

      tfNome.setText("");
      tfNatureza.setText("");
      tfForca.setText("");
      cbSaudavel.setSelected(false);

      tfID.requestFocus();
      tfID.selectAll();
      texto.setText("Cancelou\n\n\n\n\n");//limpa o campo texto

      tfNome.setEditable(false);
      tfNatureza.setEditable(false);
      tfForca.setEditable(false);
      cbSaudavel.setEnabled(false);
   }
});

btSalvar.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
      if (acao.equals("alterar")) {
         Pokemon entidadeAntigo = entidade;
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
          SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");

         entidade.setNomePokemon(tfNome.getText());
         entidade.setTipoPokemon(tfNatureza.getText());
         entidade.setForcaPokemon(Double.valueOf(tfForca.getText()));
         short vshort = 0;
          if (cbSaudavel.isSelected()==true) {
              vshort = 1;
          }
         entidade.setSaudavelPokemon(vshort);

         controle.atualizar(entidade);
          texto.setText("Registro alterado\n\n\n\n\n");//limpa o campo texto
      } else {//adicionar
         entidade = new Pokemon();
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
          SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");

         entidade.setIdPokemon(Integer.valueOf(tfID.getText()));
         entidade.setNomePokemon(tfNome.getText());
         entidade.setTipoPokemon(tfNatureza.getText());
         entidade.setForcaPokemon(Double.valueOf(tfForca.getText()));
         short vshort = 0;
          if (cbSaudavel.isSelected()==true) {
              vshort = 1;
          }
         entidade.setSaudavelPokemon(vshort);

         controle.inserir(entidade);
         texto.setText("Foi adicionado um novo registro\n\n\n\n\n");//limpa o campo texto
      }
      btSalvar.setVisible(false);
      btCancelar.setVisible(false);
      btBuscar.setVisible(true);
      btListar.setVisible(true);
      btLocalizar.setVisible(true);
      tfID.setEditable(true);

      tfNome.setText("");
      tfNatureza.setText("");
      tfForca.setText("");
      cbSaudavel.setSelected(false);

      tfID.requestFocus();
      tfID.selectAll();

      tfNome.setEditable(false);
      tfNatureza.setEditable(false);
      tfForca.setEditable(false);
      cbSaudavel.setEnabled(false);
   }
});

btExcluir.addActionListener(new ActionListener() {
   @Override   public void actionPerformed(ActionEvent e) {
      tfID.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
      if (JOptionPane.YES_OPTION
         == JOptionPane.showConfirmDialog(null,
            "Confirma a exclusão do registro <Nome = " + entidade.getNomePokemon()+ ">?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
         controle.remover(entidade);
      }
      btBuscar.setVisible(true);
      btListar.setVisible(true);
      tfID.setEditable(true);

      tfNome.setText("");
      tfNatureza.setText("");
      tfForca.setText("");
      cbSaudavel.setSelected(false);
      cbSaudavel.setEnabled(true);

      tfID.requestFocus();
      tfID.selectAll();
      btExcluir.setVisible(false);
      btAlterar.setVisible(false);
      texto.setText("Excluiu o registro de " + entidade.getIdPokemon()+ " - " + entidade.getNomePokemon()+ "\n\n\n\n\n");//limpa o campo texto
   }
});
btListar.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
      List<Pokemon> lt = controle.listInOrderId();


      String[] colunas = {"ID", "Nome", "Natureza", "Forca", "Saudavel"};
      Object[][] dados = new Object[lt.size()][colunas.length];
      String aux[];
      for (int i = 0; i < lt.size(); i++) {
         aux = lt.get(i).toString().split(";");
         for (int j = 0; j < colunas.length; j++) {
            dados[i][j] = aux[j];
         }
      }
      cardLayout.show(painelSul, "Listagem");
      scrollTabela.setPreferredSize(tabela.getPreferredSize());
      painel2.add(scrollTabela);
      scrollTabela.setViewportView(tabela);
      model.setDataVector(dados, colunas);
      btAlterar.setVisible(false);
      btExcluir.setVisible(false);
      btLocalizar.setVisible(true);
      btAdicionar.setVisible(false);
   }
});
btLocalizar.addActionListener(new ActionListener() {
    
            String[] nomeColuna = {"ID", "Nome", "Natureza", "Forca", "Saudavel"};
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<Pokemon> listaAuxiliar = controle.listInOrderId();
                if (listaAuxiliar.size() > 0) {
                    Point lc = btLocalizar.getLocationOnScreen();
                    lc.x = lc.x + btLocalizar.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y,
                            nomeColuna).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        tfID.setText(aux[0]);
                        btBuscar.doClick();
                    } else {
                        tfID.requestFocus();
                        tfID.selectAll();
                    }
                }
            }
        });
addWindowListener(new WindowAdapter() {
   @Override
   public void windowClosing(WindowEvent e) {
      //antes de sair, salvar a lista em disco
     
      // Sai da classe
      dispose();
   }
});

setVisible(true);

//depois que a tela ficou visível, clic o botão automaticamente.


}

}
