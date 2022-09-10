package entidad;

public class Cliente {
	 
	private int idCedula;
	private String nombres;
	private String apellidos;
	private Double Saldo;
	
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Double getSaldo() {
		return Saldo;
	}
	public void setSaldo(Double saldo) {
		Saldo = saldo;
	}
	
	
	
	public int getIdCedula() {
		return idCedula;
	}
	public void setIdCedula(int idCedula) {
		this.idCedula = idCedula;
	}
	public Cliente(String nombres, String apellidos, Double saldo) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		Saldo = saldo;
	}
	
	
	public Cliente(int idCedula, String nombres, String apellidos, Double saldo) {
		super();
		this.idCedula = idCedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		Saldo = saldo;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + idCedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", Saldo=" + Saldo + "]";
	}
	
	
	
}