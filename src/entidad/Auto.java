package entidad;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Auto {

	private int idauto;
	private String modelo;
	private String marca;
	private LocalDate fecha_venta;
	private LocalDate fecha_produccion;
	private int stock;
	private double precio;
	private int estado;

}