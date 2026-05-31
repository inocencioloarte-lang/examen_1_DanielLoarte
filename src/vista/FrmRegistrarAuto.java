package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Auto;
import model.AutoModel;
import util.ValidateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class FrmRegistrarAuto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtFechaVenta;
	private JTextField txtFechaProduccion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JButton btnRegistrar;
	private JCheckBox chckEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistrarAuto frame = new FrmRegistrarAuto();
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
	public FrmRegistrarAuto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de auto");
		lblTitulo.setBounds(160, 10, 115, 18);
		contentPane.add(lblTitulo);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(98, 56, 69, 12);
		contentPane.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(227, 53, 96, 18);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(227, 78, 96, 18);
		contentPane.add(txtMarca);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(98, 81, 69, 12);
		contentPane.add(lblMarca);
		
		txtFechaVenta = new JTextField();
		txtFechaVenta.setColumns(10);
		txtFechaVenta.setBounds(227, 106, 96, 18);
		contentPane.add(txtFechaVenta);
		
		JLabel lblFechaVenta = new JLabel("Fecha de venta");
		lblFechaVenta.setBounds(98, 109, 96, 12);
		contentPane.add(lblFechaVenta);
		
		txtFechaProduccion = new JTextField();
		txtFechaProduccion.setColumns(10);
		txtFechaProduccion.setBounds(227, 132, 96, 18);
		contentPane.add(txtFechaProduccion);
		
		JLabel lblFechaProduccion = new JLabel("Fecha de producción");
		lblFechaProduccion.setBounds(98, 135, 96, 12);
		contentPane.add(lblFechaProduccion);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(227, 158, 96, 18);
		contentPane.add(txtStock);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(98, 161, 69, 12);
		contentPane.add(lblStock);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(227, 186, 96, 18);
		contentPane.add(txtPrecio);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(98, 189, 69, 12);
		contentPane.add(lblPrecio);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(227, 222, 84, 20);
		contentPane.add(btnRegistrar);
		
		chckEstado = new JCheckBox("Activo");
		chckEstado.setBounds(98, 222, 92, 20);
		contentPane.add(chckEstado);

	}
	
	 @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			RegistrarAuto();
		}
		
	}	
	 
	private void RegistrarAuto() {
	
			try {
				
				String modelo = txtModelo.getText();
				String marca = txtMarca.getText();
				String fecha_venta = txtFechaVenta.getText();
				String fecha_produccion = txtFechaProduccion.getText();
				String stock = txtStock.getText();
				String precio = txtPrecio.getText();
				
				
				int estado = chckEstado.isSelected() ? 1:0;
				
				
				if (modelo.matches(ValidateUtil.TEXTO_40) == false) {
					JOptionPane.showMessageDialog(this, "El modelo no es válido. Tiene que tener de 1 a 40 caracteres");
					return;
				}
				if (marca.matches(ValidateUtil.TEXTO_40) == false) {
					JOptionPane.showMessageDialog(this, "La marca no es válida. Tiene que tener de 1 a 40 caracteres");
					return;
				}
				if (fecha_venta.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
					JOptionPane.showMessageDialog(this,"La fecha de vencimiento no es válida. Tiene que tener el formato YYYY-MM-DD");
					return;
				}
		        if (fecha_produccion.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
					JOptionPane.showMessageDialog(this,"La fecha de producción no es válida. Tiene que tener el formato YYYY-MM-DD");
					return;
				}
		        if (!stock.matches(ValidateUtil.ENTERO_MAS_UN_DIGITO)) {
		            JOptionPane.showMessageDialog(this, "El stock no es válido");
		            return;
		        }
		        if (!precio.matches(ValidateUtil.REAL_CON_O_SIN_DECIMALES)) {
		            JOptionPane.showMessageDialog(this, "Precio inválido");
		            return;
		        }   
		        
		        int stockInt = Integer.parseInt(stock);
		        double precioDouble = Double.parseDouble(precio);
		        
		        
		        
		     // 2. Crear objeto
		        Auto c = new Auto();
		        c.setModelo(modelo);
		        c.setMarca(marca); 
		        c.setFecha_venta(LocalDate.parse(fecha_venta));
		        c.setFecha_produccion(LocalDate.parse(fecha_produccion));
		        c.setStock(stockInt);
		        c.setPrecio(precioDouble);
		        c.setEstado(estado);
			
		        // 3. Enviar al model
		        AutoModel model = new AutoModel();
		        int resultado = model.insertaAuto(c);
		
		        // 4. MensaDje
		        if (resultado > 0) {
		            JOptionPane.showMessageDialog(this, "Auto registrado correctamente");
		        } else {
		            JOptionPane.showMessageDialog(this, "Error al registrar concurso");
		        } 
        
	} catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
    }
	}
}
