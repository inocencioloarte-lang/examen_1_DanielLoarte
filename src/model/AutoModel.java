package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Auto;
import util.MySqlDBConexion;

public class AutoModel {

	public int insertaAuto(Auto obj){
		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "INSERT INTO auto (modelo, marca, fecha_venta, fecha_produccion, stock, precio, estado) VALUES (?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getModelo());
			pstm.setString(2, obj.getMarca());
			pstm.setDate(3, java.sql.Date.valueOf(obj.getFecha_venta()));
			pstm.setDate(4, java.sql.Date.valueOf(obj.getFecha_produccion()));
			pstm.setInt(5, obj.getStock());
			pstm.setDouble(6, obj.getPrecio());
			pstm.setInt(7, obj.getEstado());
			
			//3 Ejecutar sentencia SQL
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
	
	public List<Auto> listaAuto(String modelo, String marca) {
		ArrayList<Auto> lista = new ArrayList<Auto>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT * FROM auto "
	                   + "WHERE modelo LIKE ? "
	                   + "AND marca LIKE ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + modelo+ "%");
			pstm.setString(2, "%" + marca + "%");
			
			
			//imprimir el query para verificar que se arma correctamente
			System.out.println("SQL: " + pstm);
			
			//Se ejecuta el query en la base de datos
			rs = pstm.executeQuery();

			while (rs.next()) {
				Auto a = new Auto();
				a.setModelo(rs.getString("modelo"));
				a.setMarca(rs.getString("marca"));
				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}

	
		
}