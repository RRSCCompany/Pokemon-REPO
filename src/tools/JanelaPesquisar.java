package tools;

// @author Radames
import Entidades.Pokemon;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class JanelaPesquisar extends JDialog {

    private String valorRetornado = "";
    private List<Pokemon> lista;
    private int x = 0;
    private int y = 0;
    private Dimension dimensao = new Dimension(500, 400);
    private String titulo = "Pesquisar";
    private Object[][] dados;
    private String[] auxiliar;
    private String[] nomeColuna;
    private JTable tabela;
    private JScrollPane spTabela;
    private JToolBar toolbar = new JToolBar();
    private JTextField pesquisa = new JTextField();
    private JPanel painelPesquisa = new JPanel();
    
    public JanelaPesquisar(List<Pokemon> lista, int x, int y, String[] nomeColuna) {//esse construtor é importante para receber dados 
        this.lista = lista;
        this.x = x;
        this.y = y;
        this.nomeColuna = nomeColuna;
    }

    public JanelaPesquisar(List<Pokemon> lista, int x, int y, Dimension dimensao, String titulo) {//esse construtor é importante para receber dados 
        this.lista = lista;
        this.x = x;
        this.y = y;
        this.dimensao = dimensao;
    }

    public String getValorRetornado() {
        inicialize();

        setVisible(true);
        return valorRetornado;
    }

    public void finalizeJanela() {
        dispose();
    }

    public void inicialize() {
        setTitle(titulo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setModal(true);
        
        Container containerLista = new JPanel();
        JPanel painelTabela = new JPanel();
        Container cp;
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        // populate list
        dados = new String[lista.size()][nomeColuna.length];
        auxiliar = new String[nomeColuna.length];
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < nomeColuna.length; j++) {
                
                auxiliar = lista.get(i).toString().split(";");
                dados[i][j] = auxiliar[j];
            }
        }
        tabela = new JTable(dados, nomeColuna);
        DefaultTableModel tableModel = new DefaultTableModel(dados, nomeColuna){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        tabela.setModel(tableModel);
        spTabela = new JScrollPane(tabela);
        
        pesquisa = createRowFilter(tabela);
        painelPesquisa.setLayout(new GridLayout(1,1));
        painelPesquisa.add(toolbar);
        toolbar.add(pesquisa);
        
        painelTabela.add(spTabela);
        containerLista.setLayout(new GridLayout(1, 1));
        containerLista.add(painelTabela);
        JScrollPane pane = new JScrollPane(containerLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BorderLayout());
        painelCentro.add(pane, BorderLayout.CENTER);
        cp.add(painelPesquisa, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(new JLabel("<Clic duplo = seleciona>"), BorderLayout.SOUTH);

        //setLocationRelativeTo(null);
        setLocation(x, y);

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { //clic duplo
                    final JTable jTable = (JTable)e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    final String valueInCell = (String)tabela.getValueAt(row, 0);
                    valorRetornado = valueInCell;
                    dispose();
                }
            }
        });
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {
            }

            @Override
            public void windowClosing(WindowEvent we) {
            }

            @Override
            public void windowClosed(WindowEvent we) {
                valorRetornado = null;
                dispose();
            }

            @Override
            public void windowIconified(WindowEvent we) {
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
            }

            @Override
            public void windowActivated(WindowEvent we) {

            }
            
            @Override
            public void windowDeactivated(WindowEvent we) {
            }
        });

    }

    private JTextField createRowFilter(JTable tabela) {
        RowSorter<? extends TableModel> rs = tabela.getRowSorter();
        if(rs == null){
            tabela.setAutoCreateRowSorter(true);
            rs = tabela.getRowSorter();
        }
        
        TableRowSorter<? extends TableModel> rowSorter = (rs instanceof TableRowSorter) ? (TableRowSorter<? extends TableModel>) rs:null;
        if(rowSorter==null){
            throw new RuntimeException("Cannot find appropriate rowSorter: " + rs);
        }
        
        final JTextField tf = new JTextField();
        
        tf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                update(de);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                update(de);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                update(de);
            }
            
            private void update(DocumentEvent de){
                String text = tf.getText();
                if(text.trim().length() == 0){
                    rowSorter.setRowFilter(null);
                } else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
        return tf;
    }
}
