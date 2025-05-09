import java.time.LocalDate;

public class Vente {
	
	public int ID_Vente;
	public LocalDate Date_Vente;
	public String Produit;
	public String Categorie;
	public int Quantite;
	public double Prix_Unitaire;
	public double Total;
	
	public Vente(int iD_Vente, LocalDate date_Vente, String produit, String categorie, int quantite, double prix_Unitaire, 
			double total) {
		super();
		ID_Vente = iD_Vente;
		Date_Vente = date_Vente;
		Produit = produit;
		Categorie = categorie;
		Quantite = quantite;
		Prix_Unitaire = prix_Unitaire;
		Total = total;
	}
	public Vente(String csvline)
	{
		String[] list = csvline.split(",");
		String[] date = list[1].split("-");
		
		this.ID_Vente = Integer.parseInt(list[0]);
		this.Date_Vente = LocalDate.parse(list[1]);

		this.Produit = list[2];
		this.Categorie = list[3];
		this.Quantite = Integer.parseInt(list[4]);
		this.Prix_Unitaire = Double.parseDouble(list[5]);
		this.Total = Double.parseDouble(list[6]);
	}
	public void Affiche()
	{
		System.out.println("ID_Vente : " + this.ID_Vente);
		System.out.println("Date_Vente : " + this.Date_Vente);
		System.out.println("Produit : " + this.Produit);
		System.out.println("Categorie : " + this.Categorie);
		System.out.println("Quantite : " + this.Quantite);
		System.out.println("Prix_Unitaire : " + this.Prix_Unitaire);
		System.out.println("Total : " + this.Total);
	}
	
	public int getID_Vente() {
		return ID_Vente;
	}
	
	public void setID_Vente(int iD_Vente) {
		ID_Vente = iD_Vente;
	}
	
	public LocalDate getDate_Vente() {
		return Date_Vente;
	}
	
	public void setDate_Vente(LocalDate date_Vente) {
		Date_Vente = date_Vente;
	}
	
	public String getProduit() {
		return Produit;
	}
	
	public void setProduit(String produit) {
		Produit = produit;
	}
	
	public String getCategorie() {
		return Categorie;
	}
	
	public void setCategorie(String categorie) {
		Categorie = categorie;
	}
	
	public int getQuantite() {
		return Quantite;
	}
	
	public void setQuantite(int quantite) {
		Quantite = quantite;
	}
	
	public double getPrix_Unitaire() {
		return Prix_Unitaire;
	}
	
	public void setPrix_Unitaire(double prix_Unitaire) {
		Prix_Unitaire = prix_Unitaire;
	}
	
	public double getTotal() {
		return Total;
	}
	
	public void setTotal(double total) {
		Total = total;
	}
}
