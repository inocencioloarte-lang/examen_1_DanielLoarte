package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.Auto;
import model.AutoModel;
import util.ValidateUtil;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmConsultarAuto extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTable table;
	private JButton btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultarAuto frame = new FrmConsultarAuto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmConsultarAuto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consultar auto");
		lblTitulo.setBounds(157, 10, 82, 16);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblTitulo);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(50, 51, 82, 16);
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblModelo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(50, 88, 82, 16);
		contentPane.add(lblMarca);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(98, 121, 84, 20);
		contentPane.add(btnFiltrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addKeyListener(this);
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(234, 121, 84, 20);
		contentPane.add(btnCancelar);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(142, 51, 228, 18);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(142, 88, 228, 18);
		contentPane.add(txtMarca);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 151, 321, 79);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Modelo", "Marca"
			}
		));
		scrollPane.setViewportView(table);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(e);
		}
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
	}
	
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
	    txtModelo.setText("");
	    txtMarca.setText("");

	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	}
	
	
	protected void do_btnFiltrar_actionPerformed(ActionEvent e) {
		//1 Recibimos todos los parametros del formulario
		String modelo = txtModelo.getText();
		String marca = txtMarca.getText();
		
		//imprimir los parametros recibidos
		System.out.println("Parametros recibidos: ");
		System.out.println("Modelo: " + modelo);
		System.out.println("Marca: " + marca);
		
		
		//3 Crear la clase model
		AutoModel objAutoModel = new AutoModel();
		List<Auto> lista = objAutoModel.listaAuto(modelo,marca);
		
		//4 recorremos la lista
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
		
		for (Auto a : lista) {
			Object[] rowData = { a.getModelo(), 
								 a.getMarca(), 
								 };
			model.addRow(rowData);
		}
		
}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_keyReleased(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		
	}
	protected void do_btnCancelar_keyReleased(KeyEvent e) {
	
		txtModelo.setText("");
		txtMarca.setText("");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Limpiar la tabla
		
	}
}

