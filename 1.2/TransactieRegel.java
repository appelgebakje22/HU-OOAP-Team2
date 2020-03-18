package Domain;

public class TransactieRegel {
	private int aantal;
	private int regelTotaal;

	private Transactie transactie;
	private Product product;


	public TransactieRegel(Product product, int aantal, int regelTotaal) {
		this.product = product;
		this.aantal = aantal;
		this.regelTotaal = regelTotaal;
	}

	public Transactie getTransactie() {
		return transactie;
	}

	public void setTransactie(Transactie transactie) {
		this.transactie = transactie;
	}

	public Product getProduct() {
		return product;
	}

	public int getAantal() {
		return aantal;
	}

	public void setRegelTotaal(int regelTotaal) {
		this.regelTotaal = regelTotaal;
	}
}